package escalonador;

public class Processo {

    BCP bcp;
    private int credito;
    private int espera;

    public Processo(BCP bcp, int credito) {
        this.bcp = bcp;
        this.credito = credito;
        espera = 0;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getEspera() {
        return espera;
    }

    public void setEspera(int espera) {
        this.espera = espera;
    }
}
