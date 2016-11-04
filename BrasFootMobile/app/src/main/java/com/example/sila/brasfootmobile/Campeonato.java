package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import Model.Clube;
import Model.Estadio;
import Model.Jogador;
import Model.Jogo;
import Model.Liga;

public class Campeonato extends AppCompatActivity {
    private TextView tvClubes,tvAndamento,tvGols,tvLucro;
    private SQLiteDatabase db;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
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
        SharedPreferences s = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        int fase = s.getInt("fase",0);
        String meuClube = s.getString("clube","");
        Clube meu = null;
        for (Clube cc :clubes){
            if(cc.getNome().equals(meuClube)){
                meu = cc;
            }
        }

        Liga campeonato = new Liga();
        Jogo j = null;
        //Collections.shuffle(clubes);
        Clube local = clubes.get(fase);
            for (Clube visitante:clubes){
                if (local!=visitante){

                    j= new Jogo(visitante,local);
                    campeonato.adicionar(j);
                    if(local==meu||visitante==meu){
                        tvClubes.setText(visitante.getNome()+"x"+local.getNome());
                        tvAndamento.setText(j.resultado());
                        tvGols.setText(j.getGolsLocal()+"x"+j.getGolsVisitante());
                    }

                }
            }
        fase++;
        s.edit().putInt("fase",fase).commit();
       Toast.makeText(getApplicationContext(),Integer.toString(fase),Toast.LENGTH_LONG);



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
