package br.com.edsonvieira.salaodeunhas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class ListaDeServicosActivity extends AppCompatActivity {

    private ListView listViewServicos;
    private ArrayList<Servico> servicos;
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

        listViewServicos.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                        Servico servico = (Servico) listViewServicos.getItemAtPosition(i);

                        Toast.makeText(getApplicationContext(),
                                getString(R.string.o_servi_o)
                                        + servico.getDescricao() + " "
                                        + getString(R.string.foi_selecionado),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

    popularListViewServicos();

    } //fim OnCreate


    private void popularListViewServicos() {

        String[] nomes = getResources().getStringArray(R.array.nomes_servicos);
        int[] duracao = getResources().getIntArray(R.array.duracao_servicos);
        int[] preco = getResources().getIntArray(R.array.preco_servicos);

        servicos = new ArrayList<>();

        for(int i=0; i < nomes.length; i++ ){

            double valorDouble = (double) preco[i];

            servicos.add(new Servico(nomes[i],valorDouble, duracao[i]));
        }

        servicoAdapter = new ServicoAdapter(this, servicos);

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

                                    servicos.add(servico);

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

} //fim Activity