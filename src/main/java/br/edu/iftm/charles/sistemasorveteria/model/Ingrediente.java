package br.edu.iftm.charles.sistemasorveteria.model;

/**
 *
 * @author charl
 */
public class Ingrediente {
    private int id_ingrediente;
    private String nome;
    private double estoque;
    private String unidade_medida;

    public Ingrediente() {
    }

    public Ingrediente(int id_ingrediente, String nome, double estoque, String unidade_medida) {
        this.id_ingrediente = id_ingrediente;
        this.nome = nome;
        this.estoque = estoque;
        this.unidade_medida = unidade_medida;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public String getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida = unidade_medida;
    }
}
