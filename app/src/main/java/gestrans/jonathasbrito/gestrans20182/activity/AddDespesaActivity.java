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

import gestrans.jonathasbrito.gestrans20182.DAO.DespesaRepositorio;
import gestrans.jonathasbrito.gestrans20182.DAO.ReceitaRepositorio;
import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.models.Despesa;
import gestrans.jonathasbrito.gestrans20182.models.Receita;

public class AddDespesaActivity extends AppCompatActivity {

    private TextInputEditText valor;
    private Spinner tipoDespesa;

    private String tipo;
    private Despesa despesa;

    private static final String[] opcoesDespesa = {
            "Aluguel",
            "Combustível",
            "Seguro",
            "Prestação",
            "Manutenção"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_despesa);

        valor = findViewById(R.id.textInputValorDespesaId);
        tipoDespesa = findViewById(R.id.spinnerTipoDespesaId);

        ArrayAdapter<String> adapterOpcoesReceita = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, opcoesDespesa);
        tipoDespesa.setAdapter(adapterOpcoesReceita);

        tipoDespesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo = tipoDespesa.getItemAtPosition(position).toString();

                Toast.makeText(AddDespesaActivity.this, tipo, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_add_despesa, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.itemSalvarDespesa:
                //Executa a ação para o item salvar
                DespesaRepositorio despesaRepositorio = new DespesaRepositorio(getBaseContext());

                String valorDespesa = valor.getText().toString();
                String tipoDesp = tipoDespesa.getSelectedItem().toString();

                String hora = "23:40";

                despesa = new Despesa(valorDespesa, hora, tipoDesp);
                long id = despesaRepositorio.inserir(despesa);

                if(id != -1){

                    Toast.makeText(AddDespesaActivity.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddDespesaActivity.this, DespesasActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(AddDespesaActivity.this, "Erro ao realizar cadastro", Toast.LENGTH_LONG).show();

                }


                Toast.makeText(AddDespesaActivity.this, "Botão salvar pressionado", Toast.LENGTH_LONG).show();



                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
