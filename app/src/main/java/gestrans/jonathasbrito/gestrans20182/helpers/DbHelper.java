package gestrans.jonathasbrito.gestrans20182.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Brito on 19/11/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_GESTRANS";

    /**
     * DbHelper: Construtor
     * @param context
     */
    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    /**
     * Metodo onCreate: sempre quando inicia um banco de dados esse mêtodo é chamado
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1 = "CREATE TABLE IF NOT EXISTS usuarios (_idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " nome TEXT NOT NULL, " +
                    " senha TEXT NOT NULL, " +
                    " email TEXT NOT NULL)";

        String sql2 = "CREATE TABLE IF NOT EXISTS receitas (_idReceita INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " tipo TEXT NOT NULL, " +
                    " valor TEXT NOT NULL, " +
                    " data TEXT NOT NULL)";

        String sql3 = "CREATE TABLE IF NOT EXISTS despesas (_idDespesa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " tipo TEXT NOT NULL, " +
                " valor TEXT NOT NULL, " +
                " data TEXT NOT NULL)";


        try{

            db.execSQL(sql1);
            db.execSQL(sql2);
            db.execSQL(sql3);
            Log.i("INFO DB", "SUCESSO AO CRIAR TABELA");
        }catch(Exception e){

            Log.i("INFO DB", "ERRO AO CRIAR A TABELA");

        }

    }

    /**
     * Metodo onUpgrade: Sempre que existe atualização do banco de dados esse mêtodo é chamado
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
