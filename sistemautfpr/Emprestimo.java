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
    
    
    private int quantidade;//quantidade de itens q a pessoa pediu
    private LocalDate dataInicia;//data em q a pessoa emprestou o item
    private LocalDate dataFinal;//ultimo dia q pode ser entregue sem penalidade 
    
    //construtor
    public Emprestimo(Item item, Pessoa pessoa, int quantidade) {
        this.item = item;
        this.pessoa = pessoa;
        this.quantidade = quantidade;
        
        this.dataInicia = LocalDate.now();//pega a data na hora em q estacio a class emprestimo
        
        this.dataFinal = dataInicia.plusDays(item.getPrazoEmprestimo());//pega a data inicia e soma com o tempo(dias)
        																//em q a pessoa pode ficar com o item sem penalidade
        }

    public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if(quantidade > 0) {
			this.quantidade = quantidade;			
		}
	}

	// Valida e realiza o empréstimo direto na classe
    public boolean realizarEmprestimo() {
    	
    	//chama a função q desbloqueia a pesssoa 
    	
        if (pessoa.isBloqueio()) {//verifica se a pessoa ta bloqueada 
            System.out.println("Erro: Você está bloqueado.");
            return false;
        }
        
        if (!item.pegaEstoque(quantidade)) {//verifica se tem item no estoque 
            System.out.println("Erro: Quantidade indisponível no estoque.");
            return false;
        }
        
        
        System.out.println("Empréstimo realizado com sucesso!");
        return true;
    }
    
    //gettes and setters
    public LocalDate getDataInicia() {
		return dataInicia;
	}

	public void setDataInicia(LocalDate dataInicia) {
		
			this.dataInicia = dataInicia;			
		
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		
			this.dataFinal = dataFinal;
					

	}

	public boolean devolucaoEmprestimo() {
        LocalDate dataDevolucaoDate = LocalDate.now();
        
        item.guardaEstoque(quantidade);
        
        // Verifica se a devolução foi feita após o prazo final
        if (dataDevolucaoDate.isAfter(getDataFinal())) {
            pessoa.setBloqueio(true);
        }
        
        // Tira o item do inventário da pessoa
        pessoa.apaga_item(item.getNomeItemString());
        
        return true; // Retorna true indicando sucesso
    }

    public String fichaReciboDevolucao() {
        if (!pessoa.isBloqueio()) {
            return pessoa.getNome() + " devolveu " + quantidade + " unidade(s) de " + item.getNomeItemString();
        } else {
            return pessoa.getNome() + " devolveu " + quantidade + " unidade(s) de " + item.getNomeItemString() + " com atraso (Usuário bloqueado)";
        }
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