package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.ProdutoDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import java.util.List;

public class ProdutoBO {
    
    private final ProdutoDAO dao;
    
    public ProdutoBO() {
        this.dao = new ProdutoDAO();
    }
    
    public boolean salvar(Produto p) {
        if (validar(p)) {
            dao.salvar(p);
            return true;
        }
        return false;
    }
    
    public boolean atualizar(Produto p) {
        if (validar(p)) {
            dao.atualizar(p);
            return true;
        }
        return false;
    }
    
    public boolean excluir(Produto p) {
        if (p == null || p.getId_produto() <= 0) {
            System.out.println("ERRO: Selecione um produto.");
            return false;
        }
        dao.excluir(p.getId_produto());
        return true;
    }
    
    public List<Produto> listarTodos() {
        return dao.listarTodos();
    }
    
    private boolean validar(Produto p) {
        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            System.out.println("ERRO: Nome do produto é obrigatório.");
            return false;
        }
        if (p.getPreco() <= 0) {
            System.out.println("ERRO: O preço deve ser maior que zero.");
            return false;
        }
        if (p.getCategoria() == null || p.getCategoria().getId_categoria() <= 0) {
            System.out.println("ERRO: Selecione uma categoria para o produto.");
            return false;
        }
        return true;
    }
}