package Model;


import java.util.Random;

public class Jogo
{
    private static final int VISITANTE = 1;
    private static final int LOCAL = 2;
    private static final int EMPATE = 3;

    private Clube visitante;
    private Clube local;
    private int golsLocal;
    private int golsVisitante;
    private double lucro;
    private int vencedor;
    private int posBola;

    public void resultado (){
        Random generator = new Random();
        int chance, generated;

            // meio campo comeca em 3
            //visitante ganha -1
            //local ganha +1
            // 4 = defesa visitante
            // 2 = defesa local
            // 5 = gol do local
            // 1 = gol do visitante
            for(int attack=1; attack<7; attack++)
            {


                chance = 25 + (local.getForca()-visitante.getForca());
                generated = generator.nextInt(101);
                if(generated<=chance)
                    result.homeGoal();

                chance = 25 + (away.getAttacking()-home.getDefending());
                generated = generator.nextInt(101);
                if(generated<=chance)
                    result.awayGoal();
            }
    }

    public Clube getVisitante() {
        return visitante;
    }

    public void setVisitante(Clube visitante) {
        this.visitante = visitante;
    }

    public Clube getLocal() {
        return local;
    }

    public void setLocal(Clube local) {
        this.local = local;
    }

    public int getGolsLocal() {
        return golsLocal;
    }

    public void setGolsLocal(int golsLocal) {
        this.golsLocal = golsLocal;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public int getVencedor() {
        return vencedor;
    }

    public void setVencedor(int vencedor) {
        this.vencedor = vencedor;
    }
}