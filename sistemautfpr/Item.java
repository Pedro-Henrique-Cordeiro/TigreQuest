package sistemautfpr;

public abstract class Item {
    private String nomeItemString;
    private int numItem;
    private int prazoEmprestimo;
    
    public String getNomeItemString() {
        return nomeItemString;
    }
    public void setNomeItemString(String nomeItemString) {
        this.nomeItemString = nomeItemString;
    }

    public int getNumItem() {
        return numItem;
    }
    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }
    public int getPrazoEmprestimo() {
        return prazoEmprestimo;
    }
    public void setPrazoEmprestimo(int prazoEmprestimo) {
        this.prazoEmprestimo = prazoEmprestimo;
    }
    
    // Construtor principal da classe mãe
    public Item(String nomeItemString, int numItem, int prazoEmprestimo) {
        this.nomeItemString = nomeItemString;
        this.numItem = numItem;
        this.prazoEmprestimo = prazoEmprestimo;
    }
    
    abstract String regraString();
    
    public boolean disponivel(int numItemSol){
        return getNumItem() >= numItemSol;
    }
    
    public boolean pegaEstoque(int numRetirado) {
        if(disponivel(numRetirado)) {
            setNumItem(getNumItem() - numRetirado);
            return true;
        }
        return false;   
    }
    
    public boolean pegaEstoque() {
        return pegaEstoque(1);  
    }
    
    public void guardaEstoque(int numItens) {
        setNumItem(getNumItem() + numItens);
    }
}

class Bola extends Item {
    public Bola(String nomeItemString, int numItem, int prazoEmprestimo) {
        super(nomeItemString, numItem, prazoEmprestimo);
    }
    
    @Override
    String regraString() {
        setPrazoEmprestimo(1);
        return "podem ficar com a pessoa até o fim do dia";
    }
}

class Coletes extends Item {
    public Coletes(String nomeItemString, int numItem, int prazoEmprestimo) {
        super(nomeItemString, numItem, prazoEmprestimo);
    }
    
    @Override
    String regraString() {
        setPrazoEmprestimo(1);
        return "podem ficar com a pessoa até o fim do dia";
    }
}

class RaqueteTenis extends Item {
    public RaqueteTenis(String nomeItemString, int numItem, int prazoEmprestimo) {
        super(nomeItemString, numItem, prazoEmprestimo);
    }
    
    @Override
    String regraString() {
        setPrazoEmprestimo(2);
        return "podem ficar com a pessoa até dois dias";
    }
}

class Bicicleta extends Item {
    private int pneu;
    private int freio;
    private int corrente;

    public int getPneu() { return pneu; }
    public void setPneu(int pneu) { this.pneu = pneu; }
    public int getFreio() { return freio; }
    public void setFreio(int freio) { this.freio = freio; }
    public int getCorrente() { return corrente; }
    public void setCorrente(int corrente) { this.corrente = corrente; }

    public Bicicleta(String nomeItemString, int numItem, int prazoEmprestimo, int pneu, int freio, int corrente) {
        super(nomeItemString, numItem, prazoEmprestimo);
        this.pneu = pneu;
        this.freio = freio;
        this.corrente = corrente;
    }

    @Override
    String regraString() {
        setPrazoEmprestimo(7);
        return "pode ficar com uma pessoa até uma semana e necessitam de vistoria na devolução";
    }
    
    public boolean vistoria(int minimoAceitavel){
        return getPneu() >= minimoAceitavel &&
               getFreio() >= minimoAceitavel &&
               getCorrente() >= minimoAceitavel;
    }
}