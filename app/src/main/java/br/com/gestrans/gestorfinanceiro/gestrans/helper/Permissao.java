package br.com.gestrans.gestorfinanceiro.gestrans.helper;

/**
 * Created by Brito on 06/09/2017.
 */

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

    /**
     * Método que valida as permissões de funcionamento do aplicativo caso seja Api a partir da 23
     *
     * @param requestCode
     * @param activity
     * @param permissoes
     * @return
     */
    public static boolean validaPermissoes(int requestCode, Activity activity, String[] permissoes ){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> listaPermissoes = new ArrayList<String>();

            /**
             * Percorre as permissões passadas, verifica uma a uma
             * se já tem permissão liberada
             */
            for(String permissao : permissoes){
                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if( !validaPermissao ) listaPermissoes.add(permissao);
            }

            /**
             * Caso a lista esteja vazia, não é necessário solicitar permissão
             */
            if(listaPermissoes.isEmpty()) return true;

            /**
             * Convertando Array List para String para ser utilizado pelo metodo requestPermissions
             */
            String[] novasPermissoes = new String[ listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);

            /**
             * Solicita as permissões
             */
            ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);

        }


        return true;
    }

}
