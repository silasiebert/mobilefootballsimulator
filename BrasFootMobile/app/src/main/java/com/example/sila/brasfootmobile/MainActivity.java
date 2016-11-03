package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import Model.Clube;
import Model.DAO.Conexao;
import Model.Estadio;
import Model.Jogador;
import Model.Jogo;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("foot_sim", MODE_PRIVATE, null);
    }

    public void novoJogo(View v) {
        db = Conexao.getConexao();
        db.execSQL(
                        "CREATE TABLE  campeonato \n" +
                        "(\n" +
                        "\t id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "\n" +
                        "\tCONSTRAINT  FK_Campeonato_Jogo  FOREIGN KEY () REFERENCES  () ON DELETE No Action ON UPDATE No Action\n" +
                        ")\n" +
                        ";"
                "DROP TABLE IF EXISTS  jogador \n" +
                        ";\n" +
                        "\n" +
                        "DROP TABLE IF EXISTS  estadio \n" +
                        ";\n" +
                        "\n" +
                        "DROP TABLE IF EXISTS  clube \n" +
                        ";\n" +
                        "\n" +
                        "DROP TABLE IF EXISTS  jogo \n" +
                        ";\n" +
                        "\n" +
                        "DROP TABLE IF EXISTS  loja \n" +
                        ";\n" +
                        "\n" +
                        "DROP TABLE IF EXISTS  campeonato \n" +
                        ";\n" +
                        "\n" +
                        "CREATE TABLE  jogador \n" +
                        "(\n" +
                        "\t id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "\t posicao  INTEGER,\n" +
                        "\t jogando  INTEGER,\n" +
                        "\t motivacao  INTEGER,\n" +
                        "\t habilidade  INTEGER,\n" +
                        "\t condicionamento  INTEGER,\n" +
                        "\t nome  TEXT\n" +
                        ")\n" +
                        ";\n" +
                        "\n" +
                        "CREATE TABLE  estadio \n" +
                        "(\n" +
                        "\t id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "\t capacidade  INTEGER,\n" +
                        "\t nome  TEXT,\n" +
                        "\t precoEntrada  REAL,\n" +
                        "\t precoExpansao  REAL\n" +
                        ")\n" +
                        ";\n" +
                        "\n" +
                        "CREATE TABLE  clube \n" +
                        "(\n" +
                        "\t id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "\t forca  INTEGER,\n" +
                        "\t nome  TEXT,\n" +
                        "\n" +
                        "\tCONSTRAINT  FK_Clube_Estadio  FOREIGN KEY ( id ) REFERENCES  estadio  ( id ) ON DELETE No Action ON UPDATE No Action,\n" +
                        "\tCONSTRAINT  FK_Clube_Jogador  FOREIGN KEY ( id ) REFERENCES  jogador  ( id ) ON DELETE No Action ON UPDATE No Action\n" +
                        ")\n" +
                        ";\n" +
                        "\n" +
                        "CREATE TABLE  jogo \n" +
                        "(\n" +
                        "\t id  INTEGER NOT NULL,\n" +
                        "\t golsLocal  INTEGER,\n" +
                        "\t golsVisitante  INTEGER,\n" +
                        "\t lucro  REAL,\n" +
                        "\t vencedor  INTEGER,\n" +
                        "\tCONSTRAINT  PK_Jogo  PRIMARY KEY (),\n" +
                        "\tCONSTRAINT  FK_Jogo_Campeonato  FOREIGN KEY ( id ) REFERENCES  campeonato  ( id ) ON DELETE No Action ON UPDATE No Action,\n" +
                        "\tCONSTRAINT  FK_Visitante_Clube  FOREIGN KEY ( id ) REFERENCES  clube  ( id ) ON DELETE No Action ON UPDATE No Action,\n" +
                        "\tCONSTRAINT  FK_Local_Clube  FOREIGN KEY ( id ) REFERENCES  clube  ( id ) ON DELETE No Action ON UPDATE No Action\n" +
                        ")\n" +
                        ";\n" +
                        "\n" +
                        "CREATE TABLE  loja \n" +
                        "(\n" +
                        "\t id  INTEGER\n" +
                        ")\n" +
                        ";\n" +
                        "\n");
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
