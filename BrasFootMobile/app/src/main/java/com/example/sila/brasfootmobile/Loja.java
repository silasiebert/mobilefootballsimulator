package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Model.*;

public class Loja extends AppCompatActivity {
    private Button btComprar;
    private TextView tvJogadores;
    private String mostraJogadores;
    private  ArrayList<Jogador> jogadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);
        tvJogadores = (TextView) findViewById(R.id.tvJogadoresLoja);
        btComprar = (Button) findViewById(R.id.btComprar);

        gerarJogadores();



    }

    public void gerarJogadores() {


        //Gerador de 20 jogadores aleatorios para cada clube
        // int numeroAletorio =(int)(Math.random()*30)+20;
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Saraiva", Jogador.GOLEIRO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Leonardo da Vinci", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Caleu", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Neymar", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Tinhão", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Galileu", Jogador.DEFENSOR, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Anitta", Jogador.DEFENSOR, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Pelé", Jogador.DEFENSOR, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Bartolomeu", Jogador.MEIOCAMPO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Picasso", Jogador.MEIOCAMPO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));
            jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Simone e Simara", Jogador.MEIOCAMPO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false));

        String imp="";
        for (Jogador j: jogadores){
            imp+= "\n" + j.getNome() + j.getCondicionamento() + j.getHabilidade() + j.getPosicao();
            imp = imp + "\n";
        }
        tvJogadores.setText(imp);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuInicio:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
            case R.id.menuTime:
                startActivity(new Intent(getApplicationContext(), Time.class));
                finish();
                break;
            case R.id.menuLoja:
                startActivity(new Intent(getApplicationContext(), Loja.class));
                finish();
                break;
            case R.id.menuCampeonato:
                startActivity(new Intent(getApplicationContext(), Campeonato.class));
                finish();
                break;
            case R.id.menuEstadio:
                startActivity(new Intent(getApplicationContext(), Model.Estadio.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
