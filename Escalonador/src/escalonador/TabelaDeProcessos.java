package escalonador;

import java.util.LinkedList;

public class TabelaDeProcessos {
	
	private LinkedList<Processo> listaProcesso = new LinkedList<Processo>();
	
	public void inserirProcesso(Processo processo) {
		int posicao=0;
		for(Processo temp : listaProcesso) {			
			if(temp.credito<processo.credito&&posicao<listaProcesso.size()-1) {
				listaProcesso.add(posicao, processo);
				break;
			}
			else {
				listaProcesso.add(processo);
			}
			posicao++;
		}
		
	}

}
