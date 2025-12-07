package br.edu.iftm.charles.sistemasorveteria;

import br.edu.iftm.charles.sistemasorveteria.util.ConexaoBD;

/**
 *
 * @author charl
 */
public class SistemaSorveteria {

    public static void main(String[] args) {
        ConexaoBD cn = new ConexaoBD();
        
        cn.conectar();
    }
}
