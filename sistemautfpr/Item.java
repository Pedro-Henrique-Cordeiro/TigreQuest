package sistemautfpr;


public abstract class Item{
		private String nomeItemString;
		
		public String getNomeItemString() {
			return nomeItemString;
		}
		public void setNomeItemString(String nomeItemString) {
			this.nomeItemString = nomeItemString;
		}

		private int numItem;//numeros de itens dispoiveis para a class
		private int prazoEmprestimo;//prazo em dias ate o vencimento da entrega
		private int dataInicial;//data do emprestimo do item 
		private int dataEntrega;//data da entrega do item 
		
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
		public int getDataInicial() {
			return dataInicial;
		}
		public void setDataInicial(int dataInicial) {
			this.dataInicial = dataInicial;
		}
		public int getDataEntrega() {
			return dataEntrega;
		}
		public void setDataEntrega(int dataEntrega) {
			this.dataEntrega = dataEntrega;
		}
		
		
		public Item(int numItem, int prazoEmprestimo, int dataInicial, int dataEntrega) {
			super();
			this.numItem = numItem;
			this.prazoEmprestimo = prazoEmprestimo;
			this.dataInicial = dataInicial;
			this.dataEntrega = dataEntrega;
		
		}
		
		abstract String regraString();
		
		public void inicializarRegras() {
	        this.regraString();
	    }
		
		//verifica o numero de itens solicitado e se ha no estoque 
		public boolean disponivel(int numItemSol){
			if(getNumItem() >= numItemSol ){
				return true;
			}
			return false;
		}//disponivel
		
		public boolean pegaEstoque(int numRetirado) {
			if(disponivel(numRetirado)) {
				setNumItem(getNumItem()-numRetirado);
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

class Bola extends Item{

	public Bola(int numItem, int prazoEmprestimo, int dataInicial, int dataEntrega) {
		super(numItem, prazoEmprestimo, dataInicial, dataEntrega);
	}
	
	
	@Override
	String regraString() {
		// TODO Auto-generated method stub
		setPrazoEmprestimo(1);
		return "podem ficar com a pessoa até o fim do dia";
		
		
		
	}
}

class Coletes extends Item{

	public Coletes(int numItem, int prazoEmprestimo, int dataInicial, int dataEntrega) {
		super(numItem, prazoEmprestimo, dataInicial, dataEntrega);
	}
	
	@Override
	String regraString() {
		// TODO Auto-generated method stub
		setPrazoEmprestimo(1);
		return "podem ficar com a pessoa até o fim do dia";
	}
	
}


class  RaqueteTenis extends Item{

	public RaqueteTenis(int numItem, int prazoEmprestimo, int dataInicial, int dataEntrega) {
		super(numItem, prazoEmprestimo, dataInicial, dataEntrega);
	}
	
	@Override
	String regraString() {
		// TODO Auto-generated method stub
		setPrazoEmprestimo(2);
		return "podem ficar com a pessoa até dois dias";
	}
}

class Bicicleta extends Item{

	private int pneu;
	private int freio;
	private int corrente;
	public int getPneu() {
		return pneu;
	}

	public void setPneu(int pneu) {
		this.pneu = pneu;
	}

	public int getFreio() {
		return freio;
	}

	public void setFreio(int freio) {
		this.freio = freio;
	}

	public int getCorrente() {
		return corrente;
	}

	public void setCorrente(int corrente) {
		this.corrente = corrente;
	}

	
	
	public Bicicleta(int numItem, int prazoEmprestimo, int dataInicial, int dataEntrega, int pneu, int freio,
			int corrente) {
		super(numItem, prazoEmprestimo, dataInicial, dataEntrega);
		this.pneu = pneu;
		this.freio = freio;
		this.corrente = corrente;
	}

	@Override
	String regraString() {
		// TODO Auto-generated method stub
		setPrazoEmprestimo(7);
		return "pode ficar com uma pessoa até uma semana e necessitam de vistoria na devolução";
	}
	
	public boolean vistoria(int minimoAceitavel){
		if(		getPneu() >= minimoAceitavel &&
				getFreio() >= minimoAceitavel &&
				getCorrente() >= minimoAceitavel) {
			return true;
		}
		return false;
		
	}
	
}