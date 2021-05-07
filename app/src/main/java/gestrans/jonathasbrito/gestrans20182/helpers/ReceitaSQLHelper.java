package gestrans.jonathasbrito.gestrans20182.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Brito on 19/11/2018.
 * Classe para criação do banco de dados com relação a tabela receitas
 */

public class ReceitaSQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbGesTrans";
    private static final int VERSAO_BANCO = 2;

    public static final String TABELA_RECEITA = "receita";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_TIPO = "tipo";
    public static final String COLUNA_HORA = "hora";


    public ReceitaSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA_RECEITA + " ("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_VALOR + " TEXT NOT NULL, "
                    + COLUNA_TIPO + " TEXT NOT NULL, "
                    + COLUNA_HORA + " TEXT NOT NULL)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //para realizar atualização do banco quando existir mudanças
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_RECEITA);
        onCreate(db);

    }
}
