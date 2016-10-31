package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sila on 14/10/16.
 */

public class Clube {


    private List<Jogador> jogadores = new ArrayList<>();
    private List<Jogador> goleiros= new ArrayList<>();
    private List<Jogador> defensores = new ArrayList<>();
    private List<Jogador> meiocampos = new ArrayList<>();
    private List<Jogador> atacantes = new ArrayList<>();

    private Estadio estadio;
    private String nome;
    private int forca;

    public int getForca(int pos) {
        forca=0;
        if (pos==Jogador.ATACANTE){
            for(Jogador j:atacantes){
                if(j.isJogando())
                forca+=j.getCondicionamento()+j.getHabilidade()+j.getMotivacao();
            }
            forca=forca/atacantes.size();
            }else if(pos==Jogador.DEFENSOR){
            for(Jogador j:defensores){
                if(j.isJogando())
                forca+=j.getCondicionamento()+j.getHabilidade()+j.getMotivacao();
            }
            forca=forca/defensores.size();
            }else if(pos==Jogador.MEIOCAMPO){
            for(Jogador j:meiocampos){
                if(j.isJogando())
                forca+=j.getCondicionamento()+j.getHabilidade()+j.getMotivacao();
            }
            forca=forca/meiocampos.size();
            }else if(pos==Jogador.GOLEIRO){
            for(Jogador j:goleiros){
                if(j.isJogando())
                forca+=j.getCondicionamento()+j.getHabilidade()+j.getMotivacao();
            }
            forca=forca/goleiros.size();
        }
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public Clube(String nome, List<Jogador> jogadores, Estadio e) {
        this.estadio = e;
        this.nome = nome;
        this.jogadores = jogadores;

        for (Jogador j:jogadores
                ) {
            if (j.getPosicao()==Jogador.DEFENSOR){
                defensores.add(j);
            }
            else
            if (j.getPosicao()==Jogador.ATACANTE){
                atacantes.add(j);
            }
            else
            if (j.getPosicao()==Jogador.MEIOCAMPO){
                meiocampos.add(j);
            }
            else
            if(j.getPosicao()==Jogador.GOLEIRO){
                goleiros.add(j);
                }

        }

/*
        for (Jogador j :defensores
                ) {
            defesa+=j.getForca();
        }

        for (Jogador j :meiocampos
                ) {
            defesa+=j.getForca();
            ataque+=j.getForca();
        }

        for (Jogador j :atacantes
                ) {
            ataque+=j.getForca();
        }

        ataque = ataque /6;
        defesa = defesa / 8;
    */
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }
}
