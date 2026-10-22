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
    
    private boolean bloqueio;

    public Pessoa(boolean bloqueio, String cpf, String nome) {
        this.bloqueio = bloqueio;
        this.setCpf(cpf);
        this.nome = nome;
    }
    
    //getters

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
   
    public boolean isBloqueio() { return bloqueio; }

    
    //setters 
    public void setCpf(String cpf) {
        if (cpf.length() != 14) {
            throw new IllegalArgumentException("\nErro no CPF\n");
        }
        this.cpf = cpf;
    }

    public void setNome(String nome) { this.nome = nome; }
    public void setBloqueio(boolean bloqueio) { this.bloqueio = bloqueio; }

    public abstract int limite_itens();

    //função q se bloqueia ate x dias 
   
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

