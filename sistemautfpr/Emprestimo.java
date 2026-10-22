package sistemautfpr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;

public class Emprestimo {
    private Item item;
    private Pessoa pessoa;
    private int quantidade;
    private LocalDate dataInicia;
    private LocalDate dataFinal;

    public Emprestimo(Item item, Pessoa pessoa, int quantidade) {
        this.item = item;
        this.pessoa = pessoa;
        this.quantidade = quantidade;
        
        this.dataInicia = LocalDate.now();
        
        this.dataFinal = dataInicia.plusDays(item.getPrazoEmprestimo());
        }

    // Valida e realiza o empréstimo direto na classe
    public boolean realizarEmprestimo() {
    	
        if (pessoa.isBloqueio()) {
            System.out.println("Erro: Você está bloqueado.");
            return false;
        }
        
        if (!item.pegaEstoque(quantidade)) {
            System.out.println("Erro: Quantidade indisponível no estoque.");
            return false;
        }
        
        
        System.out.println("Empréstimo realizado com sucesso!");
        return true;
    }

    public String fichaRecibo() {
        return pessoa.getNome() + " emprestou " + quantidade + " unidade(s) de " + item.getNomeItemString();
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