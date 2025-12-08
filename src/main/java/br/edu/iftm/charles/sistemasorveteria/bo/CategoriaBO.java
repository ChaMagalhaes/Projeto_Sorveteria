package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.CategoriaDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Categoria;
import java.util.List;

public class CategoriaBO {

    private final CategoriaDAO dao;

    public CategoriaBO() {
        this.dao = new CategoriaDAO();
    }

    public boolean salvar(Categoria categoria) {
        if (validarCategoria(categoria)) {
            dao.salvar(categoria);
            return true;
        }
        return false;
    }

    public List<Categoria> listarTodos() {
        return dao.listarTodos();
    }
    
    public Categoria buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    private boolean validarCategoria(Categoria categoria) {
        if (categoria == null) {
            System.out.println("ERRO: Objeto Categoria inválido.");
            return false;
        }
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            System.out.println("ERRO: O nome da categoria é obrigatório.");
            return false;
        }
        return true;
    }
}