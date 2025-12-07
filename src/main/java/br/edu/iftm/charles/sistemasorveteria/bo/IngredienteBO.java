package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.IngredienteDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Ingrediente;
import java.util.List;

public class IngredienteBO {
    
    private final IngredienteDAO dao;
    
    public IngredienteBO() {
        this.dao = new IngredienteDAO();
    }
    
    public boolean salvar(Ingrediente ing) {
        if (validar(ing)) {
            dao.salvar(ing);
            return true;
        }
        return false;
    }
    
    public boolean atualizar(Ingrediente ing) {
        if (validar(ing)) {
            dao.alterar(ing);
            return true;
        }
        return false;
    }
    
    public boolean excluir(Ingrediente ing) {
        if (ing == null || ing.getId_ingrediente() <= 0) {
            System.out.println("ERRO: Selecione um ingrediente.");
            return false;
        }
        dao.excluir(ing.getId_ingrediente());
        return true;
    }
    
    public List<Ingrediente> listarTodos() {
        return dao.listarTodos();
    }
    
    private boolean validar(Ingrediente ing) {
        if (ing.getNome() == null || ing.getNome().trim().isEmpty()) {
            System.out.println("ERRO: Nome do ingrediente é obrigatório.");
            return false;
        }
        if (ing.getEstoque() < 0) {
            System.out.println("ERRO: O estoque não pode ser negativo.");
            return false;
        }
        return true;
    }
}