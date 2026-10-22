package sistemautfpr;

import java.time.*;

abstract class Pessoa {

    private String cpf;
    private String nome;
    private int quant_itens_carregnd;
    private String nome_itens_carregnd;
    private boolean bloqueio;


//construtor
    public Pessoa(boolean bloqueio, String cpf, String nome, int quant_itens_carregnd, String nome_itens_carregnd) {
        this.bloqueio = bloqueio;
        this.setCpf(cpf);
        this.nome = nome;
        this.quant_itens_carregnd = quant_itens_carregnd;
        this.nome_itens_carregnd = nome_itens_carregnd;
    }

//construtor para main
        public Pessoa(String nome) {
        this.bloqueio = false;
        this.cpf = "000.000.000-00";
        this.nome = nome;
        this.quant_itens_carregnd = 0;
        this.nome_itens_carregnd = "";
    }
    
    //getters
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public boolean isBloqueio() { return bloqueio; }
    public int getQuant_itens_carregnd() { return quant_itens_carregnd; }
    public String getNome_itens_carregnd() { return nome_itens_carregnd; }

    
    //setters 
    public void setCpf(String cpf) {
        if (cpf.length() != 14) {
            throw new IllegalArgumentException("\nErro no CPF\n");
        }
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setBloqueio(boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    //encapsulamento, n aceita negativo
    public void setQuant_itens_carregnd(int quant_itens_carregnd) {
        if(quant_itens_carregnd < 0){
            throw new IllegalArgumentException("\nErro quandidade de itens negativa\n");
        }
        this.quant_itens_carregnd = quant_itens_carregnd;
    }

    public void setNome_itens_carregnd(String nome_itens_carregnd) {
        this.nome_itens_carregnd = nome_itens_carregnd;
    }

    //função que as subclasses usarem para determinar o limite de cada uma delas
    public abstract int limite_itens();

    //método que verifica se ela ainda está bloqueada
        //recebe a data do desbloqueio da pessoa
    public void verifica_desbloqueio(LocalDate dia_desbloqueio){
        //variável que recebe a data atual
        LocalDate dia_atual = LocalDate.now();

        //if que verifica se penalidade já terminou e desbloqueia se já
        if(dia_atual.isEqual(dia_desbloqueio) || dia_atual.isAfter(dia_desbloqueio)){
            this.bloqueio = false;
        }
    }

    //método que apaga o item que esta no inventário dela
        //recebe o item que será excluído do inventário
    public void apaga_item(String item_recebido){
        //if verifca se a pessoa tem algum item, e verifica se o item está lá
        if(this.nome_itens_carregnd != null && this.nome_itens_carregnd.contains(item_recebido)){
            
            //se entrou, apaga o item e deixa um espaço vazio
            this.nome_itens_carregnd = this.nome_itens_carregnd.replace(item_recebido, "").trim(); //trim n lê os espaços

            //reduz a quantidade da pessoa em -1
            this.quant_itens_carregnd = this.quant_itens_carregnd - 1;
        }
    }

}

class Aluno extends Pessoa {
    public Aluno(String nome) {
        super(nome);
    }

    @Override
    public int limite_itens() { return 2; }
}

class Servidor extends Pessoa {
    public Servidor(String nome) {
        super(nome);
    }

    @Override
    public int limite_itens() { return 4; }
}