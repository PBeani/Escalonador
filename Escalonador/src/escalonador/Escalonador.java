package escalonador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Escalonador {
    
    Arquivo[] listaArquivos;
    int[] prioridades;
    int quantum;
    TabelaProcessos tabelaProcessos;
    
    public Escalonador(String diretorio) {
        // constrói a lista que contém os arquivos que irão gerar os processos
        listaArquivos = GerenciadorArquivos.carregarArquivos(diretorio);
        // carrega a lista que contém as prioridades de cada processo
        prioridades = GerenciadorArquivos.carregarPrioridades(diretorio);
        // carrega o valor do quantum que será usado no escalonamento
        quantum = GerenciadorArquivos.carregarQuantum(diretorio);
    }
    
    private void carregarTabelaProcessos() {
        for(int i = 0; i < 10; i++) {
            int prioridade = prioridades[i];
            BCP bcp = new BCP(listaArquivos[i], prioridade);
            Processo processo = new Processo(bcp, prioridade);
            tabelaProcessos.inserirProcesso(processo);
        }
    }
    
    public static void main(String[] args) {
        // caminho para a pasta que contém os arquvios que serão usados no escalonamento
        String diretorio = "E:\\Usp\\Sistemas Operacionais\\processos";
        // cria o escalonador
        Escalonador escalonador = new Escalonador(diretorio);
        escalonador.carregarTabelaProcessos();
//        List<Processo> tabelaDeProcessos = new ArrayList<Processo>();
//        List<BCP> listaBcp = GerenciadorArquivos.listaBcp(listaArquivos, prioridades);

    }

}
