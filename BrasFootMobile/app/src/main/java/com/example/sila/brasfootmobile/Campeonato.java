package com.example.sila.brasfootmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Model.Clube;
import Model.Jogador;
import Model.Jogo;

public class Campeonato extends AppCompatActivity {
    private TextView tvClubes, tvAndamento, tvGols;
    private SQLiteDatabase db;
    private Button btJogar;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    private String nomeArquivo = "jogos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campeonato);
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);
        tvAndamento = (TextView) findViewById(R.id.tvAndamento);
        tvClubes = (TextView) findViewById(R.id.tvClubes);
        tvGols = (TextView) findViewById(R.id.tvGols);
        btJogar = (Button) findViewById(R.id.btJogar);
    }

    public void rodarPartida(View v) {
        tvGols.setVisibility(View.VISIBLE);
        tvClubes.setVisibility(View.VISIBLE);
        tvAndamento.setVisibility(View.VISIBLE);
        //recuperar os clubes do banco
        ArrayList<Clube> clubes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM clube", null);
        int indiceColunaId = cursor.getColumnIndex("clubeId");
        int indiceColunaNome = cursor.getColumnIndex("nome");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            clubes.add(new Clube(cursor.getInt(indiceColunaId), cursor.getString(indiceColunaNome)));
            cursor.moveToNext();
        }
        cursor.close();
        ArrayList<Jogador> jogadores;
        Cursor cur = null;
        for (Clube c : clubes) {
            jogadores = new ArrayList<>();
            cur = db.rawQuery("SELECT * FROM jogador WHERE clubeId = " + c.getClubeId(), null);
            int indiceColunaHabilidade = cur.getColumnIndex("habilidade");
            int indiceNomeJogador = cur.getColumnIndex("nome");
            int indiceColunaPos = cur.getColumnIndex("posicao");
            int indiceColunaCond = cur.getColumnIndex("condicionamento");
            int indiceColunaMot = cur.getColumnIndex("motivacao");
            int indiceColunaJogando = cur.getColumnIndex("jogando");
            cur.moveToFirst();
            while (!cur.isAfterLast()) {
                jogadores.add(new Jogador(cur.getInt(indiceColunaHabilidade), cur.getString(indiceColunaNome), cur.getInt(indiceColunaPos), cur.getInt(indiceColunaCond), cur.getInt(indiceColunaMot), cur.getInt(indiceColunaJogando) != 0));
                cur.moveToNext();
            }
            c.setJogadores(jogadores);
        }
        cur.close();
        SharedPreferences s = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        int fase = s.getInt("fase", 0);
        String meuClube = s.getString("clube", "");
        Clube meu = null;
        for (Clube cc : clubes) {
            if (cc.getNome().equals(meuClube)) {
                meu = cc;
            }
        }


        Jogo j = null;
        Clube local = null;
        Cursor curs = db.rawQuery("SELECT * FROM clube", null);

        if (fase > curs.getCount() - 1) {
            btJogar.setClickable(false);
        }else{
           local = clubes.get(fase);
            for (Clube visitante : clubes) {
                if (local != visitante) {

                    j = new Jogo(visitante, local);


                    if (local == meu || visitante == meu) {
                        tvClubes.setText(j.getVisitante().getNome() + "x" + j.getLocal().getNome());
                        tvAndamento.setText(j.resultado());
                        tvGols.setText(j.getGolsLocal() + "x" + j.getGolsVisitante());
                    }
                    salvarJogo(j);
                }
            }
            fase++;


            s.edit().putInt("fase", fase).apply();
        }
        curs.close();




    }

    public void salvarJogo(Jogo j) {

        String mensagem = j.getVisitante().getNome() + ";" + j.getLocal().getNome() + ";" + j.getGolsVisitante() + ";" + j.getGolsLocal() + ";" + j.getVencedor()+"\n";
        try {
            FileOutputStream fos = openFileOutput(nomeArquivo, MODE_APPEND);
            fos.write(mensagem.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(),
                    "Jogo salvo com sucesso!",
                    Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
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
            case R.id.menuEstadio:
                startActivity(new Intent(getApplicationContext(), Estadio.class));
                finish();
                break;
            case R.id.menuJogos:
                startActivity(new Intent(getApplicationContext(), JogosActivity.class));
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
