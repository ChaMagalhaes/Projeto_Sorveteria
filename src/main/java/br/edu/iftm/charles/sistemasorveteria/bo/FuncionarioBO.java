package br.edu.iftm.charles.sistemasorveteria.bo;

import br.edu.iftm.charles.sistemasorveteria.dao.FuncionarioDAO;
import br.edu.iftm.charles.sistemasorveteria.model.Funcionario;
import java.util.List;

public class FuncionarioBO {
    
    private final FuncionarioDAO dao;
    
    public FuncionarioBO() {
        this.dao = new FuncionarioDAO();
    }
    
    public boolean salvar(Funcionario f) {
        if (validar(f)) {
            // Verifica duplicidade de CPF
            if (dao.buscarPorCpf(f.getCpf()) != null) {
                System.out.println("ERRO: CPF já cadastrado para outro funcionário.");
                return false;
            }
            dao.salvar(f);
            return true;
        }
        return false;
    }
    
    public List<Funcionario> listarTodos() {
        return dao.listarTodos();
    }
    
    public Funcionario buscarPorCpf(String cpf) {
        return dao.buscarPorCpf(cpf);
    }
    
    public Funcionario buscarPorId(int id) {
        return dao.buscarPorId(id);
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