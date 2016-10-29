package com.example.sila.brasfootmobile;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Model.Clube;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("foot_sim", MODE_PRIVATE, null);
    }


    public void rodarPartida(Clube local, Clube visitante){

    }
}
