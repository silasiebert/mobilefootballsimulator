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

import Model.*;

public class EscolherClube extends AppCompatActivity {
    private Button btConfirmar;
    private SQLiteDatabase db;
    private TextView tvClubes, tvJogadores;
    private Spinner lvClubes;
    private ArrayList<Clube> clubes = new ArrayList<>();
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    private String dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esolher_clube);
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        tvClubes = (TextView) findViewById(R.id.tvClubes);
        tvJogadores = (TextView) findViewById(R.id.tvJogadores);
        lvClubes  = (Spinner) findViewById(R.id.spinner);
        btConfirmar = (Button) findViewById(R.id.btConfirmar);

        mostrarDados();
    }
    public void mostrarDados(){

        Cursor cursor = db.rawQuery("SELECT * FROM clube", null);
        String[] nomes= new String[cursor.getCount()];
        int size = 0;

        String texto = "";
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            texto += cursor.getString(cursor.getColumnIndex("clubeId"));
            texto += ": " + cursor.getString(cursor.getColumnIndex("nome"));
            nomes[size]=cursor.getString(cursor.getColumnIndex("nome"));
            size++;
            texto += "\n";

            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
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
                Cursor cursorJ = db.rawQuery("SELECT * FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE clube.nome = '"+dis+"'", null);
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
        editor.commit();
        Toast.makeText(getApplicationContext(),sharedPreferences.getString("clube",""),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),Campeonato.class);
        finish();
        startActivity(intent);
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
                startActivity(new Intent(getApplicationContext(), LojaJogador.class));
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

