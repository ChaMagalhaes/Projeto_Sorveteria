package br.edu.iftm.charles.sistemasorveteria.model;

/**
 *
 * @author charl
 */
public class Cliente {
    private int id_cliente;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String documento;

    public Cliente() {
    }

    public Cliente(int id_cliente, String nome, String telefone, String email, String endereco, String documento) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.documento = documento;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
