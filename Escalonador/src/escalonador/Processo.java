package escalonador;

public class Processo {

    BCP bcp;
    int credito;
    int espera;

    public Processo(BCP bcp, int credito) {
        this.bcp = bcp;
        this.credito = credito;
        espera = 0;
    }

}
