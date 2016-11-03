package Model.DAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.widget.Toast;

import Model.Jogador;

/**
 * Created by sila on 03/11/16.
 */

public class jogadorDAO {
    private SQLiteDatabase db;

    public jogadorDAO() {
        this.db = Conexao.getConexao();
    }

    public void insert(Jogador j) {
        try {
            db.execSQL("INSERT INTO public.jogador(\n" +
                    "           posicao, jogando, motivacao, habilidade, condicionamento, \n" +
                    "            nome)\n" +
                    "    VALUES (" + j.getPosicao() + "," + j.isJogando() + "," + j.getMotivacao() + "," + j.getHabilidade() + "," + j.getCondicionamento() + ","
                    + j.getNome() + ");");

        } catch (SQLException e) {

            System.out.print(e);
        }
    }
}