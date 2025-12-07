package br.edu.iftm.charles.sistemasorveteria.model;

/**
 *
 * @author charl
 */
public class Produto {
    private int id_produto;
    private String nome;
    private double preco;
    private String descricao;
    
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int id_produto, String nome, double preco, String descricao, Categoria categoria) {
        this.id_produto = id_produto;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
