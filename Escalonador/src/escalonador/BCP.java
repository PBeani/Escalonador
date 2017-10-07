package escalonador;

public class BCP {

    private final String nomePrograma;
    private final String[] segmentoDeTexto;
    private final int prioridade;
    private int contadorDePrograma;
    private int registradorX;
    private int registradorY;
    private estadoDoProcesso estado;
    private int numTrocas; // calcular o logfile
    
    public enum estadoDoProcesso {
        EXECUTANDO, PRONTO, BLOQUEADO;
    }

    public BCP(Arquivo arquivo, int p) {
        nomePrograma = arquivo.nome;
        prioridade = p;
        segmentoDeTexto = arquivo.instrucoes;
        contadorDePrograma = 0;
        registradorX = 0;
        registradorY = 0;
        estado = estadoDoProcesso.PRONTO;
    }

    public int getContadorDePrograma() {
        return contadorDePrograma;
    }

    public int getX() {
        return registradorX;
    }
    
    public int getY() {
        return registradorY;
    }
    
    public int getPrioridade() {
        return prioridade;
    }
    
    public String getNome() {
        return nomePrograma;
    }
    
    public String[] getSegmentoDeTexto() {
        return segmentoDeTexto;
    }
    
    public estadoDoProcesso getEstado() {
        return estado;
    }
    
    public void setContadorDePrograma(int contadorDePrograma) {
        this.contadorDePrograma = contadorDePrograma;
    }
    
    public void setX(int x) {
        registradorX = x;
    }
    
    public void setY(int y) {
        registradorY = y;
    }
    
    public void setEstado(estadoDoProcesso e) {
        estado = e;
    }

	public int getNumTrocas() {
		return numTrocas;
	}

	public void setNumTrocas(int numTrocas) {
		this.numTrocas = numTrocas;
	}
}
