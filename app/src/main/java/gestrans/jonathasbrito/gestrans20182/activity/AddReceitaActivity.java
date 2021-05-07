package gestrans.jonathasbrito.gestrans20182.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import gestrans.jonathasbrito.gestrans20182.DAO.ReceitaRepositorio;
import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.models.Receita;

public class AddReceitaActivity extends AppCompatActivity {

    private TextInputEditText valor;
    private Spinner tipoReceita;

    private String tipo;
    private Receita receita;

    private static final String[] opcoesReceita = {
            "Aluguel",
            "Viagem Simples",
            "Transporte",
            "Fretamento",
            "Entrega"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receita);

        valor = findViewById(R.id.textInputValorReceitaId);
        tipoReceita = findViewById(R.id.spinnerTipoReceitaId);

        ArrayAdapter<String> adapterOpcoesReceita = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoesReceita);
        tipoReceita.setAdapter(adapterOpcoesReceita);

        tipoReceita.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo = tipoReceita.getItemAtPosition(position).toString();

                Toast.makeText(AddReceitaActivity.this, tipo, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_add_receita, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.itemSalvar:
                //Executa a ação para o item salvar
                ReceitaRepositorio receitaRepositorio = new ReceitaRepositorio(getBaseContext());

                String valorReceita = valor.getText().toString();
                String tipoRec = tipoReceita.getSelectedItem().toString();

                String hora = "23:40";

                receita = new Receita(valorReceita, tipoRec, hora);
                long id = receitaRepositorio.inserir(receita);

                if(id != -1){

                    Toast.makeText(AddReceitaActivity.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddReceitaActivity.this, ReceitasActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(AddReceitaActivity.this, "Erro ao realizar cadastro", Toast.LENGTH_LONG).show();

                }


                Toast.makeText(AddReceitaActivity.this, "Botão salvar pressionado", Toast.LENGTH_LONG).show();



                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
