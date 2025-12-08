package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Ingrediente;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {
    private final Connection conn;
    
    public IngredienteDAO() {
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Ingrediente i) {
        String sql = "INSERT INTO ingrediente (nome, estoque, unidade_medida) VALUES (?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, i.getNome());
            stmt.setDouble(2, i.getEstoque());
            stmt.setString(3, i.getUnidadeMedida());
            
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Ingrediente> listarTodos() {
        List<Ingrediente> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingrediente ORDER BY nome";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                Ingrediente i = new Ingrediente();
                i.setId_ingrediente(rs.getInt("id_ingrediente"));
                i.setNome(rs.getString("nome"));
                i.setEstoque(rs.getDouble("estoque"));
                i.setUnidadeMedida(rs.getString("unidade_medida"));
                
                lista.add(i);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
}