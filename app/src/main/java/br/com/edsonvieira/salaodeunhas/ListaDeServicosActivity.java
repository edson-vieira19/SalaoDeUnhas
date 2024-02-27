package br.com.edsonvieira.salaodeunhas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class ListaDeServicosActivity extends AppCompatActivity {

    private ListView listViewServicos;
    private ArrayList<Servico> arrayListServicos;
    private Servico servico;

    private ServicoAdapter servicoAdapter;

    public static void nova(AppCompatActivity activity){

        Intent intent = new Intent(activity, ListaDeServicosActivity.class);

        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_servicos);

    setTitle(getString(R.string.servicos));

    listViewServicos = findViewById(R.id.listViewServicos);

    popularListViewServicos();

    registerForContextMenu(listViewServicos);


    } //fim metodo OnCreate


    private void popularListViewServicos() {

        String[] nomes = getResources().getStringArray(R.array.nomes_servicos);
        int[] duracao = getResources().getIntArray(R.array.duracao_servicos);
        int[] preco = getResources().getIntArray(R.array.preco_servicos);

        arrayListServicos = new ArrayList<>();

        for(int i=0; i < nomes.length; i++ ){

            double valorDouble = (double) preco[i];

            arrayListServicos.add(new Servico(nomes[i],valorDouble, duracao[i]));
        }

        servicoAdapter = new ServicoAdapter(this, arrayListServicos);

        listViewServicos.setAdapter(servicoAdapter);

    }

    ActivityResultLauncher<Intent> launcherNovoServico =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {

                            if (result.getResultCode() == Activity.RESULT_OK) {

                                Intent intent = result.getData();

                                Bundle bundle = intent.getExtras();

                                if (bundle != null) {

                                    String descricao = bundle.getString
                                            (CadastroDeServicosActivity.DESCRICAO);

                                    double preco = Double.parseDouble(
                                            Objects.requireNonNull
                                                    (bundle.getString
                                                            (CadastroDeServicosActivity.PRECO))
                                    );

                                    int duracao = Integer.parseInt(
                                            Objects.requireNonNull(bundle.getString
                                                    (CadastroDeServicosActivity.DURACAO))
                                    );

                                    servico = new Servico(descricao, preco, duracao);

                                    arrayListServicos.add(servico);

                                    listViewServicos.deferNotifyDataSetChanged();

                                }
                            }
                        }
                    });

    public void sobre(View view){

        SobreActivity.nova(this);

    }

    public void novoServico(View view){

        CadastroDeServicosActivity.novoServico(this, launcherNovoServico);

    }

}