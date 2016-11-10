package com.example.sila.brasfootmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Model.Clube;
import Model.Jogador;

public class Estadio extends AppCompatActivity {
    private TextView tvValorMostrado;
    private EditText valorRecebido;
    private Editable texto;
    private int ss;
    private int finalValor;
    private SQLiteDatabase db;
    private static final String ARQUIVO_PREFERENCIAS = "arquivo_preferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadio);
        tvValorMostrado= (TextView) findViewById(R.id.tvValorMostrado);
        valorRecebido = (EditText) findViewById(R.id.etValorDigitado);
        db = openOrCreateDatabase("foot", MODE_PRIVATE, null);

    }


    public void calcular(View v){
        texto = this.valorRecebido.getText();
        ss = Integer.parseInt(texto.toString());
        finalValor = ss*500;



        tvValorMostrado.setText("R$: "+ finalValor);
    }

    public void confirmar(View v) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Deseja Confirmar sua Compra de " + ss + " lugares por " + finalValor + "?");
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
                        double novoCaixa=0;
                        if(caixa>=finalValor) {
                            novoCaixa=caixa-finalValor;
                            db.execSQL("UPDATE clube SET caixa = '"+novoCaixa+"' WHERE clubeId = '" + meuClubeId + "';");
                            Toast.makeText(getApplicationContext(), ss  +" lugares construído", Toast.LENGTH_SHORT).show();


                        }
                        else{
                            Toast.makeText(getApplicationContext(), finalValor + " não disponível. Valor em Caixa: " + caixa, Toast.LENGTH_SHORT).show();
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
            case R.id.menuJogos:
                startActivity(new Intent(getApplicationContext(), JogosActivity.class));
                finish();
                break;
            case R.id.menuResultados:
                startActivity(new Intent(getApplicationContext(), ResultadosActivity.class));
                finish();
                break;

            case R.id.menuJogador:
                startActivity(new Intent(getApplicationContext(), JogadorActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}