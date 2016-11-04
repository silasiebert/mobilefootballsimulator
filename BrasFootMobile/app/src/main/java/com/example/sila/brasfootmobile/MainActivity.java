package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Clube;
import Model.Estadio;
import Model.Jogador;
import Model.Jogo;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private TextView tvClubes,tvJogadores;


    public void criarTabelas() {
        db.execSQL("CREATE TABLE IF NOT EXISTS clube (clubeId  INTEGER NOT NULL PRIMARY KEY, forca  INTEGER,nome  TEXT);");
        db.execSQL("CREATE TABLE  IF NOT EXISTS estadio (estadioId  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,capacidade  INTEGER,nome  TEXT, precoEntrada  REAL,precoExpansao  REAL,clubeId  INTEGER NOT NULL,CONSTRAINT  FK_possui_clube  FOREIGN KEY ( clubeId ) REFERENCES  clube  ( clubeId ) ON DELETE No Action ON UPDATE No Action);");
        db.execSQL("CREATE TABLE  IF NOT EXISTS jogador(jogadorId  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,posicao  INTEGER,jogando  INTEGER,motivacao  INTEGER,habilidade  INTEGER,condicionamento  INTEGER,nome  TEXT,clubeId  INTEGER NOT NULL,lojaId  INTEGER,CONSTRAINT  FK_pertence_clube  FOREIGN KEY ( clubeId ) REFERENCES  clube  ( clubeId ) ON DELETE No Action ON UPDATE No Action,CONSTRAINT  FK_vende_jogador  FOREIGN KEY ( lojaId ) REFERENCES  loja  ( lojaId ) ON DELETE No Action ON UPDATE No Action);");
        db.execSQL("CREATE TABLE  IF NOT EXISTS jogo(jogoId  INTEGER NOT NULL PRIMARY KEY,golsLocal  INTEGER, golsVisitante  INTEGER,lucro  REAL,vencedor  INTEGER,estadioId  INTEGER,clubeId  INTEGER,CONSTRAINT  FK_contem_jogo  FOREIGN KEY ( estadioId ) REFERENCES  campeonato  ( estadioId ) ON DELETE No Action ON UPDATE No Action,CONSTRAINT  FK_Visitante_clube  FOREIGN KEY ( clubeId ) REFERENCES  clube  ( clubeId ) ON DELETE No Action ON UPDATE No Action,CONSTRAINT  FK_Local_clube  FOREIGN KEY ( clubeId ) REFERENCES  clube  ( clubeId ) ON DELETE No Action ON UPDATE No Action) ;");
    }

    public void apagarTabelas() {
        //db.execSQL("DROP TABLE campeonato;");
        db.execSQL("DROP TABLE IF EXISTS clube;");
        db.execSQL("DROP TABLE IF EXISTS estadio;");
        db.execSQL("DROP TABLE IF EXISTS jogador;");
        db.execSQL("DROP TABLE IF EXISTS jogo;");
        //db.execSQL("DROP TABLE loja;");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        tvClubes = (TextView) findViewById(R.id.tvClubes);
        tvJogadores= (TextView) findViewById(R.id.tvJogadores);


    }

    public void novoJogo(View v) {
        apagarTabelas();
        criarTabelas();


        gerarJogadores();
    }

    public void gerarJogadores() {
        Estadio estadio = new Estadio(100, "Maracana", 123, 123, 1);
        Clube botafogo = new Clube(1, "Botafogo");
        Clube fluminense = new Clube(2, "Fluminense");


        ArrayList<Clube>clubes = new ArrayList<>();
        clubes.add(botafogo);
        clubes.add(fluminense);

        //Gerador de 20 jogadores aleatorios para cada clube
        ArrayList<Jogador>jogadores;
        for(Clube c:clubes){
            jogadores= new ArrayList<>();
                int numeroAletorio =(int)(Math.random()*30)+20;
                jogadores.add(new Jogador(numeroAletorio,"1",Jogador.GOLEIRO,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"2",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"3",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"4",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"5",Jogador.ATACANTE,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"5",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"7",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"8",Jogador.DEFENSOR,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"9",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"10",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
            jogadores.add(new Jogador(numeroAletorio,"11",Jogador.MEIOCAMPO,numeroAletorio,numeroAletorio,true));
            c.setJogadores(jogadores);

        }


        criarTabelas();
        int jogand =0;
        //Insercao dos clubes e dos jogadores no banco
        for(Clube clube:clubes){
            db.execSQL("INSERT INTO clube (clubeId,nome) VALUES (" + clube.getClubeId() + ", '" + clube.getNome() + "');");
            for (Jogador j :clube.getJogadores()){
                if(j.isJogando()){
                    jogand = 1;
                }
                db.execSQL("INSERT INTO jogador(posicao,jogando,motivacao,habilidade,condicionamento,nome,clubeId)VALUES ("+j.getPosicao()+","+jogand+","+j.getMotivacao()+","+j.getHabilidade()+","+j.getCondicionamento()+",'"+j.getNome()+"',"+clube.getClubeId()+");");
            }
        }


        Cursor cursor = db.rawQuery("SELECT * FROM clube", null);
        String texto = "";
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            texto += cursor.getString(cursor.getColumnIndex("clubeId"));
            texto += ": " + cursor.getString(cursor.getColumnIndex("nome"));

            texto += "\n";
            cursor.moveToNext();
        }
        Cursor cursorJ = db.rawQuery("SELECT * FROM jogador", null);
        String txt = "";
        cursorJ.moveToFirst();
        while (!cursorJ.isAfterLast()) {
            txt += cursorJ.getString(cursorJ.getColumnIndex("jogadorId"));
            txt += ": " + cursorJ.getString(cursorJ.getColumnIndex("nome"));
            txt += ": " + cursorJ.getString(cursorJ.getColumnIndex("habilidade"));
            txt += ": " + cursorJ.getString(cursorJ.getColumnIndex("motivacao"));
            txt += ": " + cursorJ.getString(cursorJ.getColumnIndex("condicionamento"));


            txt += "\n";
            cursorJ.moveToNext();
        }
        tvJogadores.setText(txt);

        tvClubes.setText(texto);


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
