package sistemautfpr;

//entt pensei em usar o pradao mvc (modelo, viwer, e controler) pq mexer naquela bombar de swing e muito ruim
//com esse modelo o swing so fica no viwer de uma forma clara *ela fica no controle tbm
//mas do geito q to pensando n vai ficar muito 

//modelo
//vai ficar toda a logica da coisa todas as funçoes tipo emprestar devolver... 

public abstract class Modelo {
	 
	public abstract class Pessoa{
		private String nomeString;
		private int diasAtrasados;
		private int numMaxItens;
		private int itemsEmprestado;//numero atual de itens empretados pela pessoas
		public int getItemsEmprestado() {
			return itemsEmprestado;
		}
		public void setItemsEmprestado(int itemsEmprestado) {
			this.itemsEmprestado = itemsEmprestado;
		}
		public int getNumMaxItens() {
			return numMaxItens;
		}
		public void setNumMaxItens(int numMaxItens) {
			this.numMaxItens = numMaxItens;
		}
		private boolean bloqueada;//se a pessoa ta bloqueada por atraso 
		
		public String getNomeString() {
			return nomeString;
		}
		public void setNomeString(String nomeString) {
			this.nomeString = nomeString;
		}
		public int getDiasAtrasados() {
			return diasAtrasados;
		}
		public void setDiasAtrasados(int diasAtrasados) {
			this.diasAtrasados = diasAtrasados;
		}
		public boolean isBloqueada() {
			return bloqueada;
		}
		public void setBloqueada(boolean bloqueada) {
			this.bloqueada = bloqueada;
		}
		
		
		public Pessoa(String nomeString, int diasAtrasados, int numMaxItens, boolean bloqueada) {
			super();
			this.nomeString = nomeString;
			this.diasAtrasados = diasAtrasados;
			this.numMaxItens = numMaxItens;
			this.bloqueada = false;
			this.itemsEmprestado = 0;
		}
		public boolean emprestimoItem(Item item, int numItemSoli) {
		//verifica se esta disponivel
			if(getNumMaxItens() < getItemsEmprestado())
			if(item.disponivel(numItemSoli) && !isBloqueada()) {
				
				item.setNumItem(item.getNumItem()-1);//atualiza o numero de itens disponiveis no estoque
				item.setDataInicial(11111);//pensar em akgo para pegar a data 
				item.setDataEntrega(11111);//pensei em pegar o dia inicia e somar com o prazoEmpresimo 
				//fazer uma variavel q guarda os nomes dos itens emprestados pela pessoa
				//*acho q ela precisa ser um vetor, n sei como faz 
				//fazer essa função guarda do nome aqui 
				
				return true;
				}//if
			
			return false;
		}
		
		
	}

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
		
		//verifica o numero de itens solicitado e se ha no estoque 
		public boolean disponivel(int numItemSol){
			if(getNumItem() >= numItemSol ){
				return true;
			}
			return false;
		}//disponivel
			
	}
	
}//modelo
