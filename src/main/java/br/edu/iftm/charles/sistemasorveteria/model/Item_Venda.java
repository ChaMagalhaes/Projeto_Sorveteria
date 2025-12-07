package br.edu.iftm.charles.sistemasorveteria.model;

/**
 *
 * @author charl
 */
public class Item_Venda {
    
    private int id;
    private int quantidade;
    private double valorUnitario; // Alterado de preco_unitario para valorUnitario
    private double subtotal;
    
    private Venda venda;
    private Produto produto;

    public Item_Venda() {
    }

    public Item_Venda(int quantidade, double valorUnitario, Venda venda, Produto produto) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.venda = venda;
        this.produto = produto;
        this.subtotal = quantidade * valorUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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
}