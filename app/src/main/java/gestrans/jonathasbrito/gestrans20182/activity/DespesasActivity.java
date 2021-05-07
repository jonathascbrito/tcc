package gestrans.jonathasbrito.gestrans20182.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gestrans.jonathasbrito.gestrans20182.DAO.DespesaRepositorio;
import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.adapter.DespesaAdapter;
import gestrans.jonathasbrito.gestrans20182.helpers.RecyclerItemClickListener;
import gestrans.jonathasbrito.gestrans20182.models.Despesa;

public class DespesasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DespesaAdapter despesaAdapter;
    private List<Despesa> listaDespesas = new ArrayList<>();
    private Despesa despesaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Configurando o RecyclerView
        recyclerView = findViewById(R.id.recyclerListaDespesas);

        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener(){
                            @Override
                            public void onItemClick(View view, int position){

                                //Recuperar receita para edição
                                Despesa despesaSelecionada = listaDespesas.get(position);

                                //Envia receita para tela adicionar receita
                                Intent intent = new Intent(DespesasActivity.this, AddReceitaActivity.class);
                                intent.putExtra("despesaSelecionada", despesaSelecionada);

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View vew, int position){

                                //Recupera receita para deletar
                                despesaSelecionada = listaDespesas.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(DespesasActivity.this);

                                //Configura titulo e mensagem do Dialog
                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir a despesa" + despesaSelecionada.getValor() + " , " + despesaSelecionada.getTipo() + "?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        DespesaRepositorio despesaRepositorio = new DespesaRepositorio(getBaseContext());
                                        if(despesaRepositorio.excluir(despesaSelecionada)){
                                            carregarListaDespesas();
                                            Toast.makeText(getApplicationContext(), "Sucesso ao Excluir Despesa!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Erro ao excluir Despesa!", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });

                                dialog.setNegativeButton("Não", null);

                                //Exibir dialog
                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                            }
                        }
                )

        );



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddDespesaActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    public void carregarListaDespesas(){

        //Lista de Receitas sendo carregadas
        DespesaRepositorio despesaRepositorio = new DespesaRepositorio(getBaseContext());
        listaDespesas = despesaRepositorio.listar();


        /*
            Exibe lista de tarefas no RecyclerView
         */

        //Configurar um Adapter
        despesaAdapter = new DespesaAdapter( listaDespesas );


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter( despesaAdapter );


    }

    @Override
    protected void onStart() {
        carregarListaDespesas();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_despesas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if( id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
