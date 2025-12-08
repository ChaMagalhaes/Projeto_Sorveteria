package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final Connection conn;
    
    public ClienteDAO(){
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Cliente c) {
        String sql = "INSERT INTO cliente (nome, telefone, email, endereco, documento) VALUES (?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getEndereco());
            stmt.setString(5, c.getDocumento());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY nome";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId_cliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setDocumento(rs.getString("documento"));
                
                lista.add(c);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId_cliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setDocumento(rs.getString("documento"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setEmail(rs.getString("email"));
                    c.setEndereco(rs.getString("endereco"));
                    return c;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    
    
    public Cliente buscarPorDocumento(String doc) {
        String sql = "SELECT * FROM cliente WHERE documento = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, doc);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Cliente c = new Cliente();
                    c.setId_cliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setDocumento(rs.getString("documento"));
                    // Pode preencher o resto se precisar
                    return c;
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}