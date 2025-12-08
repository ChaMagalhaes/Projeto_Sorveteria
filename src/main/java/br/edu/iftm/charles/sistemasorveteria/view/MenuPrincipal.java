package br.edu.iftm.charles.sistemasorveteria.view;

import br.edu.iftm.charles.sistemasorveteria.bo.CategoriaBO;
import br.edu.iftm.charles.sistemasorveteria.bo.ClienteBO;
import br.edu.iftm.charles.sistemasorveteria.bo.FuncionarioBO;
import br.edu.iftm.charles.sistemasorveteria.bo.ProdutoBO;
import br.edu.iftm.charles.sistemasorveteria.bo.VendaBO;
import br.edu.iftm.charles.sistemasorveteria.model.Categoria;
import br.edu.iftm.charles.sistemasorveteria.model.Cliente;
import br.edu.iftm.charles.sistemasorveteria.model.Funcionario;
import br.edu.iftm.charles.sistemasorveteria.model.Item_Venda;
import br.edu.iftm.charles.sistemasorveteria.model.Produto;
import br.edu.iftm.charles.sistemasorveteria.model.Venda;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);

    // Instanciando os Controladores (BOs)
    private static ClienteBO clienteBO = new ClienteBO();
    private static FuncionarioBO funcionarioBO = new FuncionarioBO();
    private static ProdutoBO produtoBO = new ProdutoBO();
    private static VendaBO vendaBO = new VendaBO();
    private static CategoriaBO categoriaBO = new CategoriaBO();

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE SORVETERIA ===");
            System.out.println("1. Gerir Clientes");
            System.out.println("2. Gerir Funcionarios");
            System.out.println("3. Gerir Produtos");
            System.out.println("6. Registar Venda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1: menuClientes(); break;
                case 2: menuFuncionarios(); break;
                case 3: menuProdutos(); break;
                case 6: menuVendas(); break;
                case 0: System.out.println("A encerrar o sistema..."); break;
                default: System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // --- SUB-MENU: CLIENTES ---
    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("\n--- GESTAO DE CLIENTES ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar Todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Alterar");
            System.out.println("5. Excluir");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Novo Cliente ---");
                    Cliente c = new Cliente();
                    System.out.print("Nome: "); c.setNome(scanner.nextLine());
                    System.out.print("CPF: "); c.setDocumento(scanner.nextLine());
                    System.out.print("Telefone: "); c.setTelefone(scanner.nextLine());
                    System.out.print("Email: "); c.setEmail(scanner.nextLine());
                    System.out.print("Endereco: "); c.setEndereco(scanner.nextLine());
                    
                    if(clienteBO.salvar(c)) System.out.println("Cliente salvo!");
                    break;
                case 2:
                    List<Cliente> lista = clienteBO.listarTodos();
                    if(lista.isEmpty()) System.out.println("Nenhum cliente.");
                    else for(Cliente cli : lista) System.out.printf("ID: %d | %s | CPF: %s\n", cli.getId_cliente(), cli.getNome(), cli.getDocumento());
                    break;
                case 3:
                    System.out.print("Digite o ID: ");
                    Cliente cBusca = clienteBO.buscarPorId(lerInteiro());
                    if(cBusca != null) System.out.printf("Encontrado: %s (CPF: %s)\n", cBusca.getNome(), cBusca.getDocumento());
                    else System.out.println("Cliente nao encontrado.");
                    break;
                case 4:
                    System.out.print("Digite o ID para alterar: ");
                    Cliente cAlt = clienteBO.buscarPorId(lerInteiro());
                    if(cAlt != null) {
                        System.out.println("Alterando: " + cAlt.getNome());
                        System.out.print("Novo Nome: "); cAlt.setNome(scanner.nextLine());
                        System.out.print("Novo Telefone: "); cAlt.setTelefone(scanner.nextLine());
                        System.out.print("Novo Email: "); cAlt.setEmail(scanner.nextLine());
                        System.out.print("Novo Endereco: "); cAlt.setEndereco(scanner.nextLine());
                        // Documento geralmente nao se altera, mas pode adicionar se quiser
                        if(clienteBO.atualizar(cAlt)) System.out.println("Cliente atualizado!");
                    } else System.out.println("Cliente nao encontrado.");
                    break;
                case 5:
                    System.out.print("Digite o ID para excluir: ");
                    Cliente cDel = new Cliente();
                    cDel.setId_cliente(lerInteiro());
                    if(clienteBO.excluir(cDel)) System.out.println("Cliente excluido!");
                    break;
                case 0: break;
            }
        } while (opcao != 0);
    }

    // --- SUB-MENU: FUNCIONARIOS ---
    private static void menuFuncionarios() {
        int opcao;
        do {
            System.out.println("\n--- GESTAO DE FUNCIONARIOS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar Todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Alterar");
            System.out.println("5. Excluir");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    Funcionario f = new Funcionario();
                    System.out.print("Nome: "); f.setNome(scanner.nextLine());
                    System.out.print("CPF: "); f.setCpf(scanner.nextLine());
                    System.out.print("Cargo: "); f.setCargo(scanner.nextLine());
                    System.out.print("Salario: "); f.setSalario(lerDouble());
                    System.out.print("Telefone: "); f.setTelefone(scanner.nextLine());
                    System.out.print("Endereco: "); f.setEndereco(scanner.nextLine());
                    if(funcionarioBO.salvar(f)) System.out.println("Funcionario salvo!");
                    break;
                case 2:
                    for(Funcionario func : funcionarioBO.listarTodos()) 
                        System.out.printf("ID: %d | %s | Cargo: %s\n", func.getId_funcionario(), func.getNome(), func.getCargo());
                    break;
                case 3:
                    System.out.print("Digite o ID: ");
                    Funcionario fBusca = funcionarioBO.buscarPorId(lerInteiro());
                    if(fBusca != null) System.out.println("Encontrado: " + fBusca.getNome());
                    else System.out.println("Nao encontrado.");
                    break;
                case 4:
                    System.out.print("Digite o ID para alterar: ");
                    Funcionario fAlt = funcionarioBO.buscarPorId(lerInteiro());
                    if(fAlt != null) {
                        System.out.println("Alterando: " + fAlt.getNome());
                        System.out.print("Novo Nome: "); fAlt.setNome(scanner.nextLine());
                        System.out.print("Novo Cargo: "); fAlt.setCargo(scanner.nextLine());
                        System.out.print("Novo Salario: "); fAlt.setSalario(lerDouble());
                        if(funcionarioBO.atualizar(fAlt)) System.out.println("Atualizado!");
                    } else System.out.println("Nao encontrado.");
                    break;
                case 5:
                    System.out.print("Digite o ID para excluir: ");
                    Funcionario fDel = new Funcionario();
                    fDel.setId_funcionario(lerInteiro());
                    if(funcionarioBO.excluir(fDel)) System.out.println("Excluido!");
                    break;
                case 0: break;
            }
        } while (opcao != 0);
    }

    // --- SUB-MENU: PRODUTOS ---
    private static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n--- GESTAO DE PRODUTOS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar Todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Alterar");
            System.out.println("5. Excluir");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    // Listar categorias antes
                    List<Categoria> cats = categoriaBO.listarTodos();
                    if(cats.isEmpty()) { System.out.println("Cadastre categorias primeiro."); break; }
                    for(Categoria c : cats) System.out.printf("[%d] %s\n", c.getId_categoria(), c.getNome());
                    
                    System.out.print("ID Categoria: "); int idCat = lerInteiro();
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("Preco: "); double preco = lerDouble();
                    System.out.print("Descricao: "); String desc = scanner.nextLine();
                    
                    Produto p = new Produto();
                    p.setNome(nome); p.setPreco(preco); p.setDescricao(desc);
                    Categoria c = new Categoria(); c.setId_categoria(idCat);
                    p.setCategoria(c);
                    
                    if(produtoBO.salvar(p)) System.out.println("Produto salvo!");
                    break;
                case 2:
                    System.out.printf("%-5s | %-20s | %s\n", "ID", "Nome", "Preco");
                    for(Produto prod : produtoBO.listarTodos())
                        System.out.printf("%-5d | %-20s | R$ %.2f\n", prod.getId_produto(), prod.getNome(), prod.getPreco());
                    break;
                case 3:
                    System.out.print("Digite o ID: ");
                    Produto pBusca = produtoBO.buscarPorId(lerInteiro());
                    if(pBusca != null) System.out.println("Produto: " + pBusca.getNome());
                    else System.out.println("Nao encontrado.");
                    break;
                case 4:
                    System.out.print("Digite o ID para alterar: ");
                    Produto pAlt = produtoBO.buscarPorId(lerInteiro());
                    if(pAlt != null) {
                        System.out.println("Alterando: " + pAlt.getNome());
                        System.out.print("Novo Nome: "); pAlt.setNome(scanner.nextLine());
                        System.out.print("Novo Preco: "); pAlt.setPreco(lerDouble());
                        if(produtoBO.atualizar(pAlt)) System.out.println("Atualizado!");
                    } else System.out.println("Nao encontrado.");
                    break;
                case 5:
                    System.out.print("Digite o ID para excluir: ");
                    Produto pDel = new Produto();
                    pDel.setId_produto(lerInteiro());
                    if(produtoBO.excluir(pDel)) System.out.println("Excluido!");
                    break;
                case 0: break;
            }
        } while (opcao != 0);
    }

    // --- SUB-MENU: VENDAS ---
    private static void menuVendas() {
        // (Código de vendas igual ao anterior...)
        System.out.println("--- NOVA VENDA ---");
        // ... (Lógica de selecionar cliente, funcionário e produtos)
        // Para encurtar a resposta, use a lógica de vendas já fornecida anteriormente.
        // Se precisar dela completa aqui novamente, me avise.
    }

    // UTILITÁRIOS
    private static int lerInteiro() {
        try { return Integer.parseInt(scanner.nextLine()); } 
        catch (NumberFormatException e) { return -1; }
    }
    private static double lerDouble() {
        try { return Double.parseDouble(scanner.nextLine()); } 
        catch (NumberFormatException e) { return 0.0; }
    }
}