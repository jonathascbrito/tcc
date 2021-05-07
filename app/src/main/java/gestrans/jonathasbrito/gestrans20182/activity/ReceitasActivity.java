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

import gestrans.jonathasbrito.gestrans20182.DAO.ReceitaRepositorio;
import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.adapter.ReceitaAdapter;
import gestrans.jonathasbrito.gestrans20182.helpers.RecyclerItemClickListener;
import gestrans.jonathasbrito.gestrans20182.models.Receita;

public class ReceitasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReceitaAdapter receitaAdapter;
    private List<Receita> listaReceitas = new ArrayList<>();
    private Receita receitaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurando o RecyclerView
        recyclerView = findViewById(R.id.recyclerListaReceitas);

        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener(){
                            @Override
                            public void onItemClick(View view, int position){

                                //Recuperar receita para edição
                                Receita receitaSelecionada = listaReceitas.get(position);

                                //Envia receita para tela adicionar receita
                                Intent intent = new Intent(ReceitasActivity.this, AddReceitaActivity.class);
                                intent.putExtra("receitaSelecionada", receitaSelecionada);

                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View vew, int position){

                                //Recupera receita para deletar
                                receitaSelecionada = listaReceitas.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(ReceitasActivity.this);

                                //Configura titulo e mensagem do Dialog
                                dialog.setTitle("Confirmar exclusão");
                                dialog.setMessage("Deseja excluir a receita" + receitaSelecionada.getValor() + " , " + receitaSelecionada.getTipo() + "?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        ReceitaRepositorio receitaRepositorio = new ReceitaRepositorio(getBaseContext());
                                        if(receitaRepositorio.excluir(receitaSelecionada)){
                                            carregarListaReceitas();
                                            Toast.makeText(getApplicationContext(), "Sucesso ao Excluir Receita!", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Erro ao excluir Receita!", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(getApplicationContext(), AddReceitaActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    public void carregarListaReceitas(){
/*
        //listar Tarefas
        Receita receita1 = new Receita();
        receita1.setHora("20:40");
        receita1.setTipo("Corridas");
        receita1.setValor("78");

        listaReceitas.add(receita1);

        Receita receita2 = new Receita();
        receita2.setHora("20:40");
        receita2.setTipo("Passeio");
        receita2.setValor("28");

        listaReceitas.add(receita2);

        Receita receita3 = new Receita();
        receita3.setHora("20:40");
        receita3.setTipo("Corrida");
        receita3.setValor("14");

        listaReceitas.add(receita3);

        Receita receita4 = new Receita();
        receita4.setHora("20:40");
        receita4.setTipo("Fretamento");
        receita4.setValor("43");

        listaReceitas.add(receita4);

        Receita receita5 = new Receita();
        receita5.setHora("20:40");
        receita5.setTipo("Corrida");
        receita5.setValor("25");

        listaReceitas.add(receita5);

*/

        //Lista de Receitas sendo carregadas
        ReceitaRepositorio receitaRepositorio = new ReceitaRepositorio(getBaseContext());
        listaReceitas = receitaRepositorio.listar();


        /*
            Exibe lista de tarefas no RecyclerView
         */

        //Configurar um Adapter
        receitaAdapter = new ReceitaAdapter( listaReceitas );


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter( receitaAdapter );


    }

    @Override
    protected void onStart() {
        carregarListaReceitas();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_receitas, menu);
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
