package br.com.gestrans.gestorfinanceiro.gestrans.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.gestrans.gestorfinanceiro.gestrans.R;
import br.com.gestrans.gestorfinanceiro.gestrans.helper.Permissao;

public class LoginActivity extends AppCompatActivity {

    private EditText emailLogin;
    private EditText password;
    private Button buttonEntrar;
    private String[] permissoesNecessarias = new String[]{

            Manifest.permission.SEND_SMS,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.SET_ALARM,
            Manifest.permission.VIBRATE

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissao.validaPermissoes(1, this, permissoesNecessarias);


        emailLogin = (EditText)findViewById(R.id.loginEmailId);
        password = (EditText)findViewById(R.id.passLoginId);
        buttonEntrar = (Button) findViewById(R.id.buttonLoginId);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }

    /**
     * Esse metodo é chamado todas as vezes que o usuário nega alguma das permissões
     * @param requestCode codigo para identificação de onde partiu a solicitação
     * @param permissions as permissões solicitadas
     * @param grandResults tem todas as verificas das permissoes se elas foram negadas ou não
     */
    public void onRequestPermissionsResult( int requestCode, String[] permissions, int[] grandResults ){

        super.onRequestPermissionsResult( requestCode, permissions, grandResults );

        for( int resultado : grandResults ) {

            if(resultado == PackageManager.PERMISSION_DENIED){

                alertaValidaçãoPermissao();

            }

        }


    }

    /**
     * Emite um alerta informando sobre a necessidade de aceitar as permissões para funcionamento
     * do app
     */
    public void alertaValidaçãoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizadar esse app, é necessário aceitar as permissões");

        //Evento ao clicar no botão de confirmação
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        //Criando a caixa de diálogo
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void abrirCadastroUsuario(View view){


        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity( intent );


    }

}
