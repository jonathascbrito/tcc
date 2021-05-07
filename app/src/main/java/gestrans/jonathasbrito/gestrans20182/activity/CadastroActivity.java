package gestrans.jonathasbrito.gestrans20182.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gestrans.jonathasbrito.gestrans20182.DAO.UsuarioRepositorio;
import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.models.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText login;
    private EditText pass;
    private EditText confPass;
    private EditText email;
    private EditText nome;
    private Button buttonCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        login = (EditText)findViewById(R.id.loginCadId);
        pass = (EditText) findViewById(R.id.passCadId);
        confPass = (EditText)findViewById(R.id.passConfId);
        nome = (EditText)findViewById(R.id.nomeCadId);
        email = (EditText)findViewById(R.id.emailCadId);

        buttonCadastro = (Button)findViewById(R.id.buttonCadastroId);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(login.getText().toString().equals("") || pass.getText().toString().equals("") || confPass.getText().toString().equals("") || nome.getText().toString().equals("") || email.getText().toString().equals("")){

                    Toast.makeText(CadastroActivity.this, "Por favor digite as informações", Toast.LENGTH_SHORT).show();


                } else if(pass.getText().toString().equals(confPass.getText().toString())){


                    UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(getBaseContext());

                    String nomeUsuario = nome.getText().toString();
                    String senhaUsuario = pass.getText().toString();
                    String emailUsuario = email.getText().toString();
                    String loginUsuario = login.getText().toString();

                    Usuario usuario = new Usuario(nomeUsuario, senhaUsuario, emailUsuario, loginUsuario);

                    long id = usuarioRepositorio.inserir(usuario);

                    if(id != -1) {
                        Toast.makeText(CadastroActivity.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(CadastroActivity.this, "Erro ao realzar o cadastro", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(CadastroActivity.this, "Senha diferente na confirmação", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
