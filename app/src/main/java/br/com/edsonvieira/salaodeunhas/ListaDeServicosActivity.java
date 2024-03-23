package br.com.edsonvieira.salaodeunhas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import br.com.edsonvieira.salaodeunhas.model.Servico;
import br.com.edsonvieira.salaodeunhas.persistencia.SalaoDeUnhasDatabase;
import br.com.edsonvieira.salaodeunhas.utils.UtilsGui;

public class ListaDeServicosActivity extends AppCompatActivity {

    public static final String ARQUIVO = "br.com.edsonvieira.salaodeunhas.PREFERENCIAS";
    public static final String ORDENACAO_ASCENDENTE = "ORDENACAO_ASCENDENTE";
    private boolean ordenacaoAscendente = true;
    private ListView listViewServicos;
    private List<Servico> listaServicos;
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

                excluirServico(mode);

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

        Servico servicoAEditar = listaServicos.get(posicaoSelecionada);

        CadastroDeServicosActivity.editarServico(this,
                launcherEditarServico, servicoAEditar);

    }

    private void excluirServico(final ActionMode mode) {

        final Servico servico = listaServicos.get(posicaoSelecionada);

        String mensagem = getString(R.string.deseja_realmente_excluir) + "\n " + "\"" +
                servico.getDescricao() + "\"";

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {

                    case DialogInterface.BUTTON_POSITIVE:

                        SalaoDeUnhasDatabase database = SalaoDeUnhasDatabase
                                .getDatabase(ListaDeServicosActivity.this);

                        int qtdAlterada = database.getServicoDao().delete(servico);

                        if (qtdAlterada > 0) {
                            listaServicos.remove(posicaoSelecionada);
                            servicoAdapter.notifyDataSetChanged();
                            mode.finish();

                        } else {
                            UtilsGui.avisoErro(ListaDeServicosActivity.this,
                                    getString(R.string.erro_ao_tentar_apagar));
                        }
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;

                }
            }
        };

        UtilsGui.confirmaAcao(this, mensagem, listener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_servicos);

        setTitle(getString(R.string.lista_de_servicos));

        listViewServicos = findViewById(R.id.listViewServicos);

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

        popularBanco();

        lerPreferenciasOrdenacao();

        popularListViewServicos();

    }

    private void popularListViewServicos() {

        SalaoDeUnhasDatabase database = SalaoDeUnhasDatabase.getDatabase(this);

        listaServicos = database.getServicoDao().queryAllAscending();

        servicoAdapter = new ServicoAdapter(this, listaServicos);

        ordenarLista();

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

                                    SalaoDeUnhasDatabase database =
                                            SalaoDeUnhasDatabase.getDatabase(
                                                    ListaDeServicosActivity.this);

                                    long id = bundle.getLong(CadastroDeServicosActivity.ID);

                                    Servico servicoInserido =
                                            database.getServicoDao().queryForId(id);

                                    listaServicos.add(servicoInserido);

                                    ordenarLista();

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

                                    SalaoDeUnhasDatabase database = SalaoDeUnhasDatabase.
                                            getDatabase(ListaDeServicosActivity.this);

                                    long id = bundle.getLong(CadastroDeServicosActivity.ID);

                                    Servico servicoEditado =
                                            database.getServicoDao().queryForId(id);

                                    listaServicos.set(posicaoSelecionada, servicoEditado);

                                    ordenarLista();

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
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem menuItemOrdenacao = menu.findItem(R.id.menuItemOrdenacao_lista_de_servicos);

        atualizarIconeOrdenacao(menuItemOrdenacao);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuItemSelecionado = item.getItemId();

        if (menuItemSelecionado == R.id.menuItemAdicionar_lista_de_servicos) {

            CadastroDeServicosActivity.novoServico(this, launcherNovoServico);

            return true;
        }
        if(menuItemSelecionado == R.id.menuItemOrdenacao_lista_de_servicos){

            salvarPreferenciasOrdenacao(!ordenacaoAscendente);

            atualizarIconeOrdenacao(item);

            ordenarLista();

            servicoAdapter.notifyDataSetChanged();

            return true;
        }
        if (menuItemSelecionado == R.id.menuItemSobre_lista_de_servicos) {

            SobreActivity.nova(this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void popularBanco() {

        //cadastra alguns servicos iniciais no banco

        SalaoDeUnhasDatabase database = SalaoDeUnhasDatabase.getDatabase(this);

        int numeroLinhas = database.getServicoDao().countServicos();

        if (numeroLinhas == 0){
            String[] nomes = getResources().getStringArray(R.array.nomes_servicos);
            int[] duracao = getResources().getIntArray(R.array.duracao_servicos);
            int[] preco = getResources().getIntArray(R.array.preco_servicos);

             for (int i = 0; i < nomes.length; i++) {

            double valorDouble = (double) preco[i];

            Servico servico = new Servico(nomes[i], valorDouble, duracao[i]);

            database.getServicoDao().insert(servico);

             }
        }
    }

    private void atualizarIconeOrdenacao(MenuItem menuItemOrdenacao) {

        if (ordenacaoAscendente) {
            menuItemOrdenacao.setIcon(R.drawable.ic_action_ordenacao_ascendente);
        } else {
            menuItemOrdenacao.setIcon(R.drawable.ic_action_ordenacao_descendente);
        }
    }

    private void ordenarLista() {

        Log.d("ordenar lista", "metodo ordenar lista foi chamado ");

        if (ordenacaoAscendente) {
            Collections.sort(listaServicos, Servico.ordenacaoCrescente);
        } else {
            Collections.sort(listaServicos, Servico.ordenacaoDecrescente);
        }

    }

    private void lerPreferenciasOrdenacao(){

        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        ordenacaoAscendente = shared.getBoolean(ORDENACAO_ASCENDENTE, ordenacaoAscendente);

    }

    private void salvarPreferenciasOrdenacao(boolean novoValor){

        SharedPreferences shared = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putBoolean(ORDENACAO_ASCENDENTE, novoValor );

        editor.commit();

        ordenacaoAscendente = novoValor;

    }
}



