package sistemautfpr;

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
}
