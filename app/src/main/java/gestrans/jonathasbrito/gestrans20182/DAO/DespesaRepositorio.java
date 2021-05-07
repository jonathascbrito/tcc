package gestrans.jonathasbrito.gestrans20182.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gestrans.jonathasbrito.gestrans20182.helpers.DespesaSQLHelper;
import gestrans.jonathasbrito.gestrans20182.models.Despesa;

/**
 * Created by Brito on 20/11/2018.
 */

public class DespesaRepositorio {

    private DespesaSQLHelper helper;

    public DespesaRepositorio(Context ctx){

        helper = new DespesaSQLHelper(ctx);

    }


    public long inserir(Despesa despesa){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DespesaSQLHelper.COLUNA_VALOR, despesa.valor);
        contentValues.put(DespesaSQLHelper.COLUNA_TIPO, despesa.tipo);
        contentValues.put(DespesaSQLHelper.COLUNA_HORA, despesa.hora);

        long id = db.insert(DespesaSQLHelper.TABELA_DESPESA, null, contentValues);

        if(id != -1){

            despesa.id = id;

        }

        return id;

    }


    public int atualizar (Despesa despesa){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DespesaSQLHelper.COLUNA_ID_DESPESA, despesa.id);
        contentValues.put(DespesaSQLHelper.COLUNA_HORA, despesa.hora);
        contentValues.put(DespesaSQLHelper.COLUNA_VALOR, despesa.valor);
        contentValues.put(DespesaSQLHelper.COLUNA_TIPO, despesa.tipo);

        int linhasAfetadas = db.update(

                DespesaSQLHelper.TABELA_DESPESA,
                contentValues,
                DespesaSQLHelper.COLUNA_ID_DESPESA + " = ?",
                new String[]{String.valueOf(despesa.id)}

        );

        db.close();
        return linhasAfetadas;

    }


    public boolean excluir(Despesa despesa){

        try{

            SQLiteDatabase db = helper.getReadableDatabase();
            int linhasAfetadas = db.delete(

                    DespesaSQLHelper.TABELA_DESPESA,
                    DespesaSQLHelper.COLUNA_ID_DESPESA + " = ?",
                    new String[]{String.valueOf(despesa.id)}

            );

            Log.i("INFO", "DESPESA REMOVIDA COM SUCESSO");
            db.close();

        }catch(Exception e){

            Log.e("INFO", "ERRO AO REMOVER DESPESA");
            return false;
        }

        return true;

    }


    public List<Despesa> listar(){

        List<Despesa> despesas = new ArrayList<>();

        String sql = "SELECT * FROM " + DespesaSQLHelper.TABELA_DESPESA + " ;";
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] campos = {helper.COLUNA_ID_DESPESA, helper.COLUNA_VALOR, helper.COLUNA_HORA, helper.COLUNA_TIPO};
        Cursor cursor = db.query(helper.TABELA_DESPESA, campos, null, null, null, null, null);

        while(cursor.moveToNext()){

            Despesa despesa = new Despesa();

            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String valor = cursor.getString(cursor.getColumnIndex("valor"));
            String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
            String hora = cursor.getString(cursor.getColumnIndex("hora"));

            despesa.setId(id);
            despesa.setHora(hora);
            despesa.setTipo(tipo);
            despesa.setValor(valor);

            despesas.add(despesa);
            Log.i("DespesaRepositorio", despesa.tipo);

        }

        //listar Receitas
        return despesas;

    }

    public List<Despesa> buscarDespesa(String filtro){

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECET * FROM "+ DespesaSQLHelper.TABELA_DESPESA;
        String[] argumentos = null;

        if(filtro != null){

            sql += " WHERE "+ DespesaSQLHelper.COLUNA_ID_DESPESA + " LIKE ?";
            argumentos = new String[]{ filtro };

        }

        sql += " ORDER BY "+ DespesaSQLHelper.COLUNA_HORA;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Despesa> despesas = new ArrayList<>();

        while(cursor.moveToFirst()){

            long id = cursor.getLong(cursor.getColumnIndex(DespesaSQLHelper.COLUNA_ID_DESPESA));
            String valor = cursor.getString(cursor.getColumnIndex(DespesaSQLHelper.COLUNA_VALOR));
            String hora = cursor.getString(cursor.getColumnIndex(DespesaSQLHelper.COLUNA_HORA));
            String tipo = cursor.getString(cursor.getColumnIndex(DespesaSQLHelper.COLUNA_TIPO));

            Despesa despesa = new Despesa(id, valor, hora, tipo);

            despesas.add(despesa);

        }

        cursor.close();
        db.close();

        return despesas;

    }

}
