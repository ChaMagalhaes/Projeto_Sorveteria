package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.CategoriaDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Categoria;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
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

    public boolean atualizar(Categoria categoria) {
        if (validarCategoria(categoria)) {
            if (categoria.getId_categoria() <= 0) {
                System.out.println("ERRO: Categoria não identificada para edição.");
                return false;
            }
            dao.atualizar(categoria);
            return true;
        }
        return false;
    }

    public boolean excluir(Categoria categoria) {
        if (categoria == null || categoria.getId_categoria() <= 0) {
            System.out.println("ERRO: Selecione uma categoria válida para excluir.");
            return false;
        }

        List<Produto> produtosDaCategoria = dao.listarProdutoCategoria(categoria.getId_categoria());
        
        if (!produtosDaCategoria.isEmpty()) {
            System.out.println("ERRO: Não é possível excluir. Existem " + produtosDaCategoria.size() + 
                " produto(s) vinculados a esta categoria.");
            return false;
        }

        dao.excluir(categoria.getId_categoria());
        return true;
    }

    public List<Categoria> listarTodos() {
        return dao.listarTodos();
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