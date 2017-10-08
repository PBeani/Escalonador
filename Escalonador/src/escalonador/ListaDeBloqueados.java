package escalonador;

import java.util.LinkedList;
import escalonador.BCP.estadoDoProcesso;

public class ListaDeBloqueados {
    
    final LinkedList<Processo> listaBloqueados = new LinkedList<Processo>();

    //insere na lista de bloqueados
    public void inserirlistaBloqueados(Processo processo) {
        listaBloqueados.add(processo);
    }

    //remove da lista de bloqueados
    public void removerlistaBloqueados(Processo processo) {
        listaBloqueados.remove(processo);
    }

    //decrementa tempo de espera
    public void atualizarListaBloqueados(ListaDeProntos listaProntos) {
        for (int i = 0; i < listaBloqueados.size(); i++) {
            Processo processo = listaBloqueados.get(i);
            processo.setEspera(processo.getEspera() - 1);

            //se o tempo zerar, o processo eh incluido na lista de prontos
            if (processo.getEspera() == 0) {
                processo.bcp.setEstado(estadoDoProcesso.PRONTO);
                removerlistaBloqueados(processo);
                listaProntos.inserirListaProntos(processo);
                i --;
            }
        }
    }
}
