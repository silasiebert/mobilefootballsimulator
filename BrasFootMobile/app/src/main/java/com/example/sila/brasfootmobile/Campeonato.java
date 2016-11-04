package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeonato);
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        tvAndamento= (TextView) findViewById(R.id.tvAndamento);
        tvClubes= (TextView) findViewById(R.id.tvClubes);
        tvGols= (TextView) findViewById(R.id.tvGols);
        tvLucro= (TextView) findViewById(R.id.tvLucro);
    }

    public void rodarPartida(View v){
        //recuperar os clubes do banco
        ArrayList<Clube>clubes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM clube",null);
        int indiceColunaId = cursor.getColumnIndex("clubeId");
        int indiceColunaNome = cursor.getColumnIndex("nome");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            clubes.add(new Clube(cursor.getInt(indiceColunaId),cursor.getString(indiceColunaNome)));
            cursor.moveToNext();
        }

        ArrayList<Jogador>jogadores;
        for(Clube c:clubes) {
            jogadores = new ArrayList<>();
            Cursor cur = db.rawQuery("SELECT * FROM jogador WHERE clubeId = "+c.getClubeId(),null);
            int indiceColunaHabilidade = cur.getColumnIndex("habilidade");
            int indiceNomeJogador = cur.getColumnIndex("nome");
            int indiceColunaPos = cur.getColumnIndex("posicao");
            int indiceColunaCond = cur.getColumnIndex("condicionamento");
            int indiceColunaMot = cur.getColumnIndex("motivacao");
            int indiceColunaJogando = cur.getColumnIndex("jogando");
            cur.moveToFirst();
            while(!cur.isAfterLast()) {
                jogadores.add(new Jogador(cur.getInt(indiceColunaHabilidade),cur.getString(indiceColunaNome),cur.getInt(indiceColunaPos),cur.getInt(indiceColunaCond),cur.getInt(indiceColunaMot),cur.getInt(indiceColunaJogando)!=0));
                cur.moveToNext();
            }
            c.setJogadores(jogadores);
        }



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
        Clube b = new Clube("Botafogo",botafogo,new Estadio(1,"1",1,1,1));
        Clube f = new Clube("Flamengo",flamengo,new Estadio(1,"1",1,1,2));
        Jogo j = null;
        for (Clube local:clubes)
            for (Clube visitante:clubes){
                if (local!=visitante){
                    j= new Jogo(visitante,local);
                }
            }



        tvClubes.setText(b.getNome()+"x"+f.getNome());
        tvAndamento.setText(j.resultado());
//        tvLucro.setText(j.calcularLucro()+"");
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
