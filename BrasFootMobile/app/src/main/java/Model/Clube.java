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
    private int clubeId;



    private Estadio estadio;
    private String nome;
    private int forca;

    public Clube(int clubeId, String nome) {
        this.clubeId = clubeId;
        this.nome = nome;
    }

    public int getForca(int pos) {
        forca=0;
        if (pos==Jogador.ATACANTE){
            for(Jogador j:atacantes){
                if(j.isJogando())
                forca+=j.getCondicionamento()*j.getHabilidade()*(j.getMotivacao()/100);
            }
            forca=forca/atacantes.size();
            }else if(pos==Jogador.DEFENSOR){
            for(Jogador j:defensores){
                if(j.isJogando())
                forca+=j.getCondicionamento()*j.getHabilidade()*(j.getMotivacao()/100);
            }
            forca=forca/defensores.size();
            }else if(pos==Jogador.MEIOCAMPO){
            for(Jogador j:meiocampos){
                if(j.isJogando())
                forca+=j.getCondicionamento()*j.getHabilidade()*(j.getMotivacao()/100);
            }
            forca=forca/meiocampos.size();
            }else if(pos==Jogador.GOLEIRO){
            for(Jogador j:goleiros){
                if(j.isJogando())
                forca+=j.getCondicionamento()*j.getHabilidade()*(j.getMotivacao()/100);
            }
            forca=forca/goleiros.size();
        }
        return forca;
    }
    public void classificarJogadores(){
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

    }
    public void setForca(int forca) {
        this.forca = forca;
    }

    public Clube(String nome) {
        this.nome = nome;
    }

    public Clube(String nome, List<Jogador> jogadores, Estadio e) {
        this.estadio = e;
        this.nome = nome;
        this.jogadores = jogadores;


    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
        classificarJogadores();
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

    public int getClubeId() {
        return clubeId;
    }

    public void setClubeId(int clubeId) {
        this.clubeId = clubeId;
    }
}
