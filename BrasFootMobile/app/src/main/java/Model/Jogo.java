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

    public Jogo(Clube visitante, Clube local) {
        this.visitante = visitante;
        this.local = local;
        this.golsLocal=0;
        this.golsVisitante=0;
    }
    public double calcularLucro(){
        Estadio e = local.getEstadio();
        return e.getCapacidade()*e.getPrecoEntrada();
    }
    public String resultado (){
        Random generator = new Random();
        int chance, generated;
        String jogadas= "";
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
            for(int jogada=1; jogada<20; jogada++)
            {

                if (posBola==3){
                    chance = 25 + (local.getForca(Jogador.MEIOCAMPO)-visitante.getForca(Jogador.MEIOCAMPO));
                    generated = generator.nextInt(101);
                    //local dominou
                    if(generated<=chance){
                        posBola--;
                        jogadas+="\n"+local.getNome()+" domina a bola e avanca contra o "+visitante.getNome();
                    }
                    //visitante dominou
                    else{
                        jogadas+="\n"+visitante.getNome()+" domina a bola e avanca contra o "+local.getNome();
                        posBola++;}

                }else if (posBola==2){
                    chance = 25 + (local.getForca(Jogador.ATACANTE)-visitante.getForca(Jogador.DEFENSOR));
                    generated = generator.nextInt(101);
                    //local dominou
                    if(generated<=chance){
                        posBola--;
                        jogadas+="\n"+local.getNome()+" domina a bola e avanca contra o "+visitante.getNome();
                    }
                    //visitante dominou
                    else{
                        jogadas+="\n"+visitante.getNome()+" domina a bola e avanca contra o "+local.getNome();
                        posBola++;}
                }else if (posBola==4){
                    chance = 25 + (local.getForca(Jogador.DEFENSOR)-visitante.getForca(Jogador.ATACANTE));
                    generated = generator.nextInt(101);
                    //local dominou
                    if(generated<=chance){
                        posBola--;
                        jogadas+="\n"+local.getNome()+" domina a bola e avanca contra o "+visitante.getNome();
                    }
                    //visitante dominou
                    else{
                        jogadas+="\n"+visitante.getNome()+" domina a bola e avanca contra o "+local.getNome();
                        posBola++;}
                }else if (posBola==1){
                    chance = 25 + (local.getForca(Jogador.ATACANTE)-visitante.getForca(Jogador.GOLEIRO));
                    generated = generator.nextInt(101);
                    //local fez gol
                    if(generated<=chance){
                        posBola=3;
                            this.golsLocal++;
                        jogadas+="\n"+local.getNome()+" faz gol contra o "+visitante.getNome()+"\n A bola volta pro meio campo.";}
                        //visitante dominou
                    else
                        posBola++;
                }
                else if (posBola==5){
                    chance = 25 + (local.getForca(Jogador.GOLEIRO)-visitante.getForca(Jogador.ATACANTE));
                    generated = generator.nextInt(101);
                    //local fez gol
                    if(generated<=chance){
                        posBola--;
                        }
                    //visitante dominou
                    else{
                        jogadas+="\n"+visitante.getNome()+" faz gol contra o "+local.getNome()+"\n A bola volta pro meio campo.";}
                        posBola=3;
                    this.golsVisitante++;}
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
}