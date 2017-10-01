package epso;

public class BCP {

	String nomePrograma;
	int linhaRodando;
	int contadorDePrograma;
	int prioridade;
	boolean es;
	boolean usandoX;
	boolean usandoY;
	String[] instruçoes;

	enum estadoDoProcesso {
		EXECUTANDO, PRONTO, BLOQUEADO;
	}

	public BCP(Arquivo arquivo, int prioridade) {
		nomePrograma = arquivo.nome;
		this.prioridade = prioridade;
		this.instruçoes = arquivo.instrucoes;
	}

	public int getContadorDePrograma() {
		return contadorDePrograma;
	}

	public void setContadorDePrograma(int contadorDePrograma) {
		this.contadorDePrograma = contadorDePrograma;
	}

	public int getLinhaRodando() {
		return linhaRodando;
	}

	public void setLinhaRodando(int linhaRodando) {
		this.linhaRodando = linhaRodando;
	}

	public boolean isEs() {
		return es;
	}

	public void setEs(boolean es) {
		this.es = es;
	}

	public boolean isUsandoX() {
		return usandoX;
	}

	public void setUsandoX(boolean usandoX) {
		this.usandoX = usandoX;
	}

	public boolean isUsandoY() {
		return usandoY;
	}

	public void setUsandoY(boolean usandoY) {
		this.usandoY = usandoY;
	}

}
