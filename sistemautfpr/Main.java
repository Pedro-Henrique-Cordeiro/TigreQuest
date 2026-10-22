package sistemautfpr;

public class Main {
    public static void main(String[] args) {
        try {
            Aluno aluno1 = new Aluno(false, "123.456.789-01", "Carlos Silva");
            Bola bola = new Bola(5, 0, 10, 10);
            bola.setNomeItemString("Bola de Futebol");
            bola.inicializarRegras();

            // Cria o objeto empréstimo
            Emprestimo emprestimo = new Emprestimo(bola, aluno1, 1);

            // Tenta realizar e, se der certo, salva no arquivo
            if (emprestimo.realizarEmprestimo()) {
                Emprestimo.salvarFicha(emprestimo, "historico.txt");
            }
            
            // Lendo o histórico do arquivo
            System.out.println("\n--- Lendo do Arquivo ---");
            System.out.print(Emprestimo.lerFichas("historico.txt"));
           
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}