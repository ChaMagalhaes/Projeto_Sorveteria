package br.edu.iftm.charles.sistemasorveteria.model;

public class Item_Venda {
    // Chave composta no banco (id_venda, id_produto), aqui usamos referências aos objetos
    private Venda venda;
    private Produto produto;
    
    private int quantidade;
    private double precoUnitario; // No banco: preco_unitario
    private double subtotal;

    public Item_Venda() {
    }

    public Item_Venda(Venda venda, Produto produto, int quantidade, double precoUnitario) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = quantidade * precoUnitario;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}