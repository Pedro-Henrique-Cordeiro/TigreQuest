package sistemautfpr;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTES DO SISTEMA UTFPR ===\n");

        // 1. Criando usuários (CPF precisa ter exatamente 14 caracteres por causa da sua validação)
        try {
            Aluno aluno1 = new Aluno(false, "123.456.789-01", "Carlos Silva");
            Servidor servidor1 = new Servidor(false, "987.654.321-02", "Ana Souza");

            // 2. Criando itens
            Bola bola = new Bola(5, 0, 10, 10);
            Bicicleta bicicleta = new Bicicleta(2, 0, 10, 17, 8, 9, 10);

            // Inicializando as regras dos itens (prazo, etc.)
            bola.inicializarRegras();
            bicicleta.inicializarRegras();

            System.out.println("-> Usuários e itens criados com sucesso!\n");

            // 3. Testando disponibilidade e empréstimo para o Aluno
            if (bola.disponivel(1)) {
                // Criando o empréstimo (passando a pessoa e o nome do item)
                Emprestimo emp1 = new Emprestimo(aluno1, "Bola de Futebol");
                
                // Registrando o empréstimo no histórico do aluno
                aluno1.emprestar(emp1);
                
                // Salvando a ficha no arquivo TXT
                Emprestimo.salvarFicha(emp1, "historico.txt");
            }

            // 4. Testando empréstimo para o Servidor (Bicicleta)
            if (bicicleta.disponivel(1)) {
                // Verificando a vistoria da bicicleta antes
                if (bicicleta.vistoria(5)) {
                    Emprestimo emp2 = new Emprestimo(servidor1, "Bicicleta Caloi");
                    servidor1.emprestar(emp2);
                    Emprestimo.salvarFicha(emp2, "historico.txt");
                } else {
                    System.out.println("Bicicleta reprovada na vistoria!");
                }
            }

            // 5. Lendo o conteúdo do arquivo TXT gravado
            System.out.println("\n=== LENDO O ARQUIVO DE HISTÓRICO (TXT) ===");
            String historicoLido = Emprestimo.lerFichas("historico.txt");
            System.out.print(historicoLido);

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Erro durante a execução: " + e.getMessage());
        }
    }
}