package epso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Escalonador {

	public static void main(String[] args) {
		Arquivo[] listaArquivos;
		// chama o método que vai listar os arquivos que serão executados
		String diretorio = "E:\\Usp\\Sistemas Operacionais\\processos";
		listaArquivos = GerenciadorArquivos.carregarArquivos(diretorio);
		File prioridades = new File(
				"E:\\Usp\\Sistemas Operacionais\\processos\\prioridades.txt");
		File quantum = new File(
				"E:\\Usp\\Sistemas Operacionais\\processos\\quantum.txt");
		List<Processo> tabelaDeProcessos = new ArrayList<Processo>();
		List<BCP> listaBcp = listaBcp(listaArquivos, prioridades);

	}

	public static int quantum (File quantum){
		try{
			BufferedReader leitor = new BufferedReader(new FileReader(
				quantum));
			return Integer.parseInt(leitor.readLine());
		}catch(IOException e){
			System.out.println("Erro ao carregar o quantum");
		}
		return 0;
	}

	public static List<BCP> listaBcp(Arquivo[] listaArquivos, File prioridades) {
		try {
			List<BCP> bcp = new LinkedList<BCP>();
			BufferedReader leitor = new BufferedReader(new FileReader(
					prioridades));
			for (int i = 0; i < listaArquivos.length; i++) {
				String linha = leitor.readLine();
				int prioridade = Integer.parseInt(linha);
				bcp.add(new BCP(listaArquivos[i], prioridade));
			}
			return bcp;
		} catch (IOException e) {
			System.out.println("Erro ao carregar a prioridade");
		}
		return null;
	}
}
