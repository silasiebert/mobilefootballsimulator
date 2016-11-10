package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Jogador;

public class JogadorActivity extends AppCompatActivity {
    private Spinner spinner;
    private TextView tvNome,tvPos,tvHab,tvCon,tvMot,tvValor;
    private ArrayList<Jogador>jogadors;
    private SQLiteDatabase db;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);
        spinner = (Spinner) findViewById(R.id.spinnerJogador);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvPos = (TextView) findViewById(R.id.tvPosicao);
        tvHab = (TextView) findViewById(R.id.tvHabilidade);
        tvMot = (TextView) findViewById(R.id.tvMotivacao);
        tvValor = (TextView) findViewById(R.id.tvValorVenda);
        tvCon = (TextView) findViewById(R.id.tvCondicionamento);
        carregarJogadores();
    }
    public void carregarJogadores(){

        jogadors = new ArrayList<>();
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT jogador.habilidade as habilidadej,jogador.nome as nomej,jogador.posicao as posicaoj,jogador.condicionamento as condj,jogador.motivacao as motj,jogador.jogando as jogandoj FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE clube.nome = '" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            jogadors.add(new Jogador(c.getInt(c.getColumnIndex("habilidadej")), c.getString(c.getColumnIndex("nomej")), c.getInt(c.getColumnIndex("posicaoj")), c.getInt(c.getColumnIndex("condj")), c.getInt(c.getColumnIndex("motj")), c.getInt(c.getColumnIndex("jogandoj")) != 0));
            c.moveToNext();
        }

        c.close();
        spinner.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                jogadors));
        selecionarJogador();

    }
    private void selecionarJogador(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Jogador jogador = jogadors.get(position);
                tvNome.setText(jogador.getNome());
                tvPos.setText(String.valueOf(jogador.getPosicao()));
                tvMot.setText(String.valueOf(jogador.getMotivacao()));
                tvValor.setText(String.valueOf(jogador.getValor()));
                tvHab.setText(String.valueOf(jogador.getHabilidade()));
                tvCon.setText(String.valueOf(jogador.getCondicionamento()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
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
