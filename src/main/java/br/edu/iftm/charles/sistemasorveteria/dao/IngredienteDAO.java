/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import br.edu.iftm.charles.sistemasorveteria.model.Ingrediente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author charl
 */
public class IngredienteDAO {
    
    private final Connection conexao;

    public IngredienteDAO() {
        this.conexao = new ConexaoBD().conectar();
    }

    // --- MÉTODOS DE ESCRITA (CUD) ---

    public void salvar(Ingrediente obj) {
        // Atenção aos nomes exatos das colunas do seu banco
        String sql = "INSERT INTO ingrediente (nome, estoque, unidade_medida) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getEstoque());       // Ajustado para o campo estoque
            stmt.setString(3, obj.getUnidade_medida()); // Ajustado para o campo unidade_medida

            stmt.execute();
            System.out.println("LOG: Ingrediente salvo com sucesso!");

        } catch (SQLException e) {
            System.out.println("ERRO: Erro ao salvar ingrediente: " + e.getMessage());
        }
    }

    public void alterar(Ingrediente obj) {
        // Atualiza nome, estoque e unidade baseados no ID
        String sql = "UPDATE ingrediente SET nome = ?, estoque = ?, unidade_medida = ? WHERE id_ingrediente = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, obj.getNome());
            stmt.setDouble(2, obj.getEstoque());
            stmt.setString(3, obj.getUnidade_medida());
            stmt.setInt(4, obj.getId_ingrediente()); // Seu modelo deve ter getId() mapeando para id_ingrediente

            stmt.execute();
            System.out.println("LOG: Ingrediente alterado com sucesso!");

        } catch (SQLException e) {
            System.out.println("ERRO: Erro ao alterar ingrediente: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM ingrediente WHERE id_ingrediente = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.execute();
            System.out.println("LOG: Ingrediente excluído com sucesso!");

        } catch (SQLException e) {
            // Código 1451 é comum no MySQL para violação de constraint (chave estrangeira)
            if (e.getErrorCode() == 1451) { 
                System.out.println("ERRO: Não é possível excluir: Este ingrediente está sendo usado em um produto.");
            } else {
                System.out.println("ERRO: Erro ao excluir ingrediente: " + e.getMessage());
            }
        }
    }

    // --- MÉTODOS DE LEITURA (READ) ---

    public List<Ingrediente> listarTodos() {
        List<Ingrediente> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingrediente ORDER BY nome";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ingrediente ingrediente = new Ingrediente();
                // Mapeando as colunas do banco para o objeto
                ingrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
                ingrediente.setNome(rs.getString("nome"));
                ingrediente.setEstoque(rs.getDouble("estoque"));
                ingrediente.setUnidade_medida(rs.getString("unidade_medida"));
                
                lista.add(ingrediente);
            }

        } catch (SQLException e) {
            System.out.println("ERRO: Erro ao listar ingredientes: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca ingredientes vinculados a um produto na tabela 'produto_ingrediente'
     */
    public List<Ingrediente> listarPorProduto(int idProduto) {
        List<Ingrediente> lista = new ArrayList<>();
        
        // JOIN entre a tabela ingrediente e a tabela de ligação
        String sql = "SELECT i.* FROM ingrediente i " +
                     "INNER JOIN produto_ingrediente pi ON i.id_ingrediente = pi.id_ingrediente " +
                     "WHERE pi.id_produto = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ingrediente ingrediente = new Ingrediente();
                    ingrediente.setId_ingrediente(rs.getInt("id_ingrediente"));
                    ingrediente.setNome(rs.getString("nome"));
                    ingrediente.setEstoque(rs.getDouble("estoque"));
                    ingrediente.setUnidade_medida(rs.getString("unidade_medida"));
                    
                    lista.add(ingrediente);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERRO: Erro ao buscar ingredientes do produto: " + e.getMessage());
        }
        return lista;
    }
}