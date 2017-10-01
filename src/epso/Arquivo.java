package epso;

public class Arquivo {
    public String nome;
    public String[] instrucoes;
    
    public Arquivo(String[] conteudo, int nComandos) {
        nome = conteudo[0];
        instrucoes = new String[nComandos];
        // salva os comandos lidos na lista de comandos do objeto daquele arquvio
        for(int i = 0; i < instrucoes.length; i++) {
            instrucoes[i] = conteudo[i+1];
        }
    }
}
