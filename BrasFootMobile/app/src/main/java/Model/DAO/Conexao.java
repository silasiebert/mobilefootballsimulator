package Model.DAO;


import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sila on 02/11/16.
 */

public class Conexao {
    private Conexao c;

    private SQLiteDatabase bancoDados;;

    private Conexao() {
        bancoDados = bancoDados.openOrCreateDatabase("asdasd",null);

    }

    public static SQLiteDatabase getConexao(){
        if(c==null){
            this.c = new Conexao();
            }

        return c.bancoDados;
    }


}
