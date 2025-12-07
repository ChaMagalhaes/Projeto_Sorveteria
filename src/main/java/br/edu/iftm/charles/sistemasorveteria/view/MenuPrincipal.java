package br.edu.iftm.charles.sistemasorveteria.view;

import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import java.util.Scanner;
import java.util.List;

public class MenuPrincipal {
    
    private static Scanner scanner = new Scanner(System.in);
    
    // --- INSTANCIANDO OS CONTROLLERS (BOs) ---
    // Eles ficam aqui como static para manterem os dados salvos enquanto o menu roda
    private static SorveteBO sorveteBO = new SorveteBO();
    private static ClienteBO clienteBO = new ClienteBO();
    private static FuncionarioBO funcionarioBO = new FuncionarioBO();

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n=========================================");
            System.out.println("      SISTEMA DE GESTÃO - SORVETERIA     ");
            System.out.println("=========================================");
            System.out.println("--- CADASTROS ---");
            System.out.println("1 - Cadastrar Sorvete");
            System.out.println("2 - Cadastrar Cliente");
            System.out.println("3 - Cadastrar Funcionário");
            System.out.println("-----------------");
            System.out.println("--- CONSULTAS ---");
            System.out.println("5 - Consultar Estoque (Sorvetes)");
            System.out.println("6 - Consultar Clientes");
            System.out.println("7 - Consultar Funcionários");
            System.out.println("-----------------");
            System.out.println("0 - Sair");
            System.out.println("=========================================");
            System.out.print("Digite a opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarSorvete(); break;
                case 2: cadastrarCliente(); break;
                case 3: cadastrarFuncionario(); break;
                
                // Pulei o 4 (Venda) para focar no que você pediu
                
                case 5: consultarSorvetes(); break;
                case 6: consultarClientes(); break;
                case 7: consultarFuncionarios(); break;
                
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
        scanner.close();
    }

    // --- MÉTODOS DE CADASTRO ---

    private static void cadastrarSorvete() {
        System.out.println("\n--- NOVO SORVETE ---");
        System.out.print("Sabor: ");
        String sabor = scanner.nextLine();
        System.out.print("Tipo (Massa/Picolé): ");
        String tipo = scanner.nextLine();
        System.out.print("Preço (R$): ");
        double preco = lerDouble();
        System.out.print("Quantidade: ");
        int qtd = lerInteiro();

        // Cria o objeto e salva no BO
        Sorvete s = new Sorvete(sabor, tipo, preco, qtd);
        sorveteBO.salvar(s);
        
        System.out.println(">> Sucesso: Sorvete salvo!");
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- NOVO CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String fone = scanner.nextLine();

        // Cria o objeto e salva no BO
        Cliente c = new Cliente(nome, cpf, fone);
        clienteBO.salvar(c);

        System.out.println(">> Sucesso: Cliente salvo!");
    }

    private static void cadastrarFuncionario() {
        System.out.println("\n--- NOVO FUNCIONÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário (R$): ");
        double salario = lerDouble();

        // Cria o objeto e salva no BO
        Funcionario f = new Funcionario(nome, cargo, salario);
        funcionarioBO.salvar(f);

        System.out.println(">> Sucesso: Funcionário salvo!");
    }

    // --- MÉTODOS DE CONSULTA ---

    private static void consultarSorvetes() {
        System.out.println("\n--- LISTA DE SORVETES ---");
        List<Produto> lista = sorveteBO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum sorvete cadastrado.");
        } else {
            System.out.println("Sabor | Tipo | Preço | Qtd");
            for (Sorvete s : lista) {
                System.out.printf("%s | %s | R$ %.2f | %d\n", 
                    s.getNome(), s.getTipo(), s.getPreco(), s.getQuantidade());
            }
        }
    }

    private static void consultarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        List<Cliente> lista = clienteBO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : lista) {
                System.out.println("Nome: " + c.getNome() + " | CPF: " + c.getCpf());
            }
        }
    }

    private static void consultarFuncionarios() {
        System.out.println("\n--- LISTA DE FUNCIONÁRIOS ---");
        List<Funcionario> lista = funcionarioBO.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f : lista) {
                System.out.println("Nome: " + f.getNome() + " | Cargo: " + f.getCargo() + " | Salário: " + f.getSalario());
            }
        }
    }

    // --- UTILITÁRIOS ---

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite novamente: ");
            }
        }
    }

    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número inteiro: ");
            }
        }
    }
}