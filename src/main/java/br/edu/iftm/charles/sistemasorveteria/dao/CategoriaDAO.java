package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Categoria;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private final Connection conn;
    
    public CategoriaDAO(){
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Categoria c) {
        String sql = "INSERT INTO categoria (nome_categoria) VALUES (?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, c.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Categoria> listarTodos() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome_categoria"));
                
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return new Categoria(rs.getInt("id_categoria"), rs.getString("nome_categoria"));
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}