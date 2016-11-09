package Model;


import java.util.Random;

public class Jogo {
    public static final int VISITANTE = 1;
    public static final int LOCAL = 2;
    public static final int EMPATE = 3;

    private Clube visitante;
    private Clube local;
    private int golsLocal;
    private int golsVisitante;
    private double lucro;
    private int vencedor;
    private int posBola;

    public Jogo(Clube visitante, Clube local, int golsLocal, int golsVisitante, int vencedor) {
        this.visitante = visitante;
        this.local = local;
        this.golsLocal = golsLocal;
        this.golsVisitante = golsVisitante;
        this.vencedor = vencedor;
    }

    public Jogo(Clube visitante, Clube local) {
        this.visitante = visitante;
        this.local = local;
        this.golsLocal = 0;
        this.golsVisitante = 0;
    }

    public double calcularLucro() {
        Estadio e = local.getEstadio();
        return e.getCapacidade() * e.getPrecoEntrada();
    }

    public String resultado() {
        int chanceVisitante, chanceLocal;
        String jogadas = "";
        // meio campo comeca em 3
        //visitante ganha -1
        //local ganha +1
        // 4 = defesa visitante
        // 2 = defesa local
        // 5 = gol do local
        // 1 = gol do visitante
        posBola = 3;


        // gol local 5
        //defesa local 4
        //meio campo 3
        //defesa visitante 2
        //gol visitante 1
        for (int jogada = 1; jogada < 20; jogada++) {

            if (posBola == 3) {
                chanceLocal = (int) (local.getForca(Jogador.MEIOCAMPO)+Math.random()*25);
                chanceVisitante = (int) (visitante.getForca(Jogador.MEIOCAMPO)+Math.random()*25);
                //local dominou
                if (chanceLocal >= chanceVisitante) {
                    posBola--;
                    jogadas += "\n" + local.getNome() + " domina a bola no meio campo e avanca contra o " + visitante.getNome();
                }
                //visitante dominou
                else {
                    jogadas += "\n" + visitante.getNome() + " domina a bola meio campo e avanca contra o " + local.getNome();
                    posBola++;
                }

            } else if (posBola == 2) {
                chanceVisitante = (int) (visitante.getForca(Jogador.DEFENSOR)+Math.random()*25);
                chanceLocal = (int) (local.getForca(Jogador.ATACANTE)+Math.random()*25);
                //local dominou
                if (chanceLocal >= chanceVisitante) {
                    posBola--;
                    jogadas += "\nOs atacantes do " + local.getNome() + " roubam a bola dos defensores do " + visitante.getNome()+" e avancam pro gol.";
                }
                //visitante dominou
                else {
                    jogadas += "\nOs defensores do " + visitante.getNome() + " recuperam a bola dos atacantes do " + local.getNome();
                    posBola++;
                }
            } else if (posBola == 4) {
                chanceLocal = (int) (local.getForca(Jogador.DEFENSOR)+Math.random()*25);
                chanceVisitante = (int) (visitante.getForca(Jogador.ATACANTE)+Math.random()*25);
                //local dominou
                if (chanceLocal >= chanceVisitante) {
                    posBola--;
                    jogadas += "\nOs defensores do \" " + local.getNome() + " recuperam a bola dos atacantes do " + visitante.getNome();
                }
                //visitante dominou
                else {
                    jogadas += "\nOs atacantes do " + visitante.getNome() + " roubam a bola dos defensores do " + local.getNome()+" e avancam pro gol.";
                    posBola++;
                }
            } else if (posBola == 1) {
                chanceVisitante = (int) (visitante.getForca(Jogador.GOLEIRO)+Math.random()*25);
                chanceLocal = (int) (local.getForca(Jogador.ATACANTE)+Math.random()*25);
                //local fez gol
                if (chanceLocal >= chanceVisitante) {
                    posBola = 3;
                    this.golsLocal++;
                    jogadas += "\n" + local.getNome() + " faz gol contra o " + visitante.getNome();
                }
                //visitante dominou
                else
                    posBola++;
            } else if (posBola == 5) {
                chanceLocal = (int) (local.getForca(Jogador.GOLEIRO)+Math.random()*25);
                chanceVisitante = (int) (visitante.getForca(Jogador.ATACANTE)+Math.random()*25);
                //local fez gol
                if (chanceLocal >= chanceVisitante) {
                    posBola--;
                }
                //visitante dominou
                else {
                    jogadas += "\n" + visitante.getNome() + " faz gol contra o " + local.getNome();
                    posBola = 3;
                    this.golsVisitante++;
                }

            }

        }
        if (golsLocal > golsVisitante) {
            this.vencedor = LOCAL;
        } else if (golsVisitante > golsLocal) {
            this.vencedor = VISITANTE;
        } else {
            this.vencedor = EMPATE;
        }

        return jogadas;
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

    @Override
    public String toString() {
        return visitante.getNome() +
                " X " + local.getNome();
    }
}