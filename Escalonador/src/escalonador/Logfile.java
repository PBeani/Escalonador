package escalonador;

import java.io.PrintWriter;

public class Logfile {
	
    private double mediaTrocas;
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
	
	public double fazerMediaTrocas() {
		return mediaTrocas/totalTrocas;
	}

	public double getMediaInstrucoes() {
		return mediaInstrucoes;
	}

	public void setMediaInstrucoes(int mediaInstrucoes) {
		this.mediaInstrucoes = mediaInstrucoes;
	}

	public double getMediaTrocas() {
		return mediaTrocas;
	}

	public void setMediaTrocas(int mediaTrocas) {
		this.mediaTrocas = mediaTrocas;
	}
}
