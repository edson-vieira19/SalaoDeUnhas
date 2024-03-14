package br.com.edsonvieira.salaodeunhas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.edsonvieira.salaodeunhas.model.Servico;

public class ServicoAdapter extends BaseAdapter {

    private Context context;

    private List<Servico> servicos;

    private NumberFormat numberFormat;

    private static class ServicoHolder{

        public TextView textViewValorDescricao;
        public TextView textViewValorPreco;
        public TextView textViewValorDuracao;

    }

    public ServicoAdapter(Context context, List<Servico> servicos){
        this.context = context;
        this.servicos = servicos;

        numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    }



    @Override
    public int getCount() {
        return servicos.size();
    }

    @Override
    public Object getItem(int i) {
        return servicos.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ServicoHolder holder;

        if(view == null){

            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view =
                    inflater.inflate(R.layout.linha_lista_servicos, viewGroup, false);

            holder = new ServicoHolder();

            holder.textViewValorDescricao = view.findViewById(R.id.textViewValorDescricao);
            holder.textViewValorDuracao = view.findViewById(R.id.textViewValorDuracao);
            holder.textViewValorPreco = view.findViewById(R.id.textViewValorPreco);

            view.setTag(holder);

        }else{
            holder = (ServicoHolder) view.getTag();
        }

        holder.textViewValorDescricao.setText(servicos.get(i).getDescricao());
        holder.textViewValorDuracao.setText(String.format("%d min", servicos.get(i).getDuracao()));
        holder.textViewValorPreco.setText(numberFormat.format(servicos.get(i).getPreco()));

        return view;
    }
}
