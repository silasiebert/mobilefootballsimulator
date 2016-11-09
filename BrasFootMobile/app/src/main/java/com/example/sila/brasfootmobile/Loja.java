package com.example.sila.brasfootmobile;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

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
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import Model.*;

import static java.security.AccessController.getContext;

public class Loja extends AppCompatActivity {
    private Button btComprar;
    private TextView tvJogadores;
    private String mostraJogadores;
    private ListView listView;
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private String[] nomes = new String[11];
    private AlertDialog dialog;
    public String dis = "";
    public String nome = "";
    private SQLiteDatabase db;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);
        tvJogadores = (TextView) findViewById(R.id.tvJogadores);
        btComprar = (Button) findViewById(R.id.btComprar);
        listView = (ListView) findViewById(R.id.listView);
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);

        gerarJogadores();



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void gerarJogadores() {


        //Gerador de 20 jogadores aleatorios para cada clube
        // int numeroAletorio =(int)(Math.random()*30)+20;
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Saraiva", Jogador.GOLEIRO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Leonardo da Vinci", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Caleu", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Neymar", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Gandhi", Jogador.ATACANTE, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Galileu", Jogador.DEFENSOR, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Anitta", Jogador.DEFENSOR, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Pelé", Jogador.DEFENSOR, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Bartolomeu", Jogador.MEIOCAMPO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Picasso", Jogador.MEIOCAMPO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));
        jogadores.add(new Jogador((int) (Math.random() * 30) + 20, "Simone e Simara", Jogador.MEIOCAMPO, (int) (Math.random() * 30) + 20, (int) (Math.random() * 30) + 20, false, 100000));

        int size = 0;

        for (Jogador j : jogadores) {

            nomes[size] = j.getNome() + " - " + j.getValor();

            size++;
        }


        mostrarJogadores();

    }




    public void mostrarJogadores() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dis = (String) listView.getItemAtPosition(position);
               confirmar(dis);
            }

        });
    }

    public void confirmar(String dis) {
        nome = dis.substring(0, dis.indexOf("-")-1);
       String preco = dis.substring(dis.indexOf("-")+2,dis.length());
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Deseja Confirmar sua Compra para o jogador " + nome + " por " +preco + "?");
        alertDialogBuilder.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Cursor c = db.rawQuery("SELECT clubeId, caixa from clube WHERE" +
                                " nome='" + getSharedPreferences(ARQUIVO_PREFERENCIAS, 0).getString("clube", "") + "'", null);
                        c.moveToFirst();
                        int meuClubeId = c.getInt(c.getColumnIndex("clubeId"));
                        double caixa = c.getInt(c.getColumnIndex("caixa"));
                        c.close();
                        for(Jogador j: jogadores){
                            if(nome.equalsIgnoreCase(j.getNome())){
                                double novoCaixa=0;
                                if(caixa>=j.getValor()) {
                                    novoCaixa=caixa-j.getValor();
                                    db.execSQL("INSERT INTO jogador (posicao,jogando,motivacao,habilidade,condicionamento,nome,valor,clubeId) VALUES(" + j.getPosicao() + "," + 0 + "," + j.getMotivacao() + "," + j.getHabilidade() + "," + j.getCondicionamento() + ",'" + j.getNome() + "'," + j.getValor() + "," + meuClubeId + ");");
                                    db.execSQL("UPDATE clube SET caixa = '"+novoCaixa+"' WHERE clubeId = '" + meuClubeId + "';");
                                    Toast.makeText(getApplicationContext(), j.getNome()  +" adicionado", Toast.LENGTH_SHORT).show();


                                }
                                else{
                                    Toast.makeText(getApplicationContext(), j.getValor()+ "Saldo insuficiente. Valor em Caixa: " + novoCaixa, Toast.LENGTH_SHORT).show();
                                }
                                }
                        }


                    }
                });

        alertDialogBuilder.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(), "Ação NÃO confirmada!", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Loja Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
