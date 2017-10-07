package escalonador;

import java.util.LinkedList;

public class TabelaDeProcessos {

    final private LinkedList<Processo> listaProcesso = new LinkedList<Processo>();

    public void inserirProcesso(Processo processo) {
        if (listaProcesso.isEmpty()) {
            listaProcesso.add(processo);
        } else {
            int posicao = 0;
            for (Processo temp : listaProcesso) {
                if (temp.getCredito() < processo.getCredito()
                        && posicao < listaProcesso.size() - 1) {
                    listaProcesso.add(posicao, processo);
                    break;
                }
                if (temp.getCredito() == processo.getCredito()
                        && posicao < listaProcesso.size() - 1) {
                    if (temp.bcp.getNome().compareToIgnoreCase(
                            processo.bcp.getNome()) > 0) {
                        posicao++;
                        continue;
                    } else {
                        listaProcesso.add(posicao, processo);
                    }
                }
                if (listaProcesso.size() - 1 == posicao) {
                    listaProcesso.add(processo);
                }
                posicao++;
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
