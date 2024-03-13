package br.com.edsonvieira.salaodeunhas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyViewHolder>{

    private List<Cliente> listaDeClientes;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewValorNomeCliente;
        TextView textViewCelularCliente;
        TextView textViewTratamentoDiferenciado;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewValorNomeCliente = itemView.
                    findViewById(R.id.textViewValorNomeCliente);
            textViewCelularCliente = itemView.
                    findViewById(R.id.textViewCelularCliente);
            textViewTratamentoDiferenciado = itemView.
                    findViewById(R.id.textViewTratamentoDiferenciado);
        }
    }
    public ClienteAdapter(Context context, List<Cliente> listaDeClientes){
        this.listaDeClientes = listaDeClientes;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext()).
                                        inflate(R.layout.linha_lista_clientes,
                                                viewGroup, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        Cliente cliente = listaDeClientes.get(i);


        holder.textViewValorNomeCliente.setText(cliente.getNome());
        holder.textViewCelularCliente.setText(cliente.getCelular());

        if (cliente.isDiabetes() || cliente.isAtividadeDesgaste() ||
                cliente.isDescolamento() || cliente.isManchas() || cliente.isMicose()
                || cliente.isUnha_quebradica()
                || cliente.isUnhaEncravada() || cliente.isFumante()
                || !cliente.getProdutoEscolhido().getNome().equals(
                        context.getString(R.string.nenhum))
        ) {
            holder.textViewTratamentoDiferenciado.setText(context.getString(R.string.sim));
        }
        else{
            holder.textViewTratamentoDiferenciado.setText(context.getString(R.string.nao));
        }
    }
    @Override
    public int getItemCount() {
        return listaDeClientes.size();
    }
}
