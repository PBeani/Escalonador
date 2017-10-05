package escalonador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GerenciadorArquivos {

    public static Arquivo[] carregarArquivos(String diretorio) {
        // lista que tera os arquivos que serao executados
        Arquivo[] lista = new Arquivo[10];
        // carrega a pasta que contem os arquivos
        File pasta = new File(diretorio);
        // gera uma lista com os arquivos existentes na pasta
        File[] arquivos = pasta.listFiles();

        try {
            // itera sobre os arquivos, lendo linha por linha
            for (int i = 0; i < 10; i++) {
                // arquivo que esta sendo lido no momento
                File arquivo = arquivos[i];
                // array que vai guardar as linhas do arquivo
                String[] conteudo = new String[22];

                BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
                String linha;
                // laco que vai salvar as linhas do programa no array
                int j = 0;
                while ((linha = leitor.readLine()) != null) {
                    conteudo[j] = linha;
                    j++;
                }

                // cria o arquivo e salva na lista que sera usada pelo escalonador
                lista[i] = new Arquivo(conteudo, j - 1);
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar os arquivos");
        }
        return lista;
    }

    public static int[] carregarPrioridades(String diretorio) {
        // array que ira armazenar a prioridade dos arquivos na ordem de entrada
        int[] prioridades = new int[10];
        // atualiza o endereço do arquivo que contem as prioridades
        diretorio = diretorio + "\\prioridades.txt";
        // carrega o arquivo de prioridades
        File prioridade = new File(diretorio);

        try {
            BufferedReader leitor = new BufferedReader(new FileReader(prioridade));
            String linha;

            int i = 0;
            while ((linha = leitor.readLine()) != null) {
                prioridades[i] = Integer.parseInt(linha);
                i++;
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao carregar os arquivos");
        }
        return prioridades;
    }

    public static int carregarQuantum(String diretorio) {
        // atualiza o endereco do arquivo que contem o quantum
        diretorio = diretorio + "\\quantum.txt";
        // carrega o arquivo de quantum
        File quantumFile = new File(diretorio);
        // irá guargar o valor do quantum
        int quantum = 0;
        
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(quantumFile));
            String linha;

            linha = leitor.readLine();
            quantum = Integer.parseInt(linha);
        } catch (IOException e) {
            System.out.println("Erro ao carregar os arquivos");
        }
        
        return quantum;
    }
}
