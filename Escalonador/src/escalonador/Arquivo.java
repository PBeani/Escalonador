package escalonador;

public class Arquivo {
    public String nome;
    public String[] instrucoes;
    public String nomeArquivo;
    
    public Arquivo(String nome, String[] conteudo, int nComandos) {
        nomeArquivo = nome;
        this.nome = conteudo[0];
        instrucoes = new String[nComandos];
        // salva os comandos lidos na lista de comandos do objeto daquele arquvio
        for(int i = 0; i < instrucoes.length; i++) {
            instrucoes[i] = conteudo[i+1];
        }
    }
}
