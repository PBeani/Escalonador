package epso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GerenciadorArquivos {

    public static Arquivo[] carregarArquivos(String diretorio) {
        // lista que terá os arquivos que serão executados
        Arquivo[] lista = new Arquivo[10];
        // carrega a pasta que contém os arquivos
        File pasta = new File(diretorio);
        File s = new File("E:\\Usp\\Sistemas Operacionais\\processos\\prioridades.txt");
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
                // laço que vai salvar as linhas do programa no array
                int j = 0;
                while ((linha = leitor.readLine()) != null) {
                    conteudo[j] = linha;
                    j++;
                }

                // cria o arquivo e salva na lista que será usada pelo escalonador
                lista[i] = new Arquivo(conteudo, j - 1);
            }
            
            
        } catch (IOException e) {
            System.out.println("Erro ao carregar os arquivos");
        }

        return lista;
    }
}
