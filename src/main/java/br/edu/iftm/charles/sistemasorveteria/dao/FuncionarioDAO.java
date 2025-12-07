package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import br.edu.iftm.charles.sistemasorveteria.model.Funcionario;
import br.edu.iftm.charles.sistemasorveteria.model.Venda;
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
public class FuncionarioDAO {
    private final Connection conn;
    
    public FuncionarioDAO(){
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Funcionario funcionario){
        String sql = "INSERT INTO funcionario (nome, telefone, email, endereco, cpf, salario, cargo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getEndereco());
            stmt.setString(5, funcionario.getCpf());
            stmt.setDouble(6, funcionario.getSalario());
            stmt.setString(7, funcionario.getCargo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar funcionário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<Funcionario> listarTodos(){
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionario ORDER BY nome";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery()) {
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                
                // IMPORTANTE: O ID é necessário para editar e excluir
                f.setId_funcionario(rs.getInt("id_funcionario"));
                
                f.setNome(rs.getString("nome"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setEndereco(rs.getString("endereco"));
                f.setCpf(rs.getString("cpf"));
                f.setSalario(rs.getDouble("salario"));
                f.setCargo(rs.getString("cargo"));
                
                lista.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void atualizar(Funcionario f){
        String sql = "UPDATE funcionario SET nome = ?, telefone = ?, email = ?, endereco = ?, cpf = ?, salario = ?, cargo = ? WHERE id_funcionario = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getEndereco());
            stmt.setString(5, f.getCpf());
            stmt.setDouble(6, f.getSalario());
            stmt.setString(7, f.getCargo());
            stmt.setInt(8, f.getId_funcionario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<Venda> listarVendasFuncionario(int id_funcionario){
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda WHERE id_funcionario = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id_funcionario);
            
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Venda v = new Venda();
                    
                    v.setId_venda(rs.getInt("id_venda"));
                    if (rs.getTimestamp("data_venda") != null) {
                        v.setData_venda(rs.getTimestamp("data_venda").toLocalDateTime());
                    }
                    v.setTotal(rs.getDouble("total"));
                    v.setStatus(rs.getString("status"));
                    v.setForma_pagamento(rs.getString("forma_pagamento"));
                    
                    Funcionario f = new Funcionario();
                    f.setId_funcionario(id_funcionario);
                    v.setFuncionario(f);
                    
                    vendas.add(v);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return vendas;
    }
    
    public Funcionario buscaPorCpf(String cpf){
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        Funcionario f = null;
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, cpf);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    f = new Funcionario();
                    f.setId_funcionario(rs.getInt("id_funcionario"));
                    f.setNome(rs.getString("nome"));
                    f.setTelefone(rs.getString("telefone"));
                    f.setEmail(rs.getString("email"));
                    f.setEndereco(rs.getString("endereco"));
                    f.setCpf(rs.getString("cpf"));
                    f.setSalario(rs.getDouble("salario"));
                    f.setCargo(rs.getString("cargo"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return f;
    }
}