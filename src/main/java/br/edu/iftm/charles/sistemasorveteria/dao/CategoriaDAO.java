package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.Connection;
import br.edu.iftm.charles.sistemasorveteria.model.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charl
 */
public class CategoriaDAO {
    private final Connection conn;
    
    public CategoriaDAO(){
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Categoria categoria){
    String sql = "INSERT INTO categoria (nome_categoria) VALUES (?)";
    
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM categoria WHERE id_categoria = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
        }catch(SQLException e){
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
    
    public void atualizar(Categoria c) { 
        String sql = "UPDATE categoria SET nome_categoria = ? WHERE id_categoria = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getId_categoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Produto> listarProdutoCategoria(int id_categoria){
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto WHERE id_categoria = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id_categoria);
            
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Produto p = new Produto();
                    
                    p.setId_produto(rs.getInt("id_produto"));
                    p.setNome(rs.getString("nome"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setDescricao(rs.getString("descricao"));
                    
                    Categoria c = new Categoria();
                    c.setId_categoria(id_categoria);
                    p.setCategoria(c);
                    
                    produtos.add(p);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return produtos;
    }
}
