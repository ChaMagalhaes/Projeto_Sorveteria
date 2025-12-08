package br.edu.iftm.charles.sistemasorveteria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id_venda;
    private LocalDateTime data_venda;
    private String forma_pagamento;
    private String status;
    private double total;
    
    // Relacionamentos
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Item_Venda> itens = new ArrayList<>();

    public Venda() {
    }

    public Venda(int id_venda, LocalDateTime data_venda, String forma_pagamento, String status, double total, Cliente cliente, Funcionario funcionario) {
        this.id_venda = id_venda;
        this.data_venda = data_venda;
        this.forma_pagamento = forma_pagamento;
        this.status = status;
        this.total = total;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public LocalDateTime getData_venda() {
        return data_venda;
    }

    public void setData_venda(LocalDateTime data_venda) {
        this.data_venda = data_venda;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Item_Venda> getItens() {
        return itens;
    }

    public void setItens(List<Item_Venda> itens) {
        this.itens = itens;
    }
    
    public void adicionarItem(Item_Venda item) {
        this.itens.add(item);
        item.setVenda(this); // Garante a referência de volta
    }
}