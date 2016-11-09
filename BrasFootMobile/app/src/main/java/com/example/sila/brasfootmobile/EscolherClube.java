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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

<<<<<<< HEAD
import Model.Clube;
=======
>>>>>>> origin/master
import Model.*;
import Model.Estadio;

public class EscolherClube extends AppCompatActivity {
    private Button btConfirmar;
    private SQLiteDatabase db;
    private TextView tvClubes, tvJogadores, tvCaixa;
    private Spinner lvClubes;
    private ArrayList<Clube> clubes = new ArrayList<>();
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    private String dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esolher_clube);
<<<<<<< HEAD
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
		db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
=======
>>>>>>> origin/master
        tvClubes = (TextView) findViewById(R.id.tvClubes);
        tvJogadores = (TextView) findViewById(R.id.tvJogadores);
        tvCaixa = (TextView) findViewById(R.id.tvCaixa);
        lvClubes  = (Spinner) findViewById(R.id.spinner);
        btConfirmar = (Button) findViewById(R.id.btConfirmar);

@@ -42,8 +44,13 @@ public class EscolherClube extends AppCompatActivity {
        mostrarDados();
    }
    public void mostrarDados(){
<<<<<<< HEAD
<<<<<<< HEAD

        final Cursor cursor = db.rawQuery("SELECT * FROM clube", null);
=======
=======
>>>>>>> origin/master
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM clube", null);
>>>>>>> origin/master
        String[] nomes= new String[cursor.getCount()];
        int size = 0;

        String texto = "";
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            texto += cursor.getString(cursor.getColumnIndex("clubeId"));
            texto += ": " + cursor.getString(cursor.getColumnIndex("nome"));
            texto += ": " + cursor.getString(cursor.getColumnIndex("caixa"));
            nomes[size]=cursor.getString(cursor.getColumnIndex("nome"));
            size++;
            texto += "\n";

            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );
        lvClubes.setAdapter(adapter);
        lvClubes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
                dis = (String) lvClubes.getItemAtPosition(pos);
                btConfirmar.setClickable(true);
<<<<<<< HEAD
<<<<<<< HEAD
                Cursor cursorJ = db.rawQuery("SELECT jogador.nome,jogador.habilidade,jogador.motivacao,jogador.condicionamento, jogador.valor, clube.caixa FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE clube.nome = '"+dis+"'", null);
=======
                db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
                Cursor cursorJ = db.rawQuery("SELECT jogador.nome as nomej,jogador.habilidade as habilidadej,jogador.motivacao as motivacaoj,jogador.condicionamento as condicionamentoj FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE clube.nome = '"+dis+"'", null);
>>>>>>> origin/master
=======
                db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
                Cursor cursorJ = db.rawQuery("SELECT jogador.nome as nomej,jogador.habilidade as habilidadej,jogador.motivacao as motivacaoj,jogador.condicionamento as condicionamentoj FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE clube.nome = '"+dis+"'", null);
>>>>>>> origin/master
                String txt = "";

                cursorJ.moveToFirst();
                while (!cursorJ.isAfterLast()) {
<<<<<<< HEAD
<<<<<<< HEAD
                    txt += cursorJ.getString(cursorJ.getColumnIndex("jogador.nome"));
                    txt += ": " + cursorJ.getString(cursorJ.getColumnIndex("jogador.habilidade"));
                    txt += ": " + cursorJ.getString(cursorJ.getColumnIndex("jogador.motivacao"));
                    txt += ": " + cursorJ.getString(cursorJ.getColumnIndex("jogador.condicionamento"));
                    tvCaixa.setText(cursorJ.getString(cursorJ.getColumnIndex("clube.caixa")));
=======
=======
>>>>>>> origin/master
                    txt += cursorJ.getString(cursorJ.getColumnIndex("nomej"));
                    txt += " : " + cursorJ.getString(cursorJ.getColumnIndex("habilidadej"));
                    txt += " : " + cursorJ.getString(cursorJ.getColumnIndex("motivacaoj"));
                    txt += " : " + cursorJ.getString(cursorJ.getColumnIndex("condicionamentoj"));

>>>>>>> origin/master

                    txt += "\n";
                    cursorJ.moveToNext();
                }

                cursorJ.close();
                db.close();
                tvJogadores.setText(txt);


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }
    public void escolher(View v){
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("clube", dis);
        editor.apply();
        Toast.makeText(getApplicationContext(),sharedPreferences.getString("clube",""),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),Time.class);
        finish();
        startActivity(intent);
    }
<<<<<<< HEAD
<<<<<<< HEAD
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
                startActivity(new Intent(getApplicationContext(), Estadio.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
=======
>>>>>>> origin/master
=======
>>>>>>> origin/master

}

