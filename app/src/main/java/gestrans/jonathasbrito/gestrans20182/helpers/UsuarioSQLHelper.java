package gestrans.jonathasbrito.gestrans20182.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by Brito on 19/11/2018.
 */

public class UsuarioSQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbGestrans";
    private static final int VERSAO_BANCO = 1;

    public static final String TABELA_USUARIO = "usuario";
    public static final String COLUNA_ID_USUARIO = "_id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_LOGIN = "login";


    //Tabela Receitas
    public static final String TABELA_RECEITA = "receita";
    public static final String COLUNA_ID_RECEITA = "_id";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_TIPO = "tipo";
    public static final String COLUNA_HORA = "hora";

    //Tabela Despesas
    public static final String TABELA_DESPESA = "despesa";
    public static final String COLUNA_ID_DESPESA = "_id";

    public UsuarioSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA_USUARIO + " ("
                    + COLUNA_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUNA_NOME + " TEXT NOT NULL, "
                    + COLUNA_SENHA + " TEXT NOT NULL, "
                    + COLUNA_EMAIL + " TEXT NOT NULL, "
                    + COLUNA_LOGIN + " TEXT NOT NULL)";

        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + TABELA_RECEITA + " ("
                + COLUNA_ID_RECEITA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_VALOR + " TEXT NOT NULL, "
                + COLUNA_TIPO + " TEXT NOT NULL, "
                + COLUNA_HORA + " TEXT NOT NULL)";

        db.execSQL(sql2);

        String sql3 = "CREATE TABLE " + TABELA_DESPESA + " ("
                + COLUNA_ID_DESPESA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_VALOR + " TEXT NOT NULL, "
                + COLUNA_TIPO + " TEXT NOT NULL, "
                + COLUNA_HORA + " TEXT NOT NULL)";

        db.execSQL(sql3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //para realizar atualização do banco quando existir mudanças
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        onCreate(db);

    }
}
