package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import br.edu.iftm.charles.sistemasorveteria.model.Funcionario;
import br.edu.iftm.charles.sistemasorveteria.model.Item_Venda;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import br.edu.iftm.charles.sistemasorveteria.model.Venda;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    
    private final Connection conn;
    
    public VendaDAO() {
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Venda venda) {
        String sqlVenda = "INSERT INTO venda (id_cliente, id_funcionario, data_venda, forma_pagamento, status, total) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlItem = "INSERT INTO item_venda (id_venda, id_produto, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        try {
            conn.setAutoCommit(false); // Inicia transação

            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setInt(1, venda.getCliente().getId_cliente());
            stmtVenda.setInt(2, venda.getFuncionario().getId_funcionario());
            // Se data_venda for nula, usamos a data atual
            if (venda.getData_venda() != null) {
                stmtVenda.setTimestamp(3, Timestamp.valueOf(venda.getData_venda()));
            } else {
                stmtVenda.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            }
            stmtVenda.setString(4, venda.getForma_pagamento());
            stmtVenda.setString(5, venda.getStatus());
            stmtVenda.setDouble(6, venda.getTotal());
            
            stmtVenda.executeUpdate();
            
            ResultSet rs = stmtVenda.getGeneratedKeys();
            int idVendaGerado = 0;
            if (rs.next()) {
                idVendaGerado = rs.getInt(1);
                venda.setId_venda(idVendaGerado);
            }
            stmtVenda.close();
            
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            for (Item_Venda item : venda.getItens()) {
                stmtItem.setInt(1, idVendaGerado);
                stmtItem.setInt(2, item.getProduto().getId_produto());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setDouble(4, item.getPrecoUnitario());
                stmtItem.setDouble(5, item.getSubtotal());
                
                stmtItem.executeUpdate();
            }
            stmtItem.close();
            
            conn.commit(); // Salva tudo
            conn.setAutoCommit(true);
            
        } catch (SQLException e) {
            try {
                conn.rollback(); // Desfaz se der erro
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    
    public List<Venda> listarTodos() {
        List<Venda> lista = new ArrayList<>();
        // Usando a View para facilitar a listagem com nomes
        String sql = "SELECT * FROM vw_listar_cli_fun_venda";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Venda v = new Venda();
                v.setId_venda(rs.getInt("id_venda"));
                v.setData_venda(rs.getTimestamp("data_venda").toLocalDateTime());
                v.setForma_pagamento(rs.getString("forma_pagamento"));
                v.setStatus(rs.getString("status"));
                v.setTotal(rs.getDouble("total"));
                
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome_cliente"));
                v.setCliente(c);
                
                Funcionario f = new Funcionario();
                f.setId_funcionario(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome_funcionario"));
                v.setFuncionario(f);
                
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Venda buscarPorId(int id) {
        Venda venda = null;
        String sql = "SELECT * FROM vw_listar_cli_fun_venda WHERE id_venda = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venda = new Venda();
                    venda.setId_venda(rs.getInt("id_venda"));
                    
                    // Convertendo Timestamp do banco para LocalDateTime do Java
                    if (rs.getTimestamp("data_venda") != null) {
                        venda.setData_venda(rs.getTimestamp("data_venda").toLocalDateTime());
                    }
                    
                    venda.setForma_pagamento(rs.getString("forma_pagamento"));
                    venda.setStatus(rs.getString("status"));
                    venda.setTotal(rs.getDouble("total"));
                    
                    // Preenchendo o Cliente (Dados vindos da View)
                    Cliente c = new Cliente();
                    c.setId_cliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome_cliente"));
                    venda.setCliente(c);
                    
                    // Preenchendo o Funcionário (Dados vindos da View)
                    Funcionario f = new Funcionario();
                    f.setId_funcionario(rs.getInt("id_funcionario"));
                    f.setNome(rs.getString("nome_funcionario"));
                    venda.setFuncionario(f);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar venda por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return venda;
    }
    
    public List<Item_Venda> buscarItensPorVenda(int idVenda) {
        List<Item_Venda> lista = new ArrayList<>();
        
        String sql = "SELECT i.quantidade, i.preco_unitario, i.subtotal, "
                   + "p.id_produto, p.nome, p.preco "
                   + "FROM item_venda i "
                   + "INNER JOIN produto p ON i.id_produto = p.id_produto "
                   + "WHERE i.id_venda = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVenda);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setId_produto(rs.getInt("id_produto"));
                    p.setNome(rs.getString("nome"));
                    p.setPreco(rs.getDouble("preco"));

                    Item_Venda item = new Item_Venda();
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setPrecoUnitario(rs.getDouble("preco_unitario"));
                    item.setSubtotal(rs.getDouble("subtotal"));
                    
                    item.setProduto(p);
                    
                    lista.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}