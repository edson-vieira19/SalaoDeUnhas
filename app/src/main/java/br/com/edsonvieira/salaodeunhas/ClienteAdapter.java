package br.com.edsonvieira.salaodeunhas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.MyViewHolder>{

    private List<Cliente> listaDeClientes;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewValorNomeCliente;
        TextView textViewCelularCliente;
        TextView textViewTratamentoDiferenciado;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewValorNomeCliente = itemView.
                    findViewById(R.id.textViewNomeCliente);
            textViewCelularCliente = itemView.
                    findViewById(R.id.textViewCelularCliente);
            textViewTratamentoDiferenciado = itemView.
                    findViewById(R.id.textViewTratamentoDiferenciado);
        }
    }
    public ClienteAdapter(List<Cliente> listaDeClientes){
        this.listaDeClientes = listaDeClientes;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }







}
