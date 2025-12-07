package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.VendaDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Venda;
import java.util.List;

public class VendaBO {
    
    private final VendaDAO dao;
    
    public VendaBO() {
        this.dao = new VendaDAO();
    }
    
    public boolean salvar(Venda venda) {
        if (validar(venda)) {
            dao.salvar(venda);
            return true;
        }
        return false;
    }
    
    public List<Venda> listarTodos() {
        return dao.listarTodos();
    }
    
    public Venda buscarPorId(int id) {
        Venda v = dao.buscarPorId(id);
        if (v != null) {
            v.setItens(dao.buscarItensPorVenda(id));
        }
        return v;
    }
    
    private boolean validar(Venda v) {
        if (v.getCliente() == null || v.getCliente().getId_cliente() <= 0) {
            System.out.println("ERRO: Selecione um cliente para a venda.");
            return false;
        }
        if (v.getFuncionario() == null || v.getFuncionario().getId_funcionario() <= 0) {
            System.out.println("ERRO: Venda deve estar vinculada a um funcionário.");
            return false;
        }
        if (v.getItens() == null || v.getItens().isEmpty()) {
            System.out.println("ERRO: A venda deve conter pelo menos um item.");
            return false;
        }
        if (v.getTotal() <= 0) {
            System.out.println("ERRO: O valor total da venda deve ser maior que zero.");
            return false;
        }
        return true;
    }
}