package br.edu.iftm.charles.sistemasorveteria.model;

/**
 *
 * @author charl
 */
public class Funcionario {
    private int id_funcionario;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String cpf;
    private double salario;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(int id_funcionario, String nome, String telefone, String email, String endereco, String cpf, double salario, String cargo) {
        this.id_funcionario = id_funcionario;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cpf = cpf;
        this.salario = salario;
        this.cargo = cargo;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
