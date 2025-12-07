package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.FuncionarioDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Funcionario;
import br.edu.iftm.charles.sistemasorveteria.model.Venda;
import java.util.List;

public class FuncionarioBO {
    
    private final FuncionarioDAO dao;
    
    public FuncionarioBO() {
        this.dao = new FuncionarioDAO();
    }
    
    public boolean salvar(Funcionario f) {
        if (validar(f)) {
            if (dao.buscaPorCpf(f.getCpf()) != null) {
                System.out.println("ERRO: CPF já cadastrado para outro funcionário.");
                return false;
            }
            dao.salvar(f);
            return true;
        }
        return false;
    }
    
    public boolean atualizar(Funcionario f) {
        if (validar(f)) {
            dao.atualizar(f);
            return true;
        }
        return false;
    }
    
    public boolean excluir(Funcionario f) {
        if (f == null || f.getId_funcionario() <= 0) {
            System.out.println("ERRO: Selecione um funcionário.");
            return false;
        }
        
        List<Venda> vendas = dao.listarVendasFuncionario(f.getId_funcionario());
        if (!vendas.isEmpty()) {
            System.out.println("ERRO: Não é possível excluir: Funcionário possui vendas registradas.");
            return false;
        }
        
        dao.excluir(f.getId_funcionario());
        return true;
    }
    
    public List<Funcionario> listarTodos() {
        return dao.listarTodos();
    }
    
    private boolean validar(Funcionario f) {
        if (f.getNome() == null || f.getNome().trim().isEmpty()) {
            System.out.println("ERRO: Nome é obrigatório.");
            return false;
        }
        if (f.getCpf() == null || f.getCpf().trim().isEmpty()) {
            System.out.println("ERRO: CPF é obrigatório.");
            return false;
        }
        if (f.getSalario() < 0) {
            System.out.println("ERRO: O salário não pode ser negativo.");
            return false;
        }
        return true;
    }
}