package escalonador;

public class BCP {

    private final String nomePrograma;
    private final String[] segmentoDeTexto;
    private final int prioridade;
    private int contadorDePrograma;
    private int registradorX;
    private int registradorY;

    enum estadoDoProcesso {
        EXECUTANDO, PRONTO, BLOQUEADO;
    }

    public BCP(Arquivo arquivo, int p) {
        nomePrograma = arquivo.nome;
        prioridade = p;
        segmentoDeTexto = arquivo.instrucoes;
        contadorDePrograma = 0;
        registradorX = 0;
        registradorY = 0;
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
    
    public void setContadorDePrograma(int contadorDePrograma) {
        this.contadorDePrograma = contadorDePrograma;
    }
    
    public void setX(int x) {
        registradorX = x;
    }
    
    public void setY(int y) {
        registradorY = y;
    }
}
