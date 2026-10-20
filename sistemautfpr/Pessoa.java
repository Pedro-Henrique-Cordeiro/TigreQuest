package sistemautfpr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Super classe Pessoa
abstract class Pessoa {
    private String cpf;
    private String nome;
    protected List<Emprestimo> historico_emprestimo = new ArrayList<>();
    private boolean bloqueio;

    public Pessoa(boolean bloqueio, String cpf, String nome) {
        this.bloqueio = bloqueio;
        this.setCpf(cpf);
        this.nome = nome;
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public List<Emprestimo> getHistorico_emprestimo() { return historico_emprestimo; }
    public boolean isBloqueio() { return bloqueio; }

    public void setCpf(String cpf) {
        if (cpf.length() != 14) {
            throw new IllegalArgumentException("\nErro no CPF\n");
        }
        this.cpf = cpf;
    }

    public void setNome(String nome) { this.nome = nome; }
    public void setBloqueio(boolean bloqueio) { this.bloqueio = bloqueio; }

    public abstract int limite_itens();

    public void emprestar(Emprestimo novo_emprestimo) {
        if (this.bloqueio) {
            throw new IllegalStateException("\nErro, pessoa bloqueada\n");
        }
        if (this.historico_emprestimo.size() >= this.limite_itens()) {
            throw new IllegalStateException("\nErro, pessoa já tem maximo de itens\n");
        }
        this.historico_emprestimo.add(novo_emprestimo);    
    }

    // Essencial para o recibo imprimir o nome em vez de caracteres estranhos
    @Override
    public String toString() {
        return this.nome;
    }
}

class Aluno extends Pessoa {
    public Aluno(boolean bloqueio, String cpf, String nome) {
        super(bloqueio, cpf, nome);
    }

    @Override
    public int limite_itens() { return 2; }
}

class Servidor extends Pessoa {
    public Servidor(boolean bloqueio, String cpf, String nome) {
        super(bloqueio, cpf, nome);
    }

    @Override
    public int limite_itens() { return 4; }
}

class Emprestimo {
    private Pessoa nome;
    private String nome_item;

    public Emprestimo(Pessoa nome, String nome_item) {
        this.nome = nome;
        this.nome_item = nome_item;
    }

    public Pessoa getNome() { return nome; }
    public String getNome_item() { return nome_item; }
    
    public String fichaRecibo() {
        return getNome() + " Emprestou " + getNome_item();
    }

    public static void salvarFicha(Emprestimo emprestimo, String caminhoArquivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            escritor.write(emprestimo.fichaRecibo());
            escritor.newLine();
            System.out.println("Ficha salva com sucesso no arquivo!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    
    public static String lerFichas(String caminhoArquivo) {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return conteudo.toString();
    }
}