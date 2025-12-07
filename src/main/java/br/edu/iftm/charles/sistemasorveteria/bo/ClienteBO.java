package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.ClienteDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import br.edu.iftm.charles.sistemasorveteria.model.Item_Venda;
import java.util.List;

public class ClienteBO {
    
    private final ClienteDAO dao;
    
    public ClienteBO() {
        this.dao = new ClienteDAO();
    }
    
    public boolean salvar(Cliente cliente) {
        if (validar(cliente)) {
            if (dao.buscarPorDocumento(cliente.getDocumento()) != null) {
                System.out.println("ERRO: Já existe um cliente cadastrado com este CPF/Documento.");
                return false;
            }
            dao.salvar(cliente);
            return true;
        }
        return false;
    }
    
    public boolean atualizar(Cliente cliente) {
        if (validar(cliente)) {
            dao.atualizar(cliente);
            return true;
        }
        return false;
    }
    
    public boolean excluir(Cliente cliente) {
        if (cliente == null || cliente.getId_cliente() <= 0) {
            System.out.println("ERRO: Selecione um cliente válido.");
            return false;
        }
        
        List<Item_Venda> historico = dao.listarHistoricoCompras(cliente.getId_cliente());
        if (!historico.isEmpty()) {
            System.out.println("ERRO: Não é possível excluir este cliente pois ele possui histórico de compras.");
            return false;
        }
        
        dao.excluir(cliente.getId_cliente());
        return true;
    }
    
    public List<Cliente> listarTodos() {
        return dao.listarTodos();
    }
    
    public List<Cliente> buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }
    
    private boolean validar(Cliente c) {
        if (c.getNome() == null || c.getNome().trim().isEmpty()) {
            System.out.println("ERRO: Nome do cliente é obrigatório.");
            return false;
        }
        if (c.getDocumento() == null || c.getDocumento().trim().isEmpty()) {
            System.out.println("ERRO: CPF/Documento é obrigatório.");
            return false;
        }
        return true;
    }
}