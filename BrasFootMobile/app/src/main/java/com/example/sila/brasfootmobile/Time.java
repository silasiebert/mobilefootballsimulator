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
import android.widget.TextView;

import java.util.ArrayList;

import Model.Jogador;

public class Time extends AppCompatActivity {
    private ListView lvGoleiros,lvAtacantes,lvMeioCampos,lvDefensores;
    ArrayList<Jogador>defensores,atacantes,meiocampos,goleiros;
    private Button btSalvar;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    private SQLiteDatabase db;
    private TextView tvNomeClube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        lvGoleiros = (ListView) findViewById(R.id.lvGoleiros);
        lvAtacantes = (ListView) findViewById(R.id.lvAtacantes);
        lvMeioCampos = (ListView) findViewById(R.id.lvMeioCampos);
        lvDefensores = (ListView) findViewById(R.id.lvDefensores);
        tvNomeClube = (TextView) findViewById(R.id.tvNomeClube);
        tvNomeClube.setText(getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube",""));

        carregarGoleiros();
        carregarAtacantes();
        carregarMeiCampos();
        carregarDefensores();
    }
    public void carregarDefensores(){
        defensores = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.DEFENSOR+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);

        c.moveToFirst();
        while (!c.isAfterLast()){

            defensores.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")),c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando"))!=0));
            c.moveToNext();
        }

        c.close();
        lvDefensores.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                defensores));
        lvDefensores.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        int i=0;
        for (Jogador j:defensores){
            if(j.isJogando()){
                lvDefensores.setItemChecked(i,true);
            }else {
                lvDefensores.setItemChecked(i,false);
            }
            i++;
        }

    }
    public void carregarAtacantes(){
        atacantes = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.ATACANTE+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            atacantes.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")),c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando"))!=0));
            c.moveToNext();
        }
        c.close();

        lvAtacantes.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                atacantes));
        lvAtacantes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        int i=0;
        for (Jogador j:atacantes){
            if(j.isJogando()){
                lvAtacantes.setItemChecked(i,true);
            }else {
                lvAtacantes.setItemChecked(i,false);
            }
            i++;
        }
    }
    public void carregarGoleiros(){
        goleiros = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.GOLEIRO+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            goleiros.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")),c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando"))!=0));
            c.moveToNext();
        }
        c.close();
        lvGoleiros.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                goleiros));

        int i=0;
        for (Jogador j:goleiros){
            if(j.isJogando()){
                lvGoleiros.setItemChecked(i,true);
            }else {
                lvGoleiros.setItemChecked(i,false);
            }
            i++;
        }
    }
    public void carregarMeiCampos(){
        meiocampos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao ="+ Jogador.MEIOCAMPO+" AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"'",null);
        c.moveToFirst();
        while (!c.isAfterLast()){

            meiocampos.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")),c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando"))!=0));
            c.moveToNext();
        }
        c.close();
        lvMeioCampos.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                meiocampos));
        lvMeioCampos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        int i=0;
        for (Jogador j:meiocampos){
            if(j.isJogando()){
                lvMeioCampos.setItemChecked(i,true);
            }else {
                lvMeioCampos.setItemChecked(i,false);
            }
            i++;
        }
    }
    public void selecionarGoleiro(){
    lvGoleiros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean jogando = goleiros.get(position).isJogando();
                goleiros.get(position).setJogando(!jogando);
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void selecionarAtacantes(){
        lvAtacantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean jogando = atacantes.get(position).isJogando();
                atacantes.get(position).setJogando(!jogando);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void selecionarMeicampos(){
        lvMeioCampos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean jogando = meiocampos.get(position).isJogando();
                meiocampos.get(position).setJogando(!jogando);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void selecionarDefensores(){
        lvGoleiros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boolean jogando = defensores.get(position).isJogando();
                defensores.get(position).setJogando(!jogando);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void salvarTime(View view){
        ArrayList<Jogador>jogadors = new ArrayList<>();
        jogadors.addAll(goleiros);
        jogadors.addAll(atacantes);
        jogadors.addAll(meiocampos);
        jogadors.addAll(defensores);

        for (Jogador j : jogadors){
            if (j.isJogando()){
                db.execSQL("UPDATE jogador SET jogando = 1 WHERE nome="+j.getNome()+"AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"';");
            }else{
                db.execSQL("UPDATE jogador SET jogando = 0 WHERE nome !="+j.getNome()+"AND clube.nome = '"+getSharedPreferences(ARQUIVO_PREFERENCIAS,0).getString("clube","")+"';");
            }
        }

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
