package gestrans.jonathasbrito.gestrans20182.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.models.Receita;

/**
 * Created by Brito on 18/11/2018.
 */

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.MyViewHolder> {

    private List<Receita> listaReceitas;

    public ReceitaAdapter(List<Receita> lista) {

        this.listaReceitas = lista;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_receita_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Receita receita = listaReceitas.get(position);
        holder.hora.setText(receita.getHora());
        holder.tipo.setText(receita.getTipo());
        holder.valor.setText(receita.getValor());

    }

    @Override
    public int getItemCount() {

        return this.listaReceitas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView valor;
        TextView hora;
        TextView tipo;

        public MyViewHolder(View itemView){
            super(itemView);

            hora = itemView.findViewById(R.id.horaId);
            valor = itemView.findViewById(R.id.textViewValorReceitaId);
            tipo = itemView.findViewById(R.id.textViewTipoReceitaId);

        }

    }

}
