package escalonador;

import java.util.LinkedList;

import escalonador.BCP.estadoDoProcesso;

public class ListaDeBloqueados {
	static final LinkedList<Processo> listaBloqueados = new LinkedList<Processo>();
	ListaDeProntos listaProntos;

	public void inserirlistaBloqueados(Processo processo){		//insere na lista de bloqueados
		listaBloqueados.add(processo);
	}
	
	public void removerlistaBloqueados(Processo processo){		//remove da lista de bloqueados
		listaBloqueados.remove(processo);
	}
	public void atualizaListaBloqueados(){						//decrementa tempo de espera
		for(Processo processo:listaBloqueados){
			processo.setEspera(processo.getEspera()-1);
			if(processo.getEspera()==0){						//se o tempo zerar, o processo eh incluido na lista de prontos
				processo.bcp.setEstado(estadoDoProcesso.PRONTO);	
				removerlistaBloqueados(processo);
				listaProntos.inserirListaProntos(processo);
			}
		}
	}
}
