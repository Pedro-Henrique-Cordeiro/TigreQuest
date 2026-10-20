package sistemautfpr;


import java.util.ArrayList;
import java.util.List;


//super classe Pessoa, serve como molde para as subclasses: Aluno e Servidor
abstract class Pessoa{

    //cpf para identificação da pessoa
    private String cpf;
    //nome para ajudar no histórico de recibos
    private String nome;
    //recebe "recibo" dos empréstimos
    protected List<Emprestimo> historico_emprestimo = new ArrayList<>();
    //booleana para saber se a pessoa está bloaqueada ou não(proporcionalmente)
    private boolean bloqueio;
    
//construtor
    public Pessoa(boolean bloqueio, String cpf, String nome) {
        this.bloqueio = bloqueio;
        this.setCpf(cpf);
        this.nome = nome;
    }

//getters
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public List<Emprestimo> getHistorico_emprestimo() {
        return historico_emprestimo;
    }

    public boolean isBloqueio() {
        return bloqueio;
    }

//setters

    //encapsulamento
    //CPF aceita apenas 14 dígitos
    public void setCpf(String cpf) {
        if(cpf.length() < 14 || cpf.length() > 14){
            throw new IllegalArgumentException("\nErro no CPF\n");
        }else{
            this.cpf = cpf;
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setBloqueio(boolean bloqueio) {
        this.bloqueio = bloqueio;
    }

    //setHistorico_empresstimo não esta pq ngm pode modificar o histórico, só exibir

    //função que as subclasses usaram para modificar o máximo de itens que cada
    //uma pode receber
    public abstract int limite_itens();

    //função que cria um novo recibo de empréstimo
    public void emprestar(Emprestimo novo_emprestimo){
        //verifica se a pessoa está bloqueada
        if(this.bloqueio == true){
            throw new IllegalStateException("\nErro, pessoa bloqueada\n");
        }

        //verifica se ela já atingiu o máximo de itens
        if(this.historico_emprestimo.size() >= this.limite_itens()){
            throw new IllegalStateException("\nErro, pessoa já tem maximo de itens\n");
        }

        //autocomplete n ta 
        this.historico_emprestimo.add(novo_emprestimo);    
    }

}

//subclasse Aluno
class Aluno extends Pessoa{

//construtor
    public Aluno(boolean bloqueio, String cpf, String nome){
        super(bloqueio, cpf, nome);
    }

//limite de itens individual de aluno
    public int limite_itens(){
        return 2;
    }

}

//subclasse Servidor
class Servidor extends Pessoa{

//construtor
    public Servidor(boolean bloqueio, String cpf, String nome){
        super(bloqueio, cpf, nome);
    }

//limite de itens individual de servidor
    public int limite_itens(){
        return 4;
    }

}

//classe dos recibos dos empréstimos
class Emprestimo{

    //nome da pessoa que irá pegar um item
    private Pessoa nome;
    //nome do item pego
    private String nome_item;

//construtor
    public Emprestimo(Pessoa nome, String nome_item) {
        this.nome = nome;
        this.nome_item = nome_item;
    }

//getters (apenas para ler o recibo)
    public Pessoa getNome() {
        return nome;
    }

    public String getNome_item() {
        return nome_item;
    }

}
