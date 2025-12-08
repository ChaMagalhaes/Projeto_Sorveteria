package br.edu.iftm.charles.sistemasorveteria.model;

public class Ingrediente {
    private int id_ingrediente;
    private String nome;
    private double estoque;
    private String unidadeMedida; // No banco: unidade_medida

    public Ingrediente() {
    }

    public Ingrediente(int id_ingrediente, String nome, double estoque, String unidadeMedida) {
        this.id_ingrediente = id_ingrediente;
        this.nome = nome;
        this.estoque = estoque;
        this.unidadeMedida = unidadeMedida;
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

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}