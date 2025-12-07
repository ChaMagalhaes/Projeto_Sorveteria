package br.edu.iftm.charles.sistemasorveteria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author charl
 */
public class ConexaoBD {
   private final String url = "jdbc:mysql://localhost:3306/sorveteria";
    private final String usuario = "app_user";
    private final String senha = "123456";

    public Connection conectar() {
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("Erro: Não foi possivel conectar ao Banco de dados.");
            ex.printStackTrace();
            return null;
        }
    } 
}
