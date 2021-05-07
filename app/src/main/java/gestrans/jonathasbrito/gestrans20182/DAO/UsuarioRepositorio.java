package gestrans.jonathasbrito.gestrans20182.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import gestrans.jonathasbrito.gestrans20182.helpers.UsuarioSQLHelper;
import gestrans.jonathasbrito.gestrans20182.models.Usuario;

/**
 * Created by Brito on 19/11/2018.
 */

public class UsuarioRepositorio {

    private UsuarioSQLHelper helper;

    public UsuarioRepositorio(Context ctx){
        helper = new UsuarioSQLHelper(ctx);
    }

    public long inserir(Usuario usuario){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(UsuarioSQLHelper.COLUNA_NOME, usuario.nome);
        contentValues.put(UsuarioSQLHelper.COLUNA_LOGIN, usuario.login);
        contentValues.put(UsuarioSQLHelper.COLUNA_SENHA, usuario.senha);
        contentValues.put(UsuarioSQLHelper.COLUNA_EMAIL, usuario.email);

        long id = db.insert(UsuarioSQLHelper.TABELA_USUARIO, null, contentValues);
        if( id != -1 ){

            usuario.id = id;

        }

        db.close();

        return id;

    }

    public int atualizar(Usuario usuario){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UsuarioSQLHelper.COLUNA_ID_USUARIO, usuario.id);
        contentValues.put(UsuarioSQLHelper.COLUNA_LOGIN, usuario.login);
        contentValues.put(UsuarioSQLHelper.COLUNA_NOME, usuario.nome);
        contentValues.put(UsuarioSQLHelper.COLUNA_EMAIL, usuario.email);
        contentValues.put(UsuarioSQLHelper.COLUNA_SENHA, usuario.senha);

        int linhasAfetadas = db.update(
                UsuarioSQLHelper.TABELA_USUARIO,
                contentValues,
                UsuarioSQLHelper.COLUNA_ID_USUARIO +" = ?",
                new String[]{String.valueOf(usuario.id)}
        );

        db.close();
        return linhasAfetadas;

    }

    public int excluir(Usuario usuario){

        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                UsuarioSQLHelper.TABELA_USUARIO,
                UsuarioSQLHelper.COLUNA_ID_USUARIO + " = ?",
                new String[]{ String.valueOf(usuario.id)}
        );
        db.close();
        return linhasAfetadas;

    }

    public Cursor carregarUsuario(){

        Cursor cursor;
        String[] campos = {helper.COLUNA_ID_USUARIO, helper.COLUNA_NOME, helper.COLUNA_LOGIN, helper.COLUNA_SENHA, helper.COLUNA_EMAIL};

        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.query(helper.TABELA_USUARIO, campos, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }

    public Usuario validarUsuario(String login, String senha){

        String sql = "SELECT * FROM " + UsuarioSQLHelper.TABELA_USUARIO + " WHERE login = ? AND senha = ?";
        String[] selectionArgs = new String[] { login, senha };
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        cursor.moveToFirst();
        return montarUsuario(cursor);

        /*
        *
        * Esse aqui era anterior

        Cursor cursor;

        String[] campos = { helper.COLUNA_ID, helper.COLUNA_LOGIN, helper.COLUNA_SENHA, helper.COLUNA_NOME, helper.COLUNA_EMAIL};
        String where = UsuarioSQLHelper.COLUNA_LOGIN + " = " + login + " AND " + UsuarioSQLHelper.COLUNA_SENHA + " = " + senha;
        SQLiteDatabase db = helper.getReadableDatabase();

        cursor = db.query(UsuarioSQLHelper.TABELA_USUARIO, campos, where, null, null, null, null);

        if(cursor != null){

            cursor.moveToFirst();

        }

        db.close();

        return montarUsuario(cursor);
    */
    }

    public Usuario montarUsuario(Cursor cursor){

        if(cursor.getCount() == 0){

            return null;

        }

        long id = cursor.getLong(cursor.getColumnIndex("_id"));
        String login = cursor.getString(cursor.getColumnIndex("login"));
        String senha = cursor.getString(cursor.getColumnIndex("senha"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
        String nome = cursor.getString(cursor.getColumnIndex("nome"));

        return new Usuario(id, nome, senha, email, login);
    }

}
