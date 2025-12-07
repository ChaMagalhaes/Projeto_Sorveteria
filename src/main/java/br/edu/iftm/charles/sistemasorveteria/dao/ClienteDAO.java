/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import br.edu.iftm.charles.sistemasorveteria.model.Venda;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import br.edu.iftm.charles.sistemasorveteria.model.Item_Venda;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charl
 */
public class ClienteDAO {
    private final Connection conn;
    
    public ClienteDAO(){
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Cliente cliente){
        String sql = "INSERT INTO cliente (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setDocumento(rs.getString("documento"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, documento = ?, telefone = ?, email = ?, endereco = ? WHERE id_cliente = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setInt(6, cliente.getId_cliente());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Cliente buscarPorDocumento(String documento) {
        String sql = "SELECT * FROM cliente WHERE documento = ?";
        Cliente c = null;
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setId_cliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setDocumento(rs.getString("documento"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                    c.setEndereco(rs.getString("endereco"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome LIKE ? ORDER BY nome";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId_cliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setDocumento(rs.getString("documento"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                    c.setEndereco(rs.getString("endereco"));
                    
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Item_Venda> listarHistoricoCompras(int idCliente) {
        List<Item_Venda> historico = new ArrayList<>();
        
        String sql = "SELECT * FROM vw_historico_compras WHERE id_cliente = ? ORDER BY data_venda DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item_Venda item = new Item_Venda();
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setValorUnitario(rs.getDouble("valor_unitario"));
                    item.setSubtotal(rs.getDouble("subtotal"));

              
                    Produto p = new Produto();
                    p.setNome(rs.getString("nome_produto"));
                    item.setProduto(p);

                    // Preenchendo a Venda (apenas data)
                    Venda v = new Venda();
                    v.setData_venda(rs.getTimestamp("data_venda").toLocalDateTime());
                    item.setVenda(v);

                    historico.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historico;
    }
}
