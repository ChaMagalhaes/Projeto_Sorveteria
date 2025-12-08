package br.edu.iftm.charles.sistemasorveteria.dao;

import br.edu.iftm.charles.sistemasorveteria.model.Funcionario;
import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private final Connection conn;
    
    public FuncionarioDAO(){
        conn = new ConexaoBD().conectar();
    }
    
    public void salvar(Funcionario f) {
        String sql = "INSERT INTO funcionario (nome, telefone, email, endereco, cpf, salario, cargo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getEndereco());
            stmt.setString(5, f.getCpf());
            stmt.setDouble(6, f.getSalario());
            stmt.setString(7, f.getCargo());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionario ORDER BY nome";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql); 
            ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setId_funcionario(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                f.setCargo(rs.getString("cargo"));
                f.setSalario(rs.getDouble("salario"));
                
                lista.add(f);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Funcionario f = new Funcionario();
                    f.setId_funcionario(rs.getInt("id_funcionario"));
                    f.setNome(rs.getString("nome"));
                    f.setCpf(rs.getString("cpf"));
                    f.setCargo(rs.getString("cargo"));
                    f.setSalario(rs.getDouble("salario"));
                    f.setTelefone(rs.getString("telefone"));
                    f.setEndereco(rs.getString("endereco"));
                    return f;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    
    public Funcionario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, cpf);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Funcionario f = new Funcionario();
                    f.setId_funcionario(rs.getInt("id_funcionario"));
                    f.setCpf(rs.getString("cpf"));
                    f.setNome(rs.getString("nome"));
                    return f;
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}