package Model;

/**
 * Created by sila on 14/10/16.
 */

public class Jogador {
    public static final int DEFENSOR = 1;
    public static final int MEIOCAMPO = 2;
    public static final int ATACANTE = 3;

    private String nome;
    private String sobrenome;


    //Atributos do jogador
    private int forca;




    private int posicao;

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public static int getDEFENSOR() {
        return DEFENSOR;
    }

    public static int getMEIOCAMO() {
        return MEIOCAMPO;
    }

    public static int getATACANTE() {
        return ATACANTE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }
}
