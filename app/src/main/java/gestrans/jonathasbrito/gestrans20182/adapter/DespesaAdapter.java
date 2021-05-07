package gestrans.jonathasbrito.gestrans20182.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gestrans.jonathasbrito.gestrans20182.R;
import gestrans.jonathasbrito.gestrans20182.models.Despesa;
/**
 * Created by Brito on 20/11/2018.
 */

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.MyViewHolder> {

    private List<Despesa> listaDespesas;

    public DespesaAdapter(List<Despesa> lista) {

        this.listaDespesas = lista;

    }

    @Override
    public DespesaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_despesa_adapter, parent, false);

        return new DespesaAdapter.MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(DespesaAdapter.MyViewHolder holder, int position) {

        Despesa despesa = listaDespesas.get(position);
        holder.hora.setText(despesa.getHora());
        holder.tipo.setText(despesa.getTipo());
        holder.valor.setText(despesa.getValor());

    }

    @Override
    public int getItemCount() {

        return this.listaDespesas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView valor;
        TextView hora;
        TextView tipo;

        public MyViewHolder(View itemView){
            super(itemView);

            hora = itemView.findViewById(R.id.horaId);
            valor = itemView.findViewById(R.id.textViewValorDespesaId);
            tipo = itemView.findViewById(R.id.textViewTipoDespesaId);

        }

    }


}
