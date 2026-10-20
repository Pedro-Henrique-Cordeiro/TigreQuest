package sistemautfpr;

public class Main {
    public static void main(String[] args) {
        try {
            Aluno aluno1 = new Aluno(false, "123.456.789-01", "Carlos Silva");
            Bola bola = new Bola(5, 0, 10, 10);
            bola.inicializarRegras();

            if (bola.disponivel(1)) {
                Emprestimo emp = new Emprestimo(aluno1, "Bola de Futebol");
                aluno1.emprestar(emp);
                
                Emprestimo.salvarFicha(emp, "historico.txt");
                
                System.out.println("\n--- Lendo do Arquivo ---");
                System.out.print(Emprestimo.lerFichas("historico.txt"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}