package com.example.sila.brasfootmobile;

import android.content.Intent;
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
import android.widget.ListView;

import java.util.ArrayList;

import Model.Jogador;

public class Time extends AppCompatActivity {
    private ListView lvGoleiros,lvAtacantes,lvMeioCampos,lvDefensores;
    private Button btSalvar;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    private SQLiteDatabase db;
    private String nomeGoleiro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        lvGoleiros = (ListView) findViewById(R.id.lvGoleiros);
        lvAtacantes = (ListView) findViewById(R.id.lvAtacantes);
        lvMeioCampos = (ListView) findViewById(R.id.lvMeioCampos);
        lvDefensores = (ListView) findViewById(R.id.lvDefensores);


        carregarGoleiros();
        carregarAtacantes();
        carregarMeiCampos();
        carregarDefensores();
    }
    public void carregarDefensores(){
        ArrayList<String>defensores = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.nome FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.DEFENSOR+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);

        c.moveToFirst();
        while (!c.isAfterLast()){
            defensores.add(c.getString(c.getColumnIndex("jogador.nome")));
            c.moveToNext();
        }
        c.close();
        lvDefensores.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                defensores));

    }
    public void carregarAtacantes(){
        ArrayList<String>atacantes = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.nome FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.ATACANTE+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            atacantes.add(c.getString(c.getColumnIndex("jogador.nome")));
            c.moveToNext();
        }
        c.close();
        lvAtacantes.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                atacantes));

    }
    public void carregarGoleiros(){
        ArrayList<String>goleiros = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.nome FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.GOLEIRO+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);

        c.moveToFirst();
        while (!c.isAfterLast()){
            goleiros.add(c.getString(c.getColumnIndex("jogador.nome")));
            c.moveToNext();
        }
        c.close();
        lvGoleiros.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                goleiros));

    }
    public void carregarMeiCampos(){
        ArrayList<String>meiocampos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.nome FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.MEIOCAMPO+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);

        c.moveToFirst();
        while (!c.isAfterLast()){
            meiocampos.add(c.getString(c.getColumnIndex("jogador.nome")));
            c.moveToNext();
        }
        c.close();
        lvMeioCampos.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                meiocampos));

    }
    public void selecionarGoleiro(){
    lvGoleiros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nomeGoleiro = (String) lvGoleiros.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void salvarTime(View view){
        //Atualiza no banco qual vai ser o goleiro em campo e tira os outros do campo.
        db.execSQL("UPDATE jogador SET jogando = 1 WHERE nome="+nomeGoleiro+";");
        db.execSQL("UPDATE jogador SET jogando = 0 WHERE nome !="+nomeGoleiro+";");

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
        }
        return super.onOptionsItemSelected(item);
    }

}
