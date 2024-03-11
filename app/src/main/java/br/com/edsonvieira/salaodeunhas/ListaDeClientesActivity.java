package br.com.edsonvieira.salaodeunhas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ListaDeClientesActivity extends AppCompatActivity {

    private static final String TAG = "ListaDeClientesActivity";
    private List<Cliente> arrayListClientes;
    private RecyclerView recyclerViewListaClientes;
    private RecyclerView.LayoutManager layoutManager;
    private ClienteAdapter clienteAdapter;


    public static void nova(AppCompatActivity activity){

        Intent intent = new Intent(activity, ListaDeClientesActivity.class);

        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_clientes);

        setTitle(getString(R.string.clientes));

        recyclerViewListaClientes = findViewById(R.id.recyclerViewListaClientes);

        layoutManager = new LinearLayoutManager(this);

        recyclerViewListaClientes.setLayoutManager(layoutManager);
        recyclerViewListaClientes.setHasFixedSize(true);
        recyclerViewListaClientes.addItemDecoration(new DividerItemDecoration(
                this, LinearLayout.VERTICAL));

        popularLista();
    }

    private void popularLista() {

        arrayListClientes = new ArrayList<>();

        String[] nomes = getResources().getStringArray(R.array.nomes_clientes);
        String[] enderecos = getResources().getStringArray(R.array.endereco_clientes);
        String[] celulares = getResources().getStringArray(R.array.celular_clientes);
        String[] instagrans = getResources().getStringArray(R.array.instagram_clientes);
        String[] micoses = getResources().getStringArray(R.array.micose_clientes);
        String[] unhaEncravadas = getResources().getStringArray(R.array.unha_encravada_clientes);
        String[] manchas = getResources().getStringArray(R.array.manchas_clientes);
        String[] unhaQuebradicas = getResources().getStringArray(R.array.unha_quebradica_clientes);
        String[] descolamentos = getResources().getStringArray(R.array.descolamento_clientes);
        String[] outros = getResources().getStringArray(R.array.outro_clientes);
        String[] diabetes = getResources().getStringArray(R.array.diabetes_clientes);
        String[] fumantes = getResources().getStringArray(R.array.fumante_clientes);
        String[] atividadeDesgaste = getResources().getStringArray(R.array.atividade_desgaste_clientes);
        String[] produtoAlergia = getResources().getStringArray(R.array.produto_alergia_clientes);

        for (int cont = 0; cont < nomes.length; cont++){

           arrayListClientes.add(new Cliente(nomes[cont],
                   enderecos[cont],celulares[cont],instagrans[cont],
                   Boolean.parseBoolean(micoses[cont]),
                   Boolean.parseBoolean(unhaEncravadas[cont]),
                   Boolean.parseBoolean(manchas[cont]),
                   Boolean.parseBoolean(unhaQuebradicas[cont]),
                   Boolean.parseBoolean(descolamentos[cont]),
                   Boolean.parseBoolean(outros[cont]),
                   Boolean.parseBoolean(diabetes[cont]),
                   Boolean.parseBoolean(fumantes[cont]),
                   Boolean.parseBoolean(atividadeDesgaste[cont]),
                   Produto.valueOf(produtoAlergia[cont])
                   ));
            Log.d(TAG, "cliente " + cont + " adicionado na lista " +
                    arrayListClientes.get(cont).getNome());
        }



        clienteAdapter = new ClienteAdapter(arrayListClientes);

        recyclerViewListaClientes.setAdapter(clienteAdapter);

    }
}