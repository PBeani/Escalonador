package escalonador;

import java.io.PrintWriter;

public class Logfile {
	
    private int mediaTrocas;
    private int mediaInstrucoes;
    private int totalTrocas;
    private int totalInstrucoes;
	PrintWriter escreverLog;
	
	public void atualizarMediaTrocas(int x) {
		mediaTrocas += x;
		totalTrocas ++;
	}
	
	public void atualizarMediaInstrucoes(int i) {
		mediaInstrucoes += i;
		totalInstrucoes ++;
	}
  	
    public void fazerMedias() {
    	mediaTrocas = mediaTrocas/totalTrocas;
    	mediaInstrucoes = mediaInstrucoes/totalInstrucoes;
 	
		escreverLog.printf("MEDIA DE TROCAS: %i /n", mediaTrocas);
		escreverLog.printf("MEDIA DE INSTRUCOES: %i /n", mediaInstrucoes);
		escreverLog.printf("QUANTUM %i /n"); //pegar quantum
	    }

	public int getMediaInstrucoes() {
		return mediaInstrucoes;
	}

	public void setMediaInstrucoes(int mediaInstrucoes) {
		this.mediaInstrucoes = mediaInstrucoes;
	}

	public int getMediaTrocas() {
		return mediaTrocas;
	}

	public void setMediaTrocas(int mediaTrocas) {
		this.mediaTrocas = mediaTrocas;
	}
}
