package br.com.edsonvieira.salaodeunhas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
    private View viewSelecionada;
    private ActionMode actionMode;
    private int posicaoSelecionada = -1;

    public static void nova(AppCompatActivity activity) {


        Intent intent = new Intent(activity, ListaDeServicosActivity.class);

        activity.startActivity(intent);

    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.lista_servicos_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int menuItemSelecionado = item.getItemId();

            if (menuItemSelecionado == R.id.menuItemEditar_lista_servicos) {

                editarServico();

                mode.finish();

                return true;
            }
            if (menuItemSelecionado == R.id.meuItemExcluir_lista_servicos) {

                excluirServico();

                mode.finish();

                return true;
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }
            actionMode = null;
            viewSelecionada = null;
            listViewServicos.setEnabled(true);
        }
    };

    private void editarServico() {

        Servico servicoAEditar = arrayListServicos.get(posicaoSelecionada);

        CadastroDeServicosActivity.editarServico(this,
                launcherEditarServico, servicoAEditar);

    }
    private void excluirServico() {
        arrayListServicos.remove(posicaoSelecionada);
        servicoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_servicos);

        setTitle(getString(R.string.lista_de_servicos));

        listViewServicos = findViewById(R.id.listViewServicos);

        popularListViewServicos();

        registerForContextMenu(listViewServicos);

        listViewServicos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewServicos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                if (actionMode != null) {
                    return false;
                }

                posicaoSelecionada = position;

                view.setBackgroundColor(Color.LTGRAY);

                viewSelecionada = view;

                listViewServicos.setEnabled(false);

                actionMode = startSupportActionMode(actionModeCallback);

                return false;
            }
        });
    }

    private void popularListViewServicos() {

        String[] nomes = getResources().getStringArray(R.array.nomes_servicos);
        int[] duracao = getResources().getIntArray(R.array.duracao_servicos);
        int[] preco = getResources().getIntArray(R.array.preco_servicos);

        arrayListServicos = new ArrayList<>();

        for (int i = 0; i < nomes.length; i++) {

            double valorDouble = (double) preco[i];

            arrayListServicos.add(new Servico(nomes[i], valorDouble, duracao[i]));
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

                                    servicoAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

    ActivityResultLauncher<Intent> launcherEditarServico =
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

                                    servico = arrayListServicos.get(posicaoSelecionada);
                                    servico.setDescricao(descricao);
                                    servico.setPreco(preco);
                                    servico.setDuracao(duracao);

                                    posicaoSelecionada = -1;

                                    servicoAdapter.notifyDataSetChanged();

                                }
                            }
                        }
                    });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.lista_servicos_opcoes, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuItemSelecionado = item.getItemId();

        if (menuItemSelecionado == R.id.menuItemAdicionar_lista_de_servicos) {

            CadastroDeServicosActivity.novoServico(this, launcherNovoServico);

            return true;
        }
        if (menuItemSelecionado == R.id.menuItemSobre_lista_de_servicos) {

            SobreActivity.nova(this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}