package Model;

/**
 * Created by sila on 14/10/16.
 */

public class Jogador {
    public static final int DEFENSOR = 1;
    public static final int MEIOCAMPO = 2;
    public static final int ATACANTE = 3;
    public static final int GOLEIRO = 4;


    private String nome;
    private int posicao;
    private int habilidade;
    private int condicionamento;
    private int motivacao;

    public Jogador(int habilidade, String nome, int posicao, int condicionamento, int motivacao) {
        this.habilidade = habilidade;
        this.nome = nome;
        this.posicao = posicao;
        this.condicionamento = condicionamento;
        this.motivacao = motivacao;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public int getCondicionamento() {
        return condicionamento;
    }

    public void setCondicionamento(int condicionamento) {
        this.condicionamento = condicionamento;
    }

    public int getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(int motivacao) {
        this.motivacao = motivacao;
    }
}
