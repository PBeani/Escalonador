package escalonador;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import escalonador.BCP.estadoDoProcesso;

public class Escalonador {

    Arquivo[] listaArquivos;
    int[] prioridades;
    int quantum;
    int tempoEspera;
    TabelaDeProcessos tabelaProcessos;
    ListaDeProntos listaProntos;
    ListaDeBloqueados listaBloqueados;
    File logfile;
    PrintWriter escreverLog;
    FileWriter logWriter;
    Logfile log;
    

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
        log = new Logfile();
        // t de 2 processos  executarem + t do turno que ele entrou
        tempoEspera = 3;

    }

    private void criarLogfile(String diretorio) {
        try {
            String nomeQuantum = "log".concat(Integer.toString(quantum)).concat(".txt");
            logfile = new File(diretorio, nomeQuantum);
            escreverLog = new PrintWriter(logfile);
        } catch (IOException e) {
            System.out.println("Erro ao criar o logfile");
        }
    }

    private void carregarTabelaElistas() {
        for (int i = 0; i < 10; i++) {
            int prioridade = prioridades[i];
            BCP bcp = new BCP(listaArquivos[i], prioridade);
            Processo processo = new Processo(bcp, prioridade);
            tabelaProcessos.inserirProcesso(processo);
            listaProntos.inserirListaProntos(processo);
            listaProntos.ordenaListaProntos();
        }
        for (Processo processo : listaProntos.getList()) {
            escreverLog.println("Carregando " + processo.bcp.getNome());
        }
        listaProntos.ordernarNome = false;
    }

    // salva o estado atual no bcp
    private void salvarExecucao(BCP bcp, int pc, int x, int y) {
        bcp.setContadorDePrograma(pc);
        bcp.setX(x);
        bcp.setY(y);
    }

    private void bloquearProcesso(Processo p) {
        p.bcp.setEstado(estadoDoProcesso.BLOQUEADO);
        p.setEspera(tempoEspera);
        listaBloqueados.inserirlistaBloqueados(p);
    }

    private void finalizarProcesso(Processo p) {
        p.bcp.setEstado(estadoDoProcesso.BLOQUEADO);
        log.atualizarMediaTrocas(p.bcp.getNumTrocas());
        tabelaProcessos.removeTabelaProcessos(p);
    }

    private void redistribuirPrioridades() {
        listaProntos.ordernarNome = true;
        tabelaProcessos.atribuirCreditos();
        listaProntos.ordenaListaProntos();
        listaProntos.ordernarNome = false;
    }

    private void executarProcesso(Processo p) {

        // escreve no log o processo atual que esta sendo executado
        escreverLog.printf("Executando: ");
        escreverLog.println(p.bcp.getNome());
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

        while (i < quantum && bcp.getEstado() == estadoDoProcesso.EXECUTANDO && tabelaProcessos.getTabelaProcesso().contains(p)) {

            // carrega o comando que sera executado
            String comando = segmentoTexto[pc];
            // atualiza o pc
            pc++;
            // verfica qual e a instrucao atual e realiza a operacao correspondente

            switch (comando) {
                case "E/S":
                    salvarExecucao(bcp, pc, x, y);
                    bloquearProcesso(p);
                    escreverLog.printf("E/S iniciada em ");
                    escreverLog.println(p.bcp.getNome());

                    escreverLog.printf("Interrompendo ");
                    escreverLog.printf(p.bcp.getNome());
                    escreverLog.printf(" apos ");
                    escreverLog.print(i + 1);
                    escreverLog.printf(" instrucoes");
                    escreverLog.println("");

                    log.atualizarMediaInstrucoes(i + 1);
                    p.bcp.setNumTrocas(p.bcp.getNumTrocas() + 1);
                    break;
                case "COM":
                    break;
                case "SAIDA":
                    salvarExecucao(bcp, pc, x, y);
                    finalizarProcesso(p);
                    escreverLog.printf(p.bcp.getNome());
                    escreverLog.printf(" terminado. X=");
                    escreverLog.print(p.bcp.getX());
                    escreverLog.printf(" e Y=");
                    escreverLog.print(p.bcp.getY());
                    escreverLog.println("");

                    log.atualizarMediaInstrucoes(i + 1);
                    p.bcp.setNumTrocas(p.bcp.getNumTrocas() + 1);
                    break;
                // como o numero de operacoes e limitado, se nao for nenhuma das listadas acima,
                // sera a de atribuicao
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
        if (i == quantum && bcp.getEstado() == estadoDoProcesso.EXECUTANDO) {
            salvarExecucao(bcp, pc, x, y);
            listaProntos.inserirListaProntos(p);
            p.setExecutouAgora(true);
            escreverLog.printf("Interrompendo ");
            escreverLog.printf(p.bcp.getNome());
            escreverLog.printf(" apos ");
            escreverLog.print(i);
            escreverLog.printf(" instrucoes");
            escreverLog.println("");
            log.atualizarMediaInstrucoes(i);
            p.bcp.setNumTrocas(p.bcp.getNumTrocas() + 1);

        }

    }

    private void rodarEscalonador() {
        // fazer o funcionamento
        while (!tabelaProcessos.getTabelaProcesso().isEmpty()) {
            listaBloqueados.atualizarListaBloqueados(listaProntos);// incrementar na contagem do tempo
            listaProntos.atualizarStatus();
            if (tabelaProcessos.redistribuirCreditos()) {
                redistribuirPrioridades();
            }
            Processo atual = listaProntos.removerListaProntos();
            if (atual != null) { // existe elemento na lista de pronto
                atual.bcp.setEstado(estadoDoProcesso.EXECUTANDO);
                executarProcesso(atual);
            }

        }
        // rodou todos os processos
        double mediaInstrucoes = log.fazerMediaInstrucao();
        double mediaTrocas = log.fazerMediaTrocas();
        escreverLog.printf("MEDIA DE TROCAS: ");
    	escreverLog.println(mediaTrocas);
    	escreverLog.printf("MEDIA DE INSTRUCOES: ");
    	escreverLog.println(mediaInstrucoes);
    	escreverLog.printf("QUANTUM: ");
    	escreverLog.println(quantum);
        escreverLog.close();
        }

    public static void main(String[] args) {
        // caminho para a pasta que contem os arquvios que serao usados no escalonamento
        String diretorio = "C:\\Users\\amand_000\\Documents\\USP\\SO\\EP1\\processos";
        // cria o escalonador

        Escalonador escalonador = new Escalonador(diretorio);
        escalonador.criarLogfile(diretorio);
        escalonador.carregarTabelaElistas();
        escalonador.rodarEscalonador();
            }

}
