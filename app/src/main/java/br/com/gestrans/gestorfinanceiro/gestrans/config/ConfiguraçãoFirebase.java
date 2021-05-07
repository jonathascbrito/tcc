package br.com.gestrans.gestorfinanceiro.gestrans.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Classe para criação de uma referência para conexão com o firebase
 */
public final class ConfiguraçãoFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;


    /**
     * Inicia uma instancia para referencia do database
     * @return
     */
    public static DatabaseReference getFirebase(){

        if(referenciaFirebase == null)
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();


        return referenciaFirebase;

    }

    /**
     * Inicia um instancia para autenticação de usuário
     * @return
     */
    public static FirebaseAuth getFirebaseAutenticacao(){

        if( autenticacao == null )
            autenticacao = FirebaseAuth.getInstance();

        return autenticacao;

    }


}
