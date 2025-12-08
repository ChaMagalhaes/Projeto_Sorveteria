package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Categoria;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final Connection conn;
    
    public ProdutoDAO(){
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Produto p) {
        String sql = "INSERT INTO produto (id_categoria, nome, preco, descricao) VALUES (?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getCategoria().getId_categoria());
            stmt.setString(2, p.getNome());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getDescricao());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        // Usando a View criada no script para pegar o nome da categoria junto
        String sql = "SELECT * FROM vw_produto_e_categoria";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Produto p = new Produto();
                p.setId_produto(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setDescricao(rs.getString("descricao"));
                
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome_categoria"));
                
                p.setCategoria(c);
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId_produto(rs.getInt("id_produto"));
                    p.setNome(rs.getString("nome"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setDescricao(rs.getString("descricao"));
                    
                    Categoria c = new Categoria();
                    c.setId_categoria(rs.getInt("id_categoria"));
                    p.setCategoria(c);
                    
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}