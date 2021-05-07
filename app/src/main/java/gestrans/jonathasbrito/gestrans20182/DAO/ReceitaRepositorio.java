package gestrans.jonathasbrito.gestrans20182.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gestrans.jonathasbrito.gestrans20182.models.Receita;
import gestrans.jonathasbrito.gestrans20182.helpers.ReceitaSQLHelper;

/**
 * Created by Brito on 19/11/2018.
 */

public class ReceitaRepositorio {

    private ReceitaSQLHelper helper;

    public ReceitaRepositorio(Context ctx){

        helper = new ReceitaSQLHelper(ctx);

    }


    public long inserir(Receita receita){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ReceitaSQLHelper.COLUNA_VALOR, receita.valor);
        contentValues.put(ReceitaSQLHelper.COLUNA_TIPO, receita.tipo);
        contentValues.put(ReceitaSQLHelper.COLUNA_HORA, receita.hora);

        long id = db.insert(ReceitaSQLHelper.TABELA_RECEITA, null, contentValues);

        if(id != -1){

            receita.id = id;

        }

        return id;


    }


    public int atualizar (Receita receita){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ReceitaSQLHelper.COLUNA_ID, receita.id);
        contentValues.put(ReceitaSQLHelper.COLUNA_HORA, receita.hora);
        contentValues.put(ReceitaSQLHelper.COLUNA_VALOR, receita.valor);
        contentValues.put(ReceitaSQLHelper.COLUNA_TIPO, receita.tipo);

        int linhasAfetadas = db.update(

                ReceitaSQLHelper.TABELA_RECEITA,
                contentValues,
                ReceitaSQLHelper.COLUNA_ID + " = ?",
                new String[]{String.valueOf(receita.id)}

        );

        db.close();
        return linhasAfetadas;

    }


    public boolean excluir(Receita receita){

        try{

            SQLiteDatabase db = helper.getReadableDatabase();
            int linhasAfetadas = db.delete(

                    ReceitaSQLHelper.TABELA_RECEITA,
                    ReceitaSQLHelper.COLUNA_ID + " = ?",
                    new String[]{String.valueOf(receita.id)}

            );

            Log.i("INFO", "RECEITA REMOVIDA COM SUCESSO");
            db.close();

        }catch(Exception e){

            Log.e("INFO", "ERRO AO REMOVER RECEITA");
            return false;
        }

        return true;

    }


    public Cursor carregarReceitas(){

        Cursor cursor;

        String[] campos = {helper.COLUNA_ID, helper.COLUNA_VALOR, helper.COLUNA_HORA, helper.COLUNA_TIPO};
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.query(helper.TABELA_RECEITA, campos, null, null, null, null, null);

        if( cursor != null )
            cursor.moveToFirst();

        db.close();
        return cursor;

    }

    public List<Receita> listar(){

        List<Receita> receitas = new ArrayList<>();

        String sql = "SELECT * FROM " + ReceitaSQLHelper.TABELA_RECEITA + " ;";
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] campos = {helper.COLUNA_ID, helper.COLUNA_VALOR, helper.COLUNA_HORA, helper.COLUNA_TIPO};
        Cursor cursor = db.query(helper.TABELA_RECEITA, campos, null, null, null, null, null);

        while(cursor.moveToNext()){

            Receita receita = new Receita();

            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String valor = cursor.getString(cursor.getColumnIndex("valor"));
            String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
            String hora = cursor.getString(cursor.getColumnIndex("hora"));

            receita.setId(id);
            receita.setHora(hora);
            receita.setTipo(tipo);
            receita.setValor(valor);

            receitas.add(receita);
            Log.i("ReceitaRespositorio", receita.tipo);

        }

        //listar Receitas
        return receitas;

    }

    public List<Receita> buscarReceita(String filtro){

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECET * FROM "+ ReceitaSQLHelper.TABELA_RECEITA;
        String[] argumentos = null;

        if(filtro != null){

            sql += " WHERE "+ ReceitaSQLHelper.COLUNA_ID + " LIKE ?";
            argumentos = new String[]{ filtro };

        }

        sql += " ORDER BY "+ ReceitaSQLHelper.COLUNA_HORA;

        Cursor cursor = db.rawQuery(sql, argumentos);

        List<Receita> receitas = new ArrayList<>();

        while(cursor.moveToFirst()){

            long id = cursor.getLong(cursor.getColumnIndex(ReceitaSQLHelper.COLUNA_ID));
            String valor = cursor.getString(cursor.getColumnIndex(ReceitaSQLHelper.COLUNA_VALOR));
            String hora = cursor.getString(cursor.getColumnIndex(ReceitaSQLHelper.COLUNA_HORA));
            String tipo = cursor.getString(cursor.getColumnIndex(ReceitaSQLHelper.COLUNA_TIPO));

            Receita receita = new Receita(id, valor, hora, tipo);

            receitas.add(receita);

        }

        cursor.close();
        db.close();

        return receitas;

    }

}
