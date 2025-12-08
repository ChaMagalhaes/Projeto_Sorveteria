package br.edu.iftm.charles.sistemasorveteria.model;

public class Produto_Ingrediente {
    // Tabela de ligação: Receita
    private Produto produto;
    private Ingrediente ingrediente;
    private double quantidadeUsada; // No banco: quantidade_usada

    public Produto_Ingrediente() {
    }

    public Produto_Ingrediente(Produto produto, Ingrediente ingrediente, double quantidadeUsada) {
        this.produto = produto;
        this.ingrediente = ingrediente;
        this.quantidadeUsada = quantidadeUsada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public double getQuantidadeUsada() {
        return quantidadeUsada;
    }

    public void setQuantidadeUsada(double quantidadeUsada) {
        this.quantidadeUsada = quantidadeUsada;
    }
}