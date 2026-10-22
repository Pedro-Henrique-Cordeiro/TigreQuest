package sistemautfpr;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BEM-VINDO AO SISTEMA DE EMPRÉSTIMO UTFPR ===");

        // Instanciando os itens usando classes filhas concretas (fora do loop)
        Item bola = new Bola("Bola de Futebol", 10, 1);
        Item coletes = new Coletes("Coletes Esportivos", 20, 1);
        Item raquete = new RaqueteTenis("Raquete de Tênis", 5, 2);
        Item bicicleta = new Bicicleta("Bicicleta UTFPR", 3, 7, 10, 10, 10);

        int opcao;
        do {
            System.out.println("\n================ MENU ================");
            System.out.println("1. Realizar Empréstimo");
            System.out.println("2. Realizar Devolução");
            System.out.println("3. Ler Fichas de Empréstimo Salvas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o seu nome: ");
                    String nomeUsuario = scanner.nextLine();

                    System.out.println("Qual é o seu vínculo?");
                    System.out.println("1 - Aluno");
                    System.out.println("2 - Professor");
                    System.out.print("Escolha uma opção (1 ou 2): ");
                    int tipoOpcao = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer

                    Pessoa pessoa = null;

                    // Switch para instanciar a subclasse correta de Pessoa
                    switch (tipoOpcao) {
                        case 1:
                            pessoa = new Aluno(nomeUsuario);
                            break;
                        case 2:
                            pessoa = new Servidor(nomeUsuario);
                            break;
                        default:
                            System.out.println("Opção de vínculo inválida! Retornando ao menu.");
                            continue;
                    }

                    // Seleção dos itens
                    System.out.println("\n--- SELECIONE O ITEM ---");
                    System.out.println("1. " + bola.getNomeItemString() + " (Disponíveis: " + bola.getNumItem() + ")");
                    System.out.println("2. " + coletes.getNomeItemString() + " (Disponíveis: " + coletes.getNumItem() + ")");
                    System.out.println("3. " + raquete.getNomeItemString() + " (Disponíveis: " + raquete.getNumItem() + ")");
                    System.out.println("4. " + bicicleta.getNomeItemString() + " (Disponíveis: " + bicicleta.getNumItem() + ")");
                    System.out.print("Escolha o tipo de item: ");
                    int itemOpcao = scanner.nextInt();
                    scanner.nextLine();

                    Item itemSelecionado = null;

                    switch (itemOpcao) {
                        case 1:
                            itemSelecionado = bola;
                            break;
                        case 2:
                            itemSelecionado = coletes;
                            break;
                        case 3:
                            itemSelecionado = raquete;
                            break;
                        case 4:
                            itemSelecionado = bicicleta;
                            break;
                        default:
                            System.out.println("Opção de item inválida! Retornando ao menu.");
                            continue;
                    }

                    System.out.print("Digite a quantidade de itens que deseja emprestar: ");
                    int quantidadeDesejada = scanner.nextInt();
                    scanner.nextLine();

                    Emprestimo emprestimo = new Emprestimo(itemSelecionado, pessoa, quantidadeDesejada);
                    
                    if (emprestimo.realizarEmprestimo()) {
                        Emprestimo.salvarFicha(emprestimo, "fichas_emprestimo.txt");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Processando Devolução ---");
                    System.out.print("Digite o nome da pessoa que está devolvendo: ");
                    String nomeDevolucao = scanner.nextLine();

                    System.out.println("Qual é o vínculo?");
                    System.out.println("1 - Aluno");
                    System.out.println("2 - Professor");
                    int tipoDev = scanner.nextInt();
                    scanner.nextLine();

                    Pessoa pessoaDevolucao = (tipoDev == 2) ? new Servidor(nomeDevolucao) : new Aluno(nomeDevolucao);
                    
                    Emprestimo devolucao = new Emprestimo(bola, pessoaDevolucao, 1);
                    devolucao.devolucaoEmprestimo();
                    System.out.println("Operação de devolução concluída.");
                    break;

                case 3:
                    System.out.println("\n--- HISTÓRICO DE FICHAS SALVAS ---");
                    String conteudoFichas = Emprestimo.lerFichas("fichas_emprestimo.txt");
                    if (conteudoFichas.isEmpty()) {
                        System.out.println("Nenhum registro encontrado no arquivo.");
                    } else {
                        System.out.print(conteudoFichas);
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Digite um número válido do menu.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}