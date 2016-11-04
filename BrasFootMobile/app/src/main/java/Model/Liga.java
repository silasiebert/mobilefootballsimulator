package Model;

import java.util.ArrayList;

/**
 * Created by sila on 04/11/16.
 */

public class Liga {

    private  ArrayList<Jogo>jogos = new ArrayList<>();


    public Liga() {

    }

    public ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(ArrayList<Jogo> jogos) {
        this.jogos = jogos;
    }

    public  void adicionar (Jogo j){
        jogos.add(j);
    }
}
