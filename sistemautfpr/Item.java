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