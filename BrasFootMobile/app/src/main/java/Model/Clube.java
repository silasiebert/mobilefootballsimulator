package Model;

import java.util.List;

/**
 * Created by sila on 14/10/16.
 */

public class Clube {


    private List<Jogador> jogadores;
    private List<Jogador> goleiros;
    private List<Jogador> defensores;
    private List<Jogador> meiocampos;
    private List<Jogador> atacantes;

    private Estadio estadio;
   private String nome;
    public Clube(String nome, List<Jogador> jogadores,Estadio e) {
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

}
