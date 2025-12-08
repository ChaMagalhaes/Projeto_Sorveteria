package br.edu.iftm.charles.sistemasorveteria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author charl
 */
public class ConexaoBD {
    private Connection conexao;
    
    private final String URL = "jdbc:mysql://localhost:3306/sorveteria";
    
    private final String USUARIO = "app_user";
    private final String SENHA = "123456";

    public Connection conectar() {
        try {
            if(conexao == null || conexao.isClosed()){
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                System.out.println(">> Sucesso: Conexão com banco de dados estavel");
            }
        } catch (SQLException e) {
            System.out.println(">> Erro: Não foi possivel conectar ao Banco de dados.");
            System.out.println("Mensagem: " + e.getMessage());
            e.printStackTrace();
        }
        return conexao;
    }
    
    public void desconectar(){
        try{
            if(conexao != null && !conexao.isClosed()){
                conexao.close();
                System.out.println(">> Conexão encerrada.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
