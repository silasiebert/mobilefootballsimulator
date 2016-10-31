package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Clube;
import Model.Estadio;
import Model.Jogador;
import Model.Jogo;

public class Campeonato extends AppCompatActivity {
    private TextView tvClubes,tvAndamento,tvGols,tvLucro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeonato);

        tvAndamento= (TextView) findViewById(R.id.tvAndamento);
        tvClubes= (TextView) findViewById(R.id.tvClubes);
        tvGols= (TextView) findViewById(R.id.tvGols);
        tvLucro= (TextView) findViewById(R.id.tvLucro);
    }

    public void rodarPartida(View v){


        ArrayList<Jogador> botafogo= new ArrayList<>();
        ArrayList<Jogador>flamengo= new ArrayList<>();


        int numeroAletorio =(int)Math.random()*100;
        botafogo.add(new Jogador(numeroAletorio,"1",Jogador.GOLEIRO,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"2",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"3",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"4",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"5",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"5",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"7",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"8",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"9",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"10",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
        botafogo.add(new Jogador(numeroAletorio,"11",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));


        flamengo.add(new Jogador(numeroAletorio,"1",Jogador.GOLEIRO,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"2",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"3",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"4",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"5",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"5",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"7",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"8",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"9",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"10",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
        flamengo.add(new Jogador(numeroAletorio,"11",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
        Clube b = new Clube("Botafogo",botafogo,new Estadio(1,"1",1,1));
        Clube f = new Clube("Flamengo",flamengo,new Estadio(1,"1",1,1));
        Jogo j = new Jogo(b,f);

        tvClubes.setText(b.getNome()+"x"+f.getNome());
        tvAndamento.setText(j.resultado());
        tvLucro.setText(j.calcularLucro()+"");
        tvGols.setText(j.getGolsLocal()+"x"+j.getGolsVisitante());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuInicio:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.menuTime:
                startActivity(new Intent(getApplicationContext(), Time.class));
                break;
            case R.id.menuLoja:
                startActivity(new Intent(getApplicationContext(), Loja.class));
                break;
            case R.id.menuCampeonato:
                startActivity(new Intent(getApplicationContext(), Campeonato.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
