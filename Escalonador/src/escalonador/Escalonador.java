package escalonador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Escalonador {

    Arquivo[] listaArquivos;
    int[] prioridades;
    int quantum;
    TabelaDeProcessos tabelaProcessos;

    public Escalonador(String diretorio) {
        // constrói a lista que contém os arquivos que irão gerar os processos
        listaArquivos = GerenciadorArquivos.carregarArquivos(diretorio);
        // carrega a lista que contém as prioridades de cada processo
        prioridades = GerenciadorArquivos.carregarPrioridades(diretorio);
        // carrega o valor do quantum que será usado no escalonamento
        quantum = GerenciadorArquivos.carregarQuantum(diretorio);
        tabelaProcessos = new TabelaDeProcessos();
    }

    private void carregarTabelaProcessos() {
        for (int i = 0; i < 10; i++) {
            int prioridade = prioridades[i];
            BCP bcp = new BCP(listaArquivos[i], prioridade);
            Processo processo = new Processo(bcp, prioridade);
            tabelaProcessos.inserirProcesso(processo);
        }
    }

    private void executarProcesso(Processo p) {
        // carrega o bcp do processo, para trazer suas informações para a memória
        BCP bcp = p.bcp;
        // carrega o segmento de texto, que contém as instruções do programa
        String[] segmentoTexto = bcp.getSegmentoDeTexto();
        // carrega o pc do programa
        int pc = bcp.getContadorDePrograma();
        // carrega o valor dos registradores
        int x = bcp.getX();
        int y = bcp.getY();
        // o número de comandos até o limite dado pelo quantum, mas pode ser interrompido
        for (int i = 0; i < this.quantum; i++) {
            // carrega o comando que será executado
            String comando = segmentoTexto[pc];
            // atualiza o pc
            // ver se é melhor fazer isso antes ou depois de executar, acho que antes é melhor por causa de e/s
            pc++;
            // verfica qual é a instrução atual e realiza a operação correspondente
            switch (comando) {
                case "E/S":
                    this.realizarES(p);
                    break;
                case "COM":
                    break;
                case "SAIDA":
                    this.finalizarProcesso(p);
                    break;
                // como o número de operações é limitado, se não for nenhuma das listadas acima, será a de atribuição
                default:
                    // separa o que esta antes do = do que esta depois
                    String[] atribuicao = comando.split("=");
                    // carrega a variavel que esta sofrendo atribuição
                    String registrador = atribuicao[0];
                    // carrega o valor que será atribuido
                    int valor = Integer.parseInt(atribuicao[1]);
                    // verifica qual variavel receberá o valor e realiza a atribuição
                    if (registrador.equals("X")) {
                        x = valor;
                    } else {
                        y = valor;
                    }
                    break;
            }
        // ver o que precisa ser feito ao final de cada instrução
        }
        //salva o bcp
        //finalizar execução
    }

    public static void main(String[] args) {
        // caminho para a pasta que contém os arquvios que serão usados no escalonamento
        String diretorio = "E:\\Usp\\Sistemas Operacionais\\processos";
        // cria o escalonador
        Escalonador escalonador = new Escalonador(diretorio);
        escalonador.carregarTabelaProcessos();

    }

}
