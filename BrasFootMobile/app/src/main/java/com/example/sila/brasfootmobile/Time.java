package com.example.sila.brasfootmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Jogador;

public class Time extends AppCompatActivity {
    private ListView lvGoleiros, lvAtacantes, lvMeioCampos, lvDefensores;
    ArrayList<Jogador> defensores, atacantes, meiocampos, goleiros;
    private Button btSalvar;
    private TextView tvQuantGoleiros,tvQuantDefensores,tvQuantAtacantes,tvQuantMeiocampos;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    private SQLiteDatabase db;
    private TextView tvNomeClube;
    private AlertDialog.Builder dialog;
    private int emCampo;
    private Jogador j;
    private boolean colocar;

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
        tvNomeClube.setText(getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", ""));
        tvQuantAtacantes = (TextView) findViewById(R.id.tvQuantAtacantes);
        tvQuantDefensores = (TextView) findViewById(R.id.tvQuantDefensores);
        tvQuantGoleiros = (TextView) findViewById(R.id.tvQuantGoleiros);
        tvQuantMeiocampos = (TextView) findViewById(R.id.tvQuantMeioCampos);
        carregarGoleiros();
        carregarAtacantes();
        carregarMeiCampos();
        carregarDefensores();
    }

    public void carregarDefensores() {
        defensores = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao =" + Jogador.DEFENSOR + " AND clube.nome = '" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "'", null);

        c.moveToFirst();
        while (!c.isAfterLast()) {

            defensores.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")), c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando")) != 0));
            c.moveToNext();
        }

        c.close();
        lvDefensores.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                defensores));
        lvDefensores.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        atualizarJogadores(defensores,lvDefensores,tvQuantDefensores);
        selecionarDefensores();
    }

    public void carregarAtacantes() {
        atacantes = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao =" + Jogador.ATACANTE + " AND clube.nome = '" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            atacantes.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")), c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando")) != 0));
            c.moveToNext();
        }
        c.close();

        lvAtacantes.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                atacantes));
        lvAtacantes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        atualizarJogadores(atacantes,lvAtacantes,tvQuantAtacantes);
        selecionarAtacantes();
    }

    public void carregarGoleiros() {
        goleiros = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao =" + Jogador.GOLEIRO + " AND clube.nome = '" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            goleiros.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")), c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando")) != 0));
            c.moveToNext();
        }
        c.close();
        lvGoleiros.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                goleiros));
        lvGoleiros.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        atualizarJogadores(goleiros,lvGoleiros,tvQuantGoleiros);
        selecionarGoleiro();
    }

    public void carregarMeiCampos() {
        meiocampos = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT jogador.habilidade,jogador.nome,jogador.posicao,jogador.condicionamento,jogador.motivacao,jogador.jogando FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE posicao =" + Jogador.MEIOCAMPO + " AND clube.nome = '" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "'", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            meiocampos.add(new Jogador(c.getInt(c.getColumnIndex("jogador.habilidade")), c.getString(c.getColumnIndex("jogador.nome")), c.getInt(c.getColumnIndex("jogador.posicao")), c.getInt(c.getColumnIndex("jogador.condicionamento")), c.getInt(c.getColumnIndex("jogador.motivacao")), c.getInt(c.getColumnIndex("jogador.jogando")) != 0));
            c.moveToNext();
        }
        c.close();
        lvMeioCampos.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                meiocampos));
        lvMeioCampos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        atualizarJogadores(meiocampos,lvMeioCampos,tvQuantMeiocampos);
        selecionarMeicampos();
    }

    public void selecionarGoleiro() {
        lvGoleiros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goleiros.get(position).setJogando(dialogo(goleiros.get(position), goleiros, 1,lvGoleiros,tvQuantGoleiros));
            }
//
        });
    }

    public void selecionarAtacantes() {
        lvAtacantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                atacantes.get(position).setJogando(dialogo(atacantes.get(position), atacantes, 4,lvAtacantes,tvQuantAtacantes));
            }
        });
    }

    public void selecionarMeicampos() {
        lvMeioCampos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meiocampos.get(position).setJogando(dialogo(meiocampos.get(position), meiocampos, 4,lvMeioCampos,tvQuantMeiocampos));
            }
        });
    }

    public void selecionarDefensores() {
        lvDefensores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                defensores.get(position).setJogando(dialogo(defensores.get(position), defensores, 2,lvDefensores,tvQuantDefensores));
            }
//
        });
    }

    public void salvarTime(View view) {
        ArrayList<Jogador> jogadors = new ArrayList<>();
        jogadors.addAll(goleiros);
        jogadors.addAll(atacantes);
        jogadors.addAll(meiocampos);
        jogadors.addAll(defensores);

        for (Jogador j : jogadors) {
            if (j.isJogando()) {
                db.execSQL("UPDATE jogador SET jogando = 1 FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE nome='" + j.getNome() + "' AND clube.nome = '" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "';");
            } else {
                db.execSQL("UPDATE jogador SET jogando = 0 FROM jogador INNER JOIN clube ON jogador.clubeId = clube.clubeId WHERE nome !='" + j.getNome() + "' AND clube.nome = '" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "';");
            }
        }
        Intent intent = new Intent(getApplicationContext(), Campeonato.class);
        finish();
        startActivity(intent);

    }
    public void atualizarJogadores(ArrayList<Jogador>jogadores,ListView listView,TextView textView){
        int quant = 0;
        //Conta quantos jogadores da posicao escolhida estao em campo
        for (Jogador jog : jogadores) {
            if (jog.isJogando()) {
                quant++;
            }
        }
        int i = 0;
        for (Jogador j : jogadores) {
            if (j.isJogando()) {
                listView.setItemChecked(i, true);
            } else {
                listView.setItemChecked(i, false);
            }
            i++;
        }

        textView.setText(Integer.toString(quant));

    }
    public boolean dialogo(Jogador jo,final ArrayList<Jogador> jogadores, final int max,final ListView listView,final TextView tv) {
        this.j = jo;
        emCampo = 0;
        //Conta quantos jogadores da posicao escolhida estao em campo
        for (Jogador jog : jogadores) {
            if (jog.isJogando()) {
                emCampo++;
            }
        }

        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Ficha de: " + j.getNome());
        dialog.setMessage("Condicionamento: " + j.getCondicionamento() + "\nHabilidade: " + j.getHabilidade() + "\nMotivacao: " + j.getMotivacao() + "\nDeseja colocar ou retira o jogador da ação?");

        dialog.setNegativeButton("Retirar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                colocar = false;
                Toast.makeText(getApplicationContext(), "Jogador " + j.getNome() + " retirado do campo!", Toast.LENGTH_LONG).show();
                atualizarJogadores(jogadores,listView,tv);
            }
        });


        dialog.setPositiveButton("Colocar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (emCampo < max) {
                    colocar = true;
                    Toast.makeText(getApplicationContext(), "Jogador " + j.getNome() + " adicionado!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Numero de jogadores nesta posica exedido!", Toast.LENGTH_LONG).show();
                    atualizarJogadores(jogadores,listView,tv);
                }

            }
        });

        dialog.setCancelable(true);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.create();
        dialog.show();

        return colocar;
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
