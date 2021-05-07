package gestrans.jonathasbrito.gestrans20182.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gestrans.jonathasbrito.gestrans20182.DAO.UsuarioRepositorio;
import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.models.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText pass;
    private TextView cadastro;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginId);
        pass = findViewById(R.id.passwordId);

        buttonLogin = findViewById(R.id.buttonLoginId);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(login.getText().toString().equals("")){

                    Toast.makeText(LoginActivity.this, "Por favor digite o login!", Toast.LENGTH_SHORT).show();

                } else if(pass.getText().toString().equals("")){

                    Toast.makeText(LoginActivity.this, "Por favor digite a senha!", Toast.LENGTH_SHORT).show();

                } else {

                    try{

                        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio(getBaseContext());

                        Usuario usuarioLogin = usuarioRepositorio.validarUsuario(login.getText().toString(), pass.getText().toString());

                        if(usuarioLogin != null){

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {

                            Toast.makeText(LoginActivity.this, "Login ou senha incorreto", Toast.LENGTH_SHORT).show();

                        }

                        if(login.getText().toString().equals("Jonathas") && pass.getText().toString().equals("123456")){


                        } else {


                        }


                    } catch(Exception e){

                        e.printStackTrace();

                    }


                }

            }
        });


    }


    public void irCadastro(View view){

        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
}
