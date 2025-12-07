package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import br.edu.iftm.charles.sistemasorveteria.model.Funcionario;
import br.edu.iftm.charles.sistemasorveteria.model.Item_Venda;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import br.edu.iftm.charles.sistemasorveteria.model.Venda;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charl
 */
public class VendaDAO {
    
    private final Connection conn;
    
    public VendaDAO() {
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Venda venda) {
        String sqlVenda = "INSERT INTO venda (id_cliente, id_funcionario, data_venda, forma_pagamento, status, total) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlItem = "INSERT INTO item_venda (id_venda, id_produto, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            
            stmtVenda.setInt(1, venda.getCliente().getId_cliente());
            stmtVenda.setInt(2, venda.getFuncionario().getId_funcionario());
            stmtVenda.setTimestamp(3, Timestamp.valueOf(venda.getData_venda()));
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
                stmtItem.setDouble(4, item.getValorUnitario());
                stmtItem.setDouble(5, item.getSubtotal());
                
                stmtItem.executeUpdate();
            }
            stmtItem.close(); 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Venda> listarTodos() {
        List<Venda> lista = new ArrayList<>();
        
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
        Venda v = null;
        String sql = "SELECT * FROM vw_listar_cli_fun_venda vw WHERE vw.id_venda = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    v = new Venda();
                    v.setId_venda(rs.getInt("id_venda"));
                    v.setData_venda(rs.getTimestamp("data_venda").toLocalDateTime());
                    v.setTotal(rs.getDouble("total"));
                    
                    Cliente c = new Cliente();
                    c.setId_cliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome_cliente"));
                    v.setCliente(c);
                    
                    Funcionario f = new Funcionario();
                    f.setId_funcionario(rs.getInt("id_funcionario"));
                    f.setNome(rs.getString("nome_funcionario"));
                    v.setFuncionario(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }
    
    public List<Item_Venda> buscarItensPorVenda(int idVenda) {
    List<Item_Venda> lista = new ArrayList<>();
    
    String sql = "SELECT i.id, i.quantidade, i.valor_unitario, i.subtotal, "
               + "p.id AS id_prod, p.nome AS nome_prod, p.preco AS preco_prod "
               + "FROM tb_itens_venda i "
               + "INNER JOIN tb_produto p ON i.produto_id = p.id "
               + "WHERE i.venda_id = ?";

    try (Connection con = new ConexaoBD().conectar();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setInt(1, idVenda);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId_produto(rs.getInt("id_prod"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));

                Item_Venda item = new Item_Venda();
                item.setId(rs.getInt("id_venda"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValorUnitario(rs.getDouble("preco_unitario"));
                item.setSubtotal(rs.getDouble("subtotal"));
                
                item.setProduto(p);
                
                lista.add(item);
            }
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar itens da venda");
    }

    return lista;
}
}