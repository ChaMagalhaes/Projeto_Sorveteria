package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.ClienteDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import java.util.List;

public class ClienteBO {
    
    private final ClienteDAO dao;
    
    public ClienteBO() {
        this.dao = new ClienteDAO();
    }
    
    public boolean salvar(Cliente cliente) {
        if (validar(cliente)) {
            // Verifica duplicidade de documento (CPF)
            if (dao.buscarPorDocumento(cliente.getDocumento()) != null) {
                System.out.println("ERRO: Já existe um cliente cadastrado com este Documento/CPF.");
                return false;
            }
            dao.salvar(cliente);
            return true;
        }
        return false;
    }
    
    public List<Cliente> listarTodos() {
        return dao.listarTodos();
    }
    
    public Cliente buscarPorDocumento(String doc) {
        return dao.buscarPorDocumento(doc);
    }
    
    public Cliente buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
    
    public boolean atualizar(Cliente cliente) {
        // Reaproveita a validação para garantir que nome e CPF não estão vazios
        if (validar(cliente)) {
            dao.atualizar(cliente);
            return true;
        }
        return false;
    }
    
    private boolean validar(Cliente c) {
        if (c.getNome() == null || c.getNome().trim().isEmpty()) {
            System.out.println("ERRO: Nome do cliente é obrigatório.");
            return false;
        }
        if (c.getDocumento() == null || c.getDocumento().trim().isEmpty()) {
            System.out.println("ERRO: Documento/CPF é obrigatório.");
            return false;
        }
        return true;
    }
}