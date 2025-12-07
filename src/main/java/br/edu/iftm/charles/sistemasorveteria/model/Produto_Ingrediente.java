package br.edu.iftm.charles.sistemasorveteria.model;

/**
 *
 * @author charl
 */
public class Produto_Ingrediente {
    private double quantidade_usada;
    
    private Produto produto;
    private Ingrediente ingrediente;

    public Produto_Ingrediente() {
    }

    public Produto_Ingrediente(double quantidade_usada, Produto produto, Ingrediente ingrediente) {
        this.quantidade_usada = quantidade_usada;
        this.produto = produto;
        this.ingrediente = ingrediente;
    }

    public double getQuantidade_usada() {
        return quantidade_usada;
    }

    public void setQuantidade_usada(double quantidade_usada) {
        this.quantidade_usada = quantidade_usada;
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
}
