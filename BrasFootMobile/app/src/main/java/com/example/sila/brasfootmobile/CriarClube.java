package com.example.sila.brasfootmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CriarClube extends AppCompatActivity {

    private ListView listaEstadios;
    private String[] estadios = {"Estadio 1","Estadio 2","Estadio 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaEstadios = (ListView) findViewById(R.id.lvEstadios);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                estadios
        );
        listaEstadios.setAdapter(adapter);
        listaEstadios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dis = (String) listaEstadios.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), dis, Toast.LENGTH_SHORT).show();
            }
        });
    }}

