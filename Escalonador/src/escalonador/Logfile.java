package escalonador;

import java.io.PrintWriter;

public class Logfile {
	
    private int mediaTrocas;
    private double mediaInstrucoes;
    private int totalTrocas;
    private double totalInstrucoes;
	PrintWriter escreverLog;
	
	public void atualizarMediaTrocas(int x) {
		mediaTrocas += x;
		totalTrocas ++;
	}
	
	public void atualizarMediaInstrucoes(int i) {
		mediaInstrucoes += i;
		totalInstrucoes ++;
	}
  	
	public double fazerMediaInstrucao() {
		return  mediaInstrucoes/totalInstrucoes;		
	}
	
	public int fazerMediaTrocas() {
		return mediaTrocas/totalTrocas;
	}

	public double getMediaInstrucoes() {
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
