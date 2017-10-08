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
  	
    public void fazerMedias() {
    	mediaTrocas = mediaTrocas/totalTrocas;
    	mediaInstrucoes = mediaInstrucoes/totalInstrucoes;
    	System.out.printf("MEDIA DE TROCAS: %d \n", mediaTrocas);
    	System.out.printf("MEDIA DE INSTRUCOES: %2f \n", mediaInstrucoes);
    	
    	//escreverLog.printf("MEDIA DE TROCAS: ");
    	//escreverLog.println(mediaTrocas);
    	//escreverLog.printf("MEDIA DE INSTRUCOES: ");
    	//escreverLog.println(mediaInstrucoes);
    	//escreverLog.printf("QUANTUM: ");

		//escreverLog.printf("MEDIA DE TROCAS: %d \n", mediaTrocas);
		//escreverLog.printf("MEDIA DE INSTRUCOES: %i /n", mediaInstrucoes);
		//escreverLog.printf("QUANTUM %i /n"); //pegar quantum
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
