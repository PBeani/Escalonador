package escalonador;

import escalonador.BCP.estadoDoProcesso;

public class Escalonador {

    Arquivo[] listaArquivos;
    int[] prioridades;
    int quantum;
    int tempoEspera;
    TabelaDeProcessos tabelaProcessos;
    ListaDeProntos listaProntos;
    ListaDeBloqueados listaBloqueados;

    public Escalonador(String diretorio) {
        // constroi a lista que contem os arquivos que irao gerar os processos
        listaArquivos = GerenciadorArquivos.carregarArquivos(diretorio);
        // carrega a lista que contem as prioridades de cada processo
        prioridades = GerenciadorArquivos.carregarPrioridades(diretorio);
        // carrega o valor do quantum que sera usado no escalonamento
        quantum = GerenciadorArquivos.carregarQuantum(diretorio);
        tabelaProcessos = new TabelaDeProcessos();
        listaProntos = new ListaDeProntos();
        listaBloqueados = new ListaDeBloqueados();
        tempoEspera = 2;
    }

    private void carregarTabelaProcessos() {
        for (int i = 0; i < 10; i++) {
            int prioridade = prioridades[i];
            BCP bcp = new BCP(listaArquivos[i], prioridade);
            Processo processo = new Processo(bcp, prioridade);
            tabelaProcessos.inserirProcesso(processo);
        }
    }

    // salva o estado atual no bcp
    private void salvarExecucao(BCP bcp, int pc, int x, int y) {
        bcp.setContadorDePrograma(pc);
        bcp.setX(x);
        bcp.setY(y);
        // atualiza a lista de bloqueados, decrementando o tempo de espera
        listaBloqueados.atualizarListaBloqueados(listaProntos);
    }

    private void bloquearProcesso(Processo p) {
        p.bcp.setEstado(estadoDoProcesso.BLOQUEADO);
        p.setEspera(tempoEspera);
        listaBloqueados.inserirlistaBloqueados(p);
    }

    private void finalizarProcesso(Processo p) {
        tabelaProcessos.removeTabelaProcessos(p);
    }

    private void redistribuirPrioridades() {
        tabelaProcessos.atribuirCreditos();
        listaProntos.ordenaListaProntos();
    }
   
    private void executarProcesso(Processo p) {
        p.setCredito(p.getCredito() - 1);
        // carrega o bcp do processo, para trazer suas informacoes para a memoria
        BCP bcp = p.bcp;
        // carrega o segmento de texto, que contem as instrucoes do programa
        String[] segmentoTexto = bcp.getSegmentoDeTexto();
        // carrega o pc do programa
        int pc = bcp.getContadorDePrograma();
        // carrega o valor dos registradores
        int x = bcp.getX();
        int y = bcp.getY();
        // roda o numero de comandos ate o limite dado pelo quantum, mas pode ser interrompido
        int i = 0;
        while (i < quantum && bcp.getEstado() == estadoDoProcesso.EXECUTANDO) {
            // carrega o comando que sera executado
            String comando = segmentoTexto[pc];
            // atualiza o pc
            pc++;
            // verfica qual e a instrução atual e realiza a operacao correspondente
            switch (comando) {
                case "E/S":
                    salvarExecucao(bcp, pc, x, y);
                    bloquearProcesso(p);
                    break;
                case "COM":
                    break;
                case "SAIDA":
                    salvarExecucao(bcp, pc, x, y);
                    finalizarProcesso(p);
                    break;
                // como o numero de operacoes e limitado, se nao for nenhuma das listadas acima, sera a de atribuicao
                default:
                    // separa o que esta antes do = do que esta depois
                    String[] atribuicao = comando.split("=");
                    // carrega a variavel que esta sofrendo atribuicao
                    String registrador = atribuicao[0];
                    // carrega o valor que sera atribuido
                    int valor = Integer.parseInt(atribuicao[1]);
                    // verifica qual variavel recebera o valor e realiza a atribuicao
                    if (registrador.equals("X")) {
                        x = valor;
                    } else {
                        y = valor;
                    }
                    break;
            }
            // ver o que precisa ser feito ao final de cada instrucao
            i++;
        }
        // terminou o ciclo sem executar e/s e sem finalizar a execucao
        if(i == quantum) {
            salvarExecucao(bcp, pc, x, y);
            listaProntos.inserirListaProntos(p);
        }
    }

    private void rodarEscalonador() {
        //fazer o funcionamento 
    }

    public static void main(String[] args) {
        // caminho para a pasta que contem os arquvios que serao usados no escalonamento
        String diretorio = "E:\\Usp\\Sistemas Operacionais\\processos";
        // cria o escalonador
        Escalonador escalonador = new Escalonador(diretorio);
        escalonador.carregarTabelaProcessos();
        escalonador.rodarEscalonador();
    }

}
