package escalonador;

import java.util.LinkedList;

public class TabelaDeProcessos {

    final LinkedList<Processo> listaProcesso = new LinkedList<Processo>();

    public void inserirProcesso(Processo processo) {
        // se nao tiver ninguem pra comparar, so adiciona
        if (listaProcesso.isEmpty()) {
            listaProcesso.add(processo);
        } else {
            for (int i = 0; i < listaProcesso.size(); i++) {
                Processo temp = listaProcesso.get(i);
                // se o processo atual tiver menos prioridade, adiciona o novo no lugar dele
                if (temp.getCredito() < processo.getCredito()) {
                    listaProcesso.add(i, processo);
                    break;
                }

                // se tiver a mesma prioridade, ordena pelo nome
                if (temp.getCredito() == processo.getCredito()) {
                    if (temp.bcp.getNomeArquivo().compareToIgnoreCase(processo.bcp.getNomeArquivo()) > 0) {
                        listaProcesso.add(i, processo);
                        break;
                    }
                }
            }
        }

    }

    public LinkedList<Processo> getTabelaProcesso() {
        return listaProcesso;
    }

    public void removeTabelaProcessos(Processo processo) {
        listaProcesso.remove(processo);
    }

    public LinkedList<Processo> getProcessos() {
        return listaProcesso;
    }

    public void atribuirCreditos() {
        for (Processo processo : listaProcesso) {
            processo.setCredito(processo.bcp.getPrioridade());
        }
    }

    public boolean redistribuirCreditos() {
        boolean resp = true;
        for (Processo processo : listaProcesso) {
            if (processo.getCredito() > 0) {
                resp = false;
                break;
            }
        }
        return resp;
    }

}
