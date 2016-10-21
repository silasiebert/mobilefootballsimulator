package Model;

import java.util.List;

/**
 * Created by sila on 14/10/16.
 */

public class Time {
    private String name;
    private List<Jogador> jogadores;
    private List<Jogador> defensores;
    private List<Jogador> meiocampos;
    private List<Jogador> atacantes;

    private int ataque;
    private int defesa;

    public Time(String nome,List<Jogador> jogadores) {

        this.name = nome;
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

        }

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
    }
}
