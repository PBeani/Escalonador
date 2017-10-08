package escalonador;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class ListaDeProntos {

    final List<Processo> listaProntos = new ArrayList<Processo>();
    public boolean ordernarNome = true;
    PrintWriter escreverLog;

    public void ordenaListaProntos() {
        Collections.sort(listaProntos, new Comparator<Processo>() {

            public int compare(Processo o1, Processo o2) {
                Processo p1 = (Processo) o1;
                Processo p2 = (Processo) o2;

                //se o credito de p1 for maior, ele deve ser o primeiro da lista
                if (p1.getCredito() > p2.getCredito()) {
                    return -1;
                }

                //se o credito de p1 for menor, p2 deve continuar na frente
                if (p1.getCredito() < p2.getCredito()) {
                    return 1;
                }

                //se os creditos forem iguais, tratar os casos
                if (p1.getCredito() == p2.getCredito()) {
                    //prioridades iguais quando inicia-se o programa
                    if (ordernarNome == true) {
                        if (p1.bcp.getNomeArquivo().compareToIgnoreCase(p2.bcp.getNomeArquivo()) < 0) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }

                    //credito empata, mas a prioridade do que estava antes era maior
                    if (p1.bcp.getPrioridade() > p2.bcp.getPrioridade()) {
                        return -1;
                    }
                    if (p2.bcp.getPrioridade() < p2.bcp.getPrioridade()) {
                        return 1;
                    }
                    
                    //se credito e prioridade empatam, sera comparado a ordem alfabetica novamente
                    if (p1.bcp.getPrioridade() == p2.bcp.getPrioridade() && p1.bcp.getNome().compareToIgnoreCase(p2.bcp.getNome()) < 0) {
                        return -1;
                    } else {
                        return -1;
                    }
                }
                return 0;

            }
        });
    }

    public void inserirListaProntos(Processo processo) {
        // se nao tiver ninguem pra comparar, so adiciona
        if (listaProntos.isEmpty()) {
        	listaProntos.add(processo);
        } else {
            for (int i = 0; i < listaProntos.size(); i++) {
                Processo temp = listaProntos.get(i);
                // se o processo atual tiver menos credito, adiciona o novo no lugar dele
                if (temp.getCredito() < processo.getCredito()) {
                	listaProntos.add(i, processo);
                    break;
                }

                // se tiver o mesmo credito, insere o que acabou de executar na frente
                if (temp.getCredito() == processo.getCredito()) {
                	if(temp.isExecutouAgora()==true) {
                		continue;
                	}
                	else{
                		listaProntos.add(i, processo);
                        break;
                	}
                }
                //se tiver nenhum outro processo com um credito menor do que estou inserindo
                if(i==listaProntos.size()-1) {
                	listaProntos.add(processo);
                    break;
                }
            }
        }
    }
    
    public void atualizarStatus() {
    	for (Processo processo : listaProntos) {
            if(processo.isExecutouAgora()==true) {
            	processo.setExecutouAgora(false);
            	continue;
            }
        }
    }

    public Processo removerListaProntos() {
        if (listaProntos.isEmpty()) {
            return null;
        } else {
            return listaProntos.remove(0);
        }
    }

    public void imprimeLista() {
        for (Processo processo : listaProntos) {
            System.out.println(processo.bcp.getNome());
        }
    }
    
    public List<Processo> getList() {
        return listaProntos;
    }
}
