package br.com.edsonvieira.salaodeunhas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.edsonvieira.salaodeunhas.model.Servico;
import br.com.edsonvieira.salaodeunhas.persistencia.SalaoDeUnhasDatabase;
import br.com.edsonvieira.salaodeunhas.utils.UtilsGui;

public class CadastroDeServicosActivity extends AppCompatActivity {

    public static final String MODO = "MODO";
    public static final int NOVO = 1;
    public static final int EDITAR = 2;
    private int modo;
    public static final String ID = "ID";
    private EditText editTextDescricaoServico;
    private EditText editTextPrecoServico;
    private EditText editTextDuracaoServico;

    private TextView textViewCadastrarEditarServico;
    private Servico servicoEditar;

    public static void novoServico(AppCompatActivity activity,
                                   ActivityResultLauncher<Intent> launcher) {

        Intent intent = new Intent(activity, CadastroDeServicosActivity.class);

        intent.putExtra(MODO, NOVO);

        launcher.launch(intent);
    }

    public static void editarServico(AppCompatActivity activity,
                                     ActivityResultLauncher<Intent> launcher,
                                     Servico servico) {

        Intent intent = new Intent(activity, CadastroDeServicosActivity.class);

        intent.putExtra(MODO, EDITAR);
        intent.putExtra(ID, servico.getId());

        launcher.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_servicos);

        setTitle(getString(R.string.servicos));

        editTextDescricaoServico = findViewById(R.id.editTextDescricaoServico);
        editTextPrecoServico = findViewById(R.id.editTextPrecoServico);
        editTextDuracaoServico = findViewById(R.id.editTextDuracaoServico);
        textViewCadastrarEditarServico = findViewById(R.id.textViewCadastrarRegistrarServico);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {

            modo = bundle.getInt(MODO, NOVO);

            if (modo == NOVO) {

                setTitle(getString(R.string.novo_servico));
                textViewCadastrarEditarServico.setText(R.string.cadastro_de_servicos);

            } else if (modo == EDITAR) {

                setTitle(getString(R.string.editarservico));
                textViewCadastrarEditarServico.setText(R.string.editar_um_servico);

                long idServico = bundle.getLong(ID);

                SalaoDeUnhasDatabase database
                        = SalaoDeUnhasDatabase.getDatabase(this);

                servicoEditar = database.getServicoDao().queryForId(idServico);

                editTextDescricaoServico.setText(servicoEditar.getDescricao());
                editTextPrecoServico.setText(String.valueOf(servicoEditar.getPreco()));
                editTextDuracaoServico.setText(String.valueOf(servicoEditar.getDuracao()));

                editTextDescricaoServico.requestFocus();
                
                editTextDescricaoServico.setSelection(editTextDescricaoServico.getText().length());

            }
        }
    }

    private void salvar() {

        StringBuilder mensagem = new StringBuilder();

        boolean valido = true;

        String descricao = editTextDescricaoServico.getText().toString();

        if (descricao.trim().isEmpty()) {
            mensagem.append(getString(R.string.a_descricao_nao_pode_ser_vazia)).append("\n");
            valido = false;
        }

        String preco = editTextPrecoServico.getText().toString();

        if (preco.trim().isEmpty()) {
            mensagem.append(getString(R.string.o_pre_o_n_o_pode_ser_vazio)).append("\n");
            valido = false;
        }

        String duracao = editTextDuracaoServico.getText().toString();

        if (duracao.trim().isEmpty()) {
            mensagem.append(getString(R.string.a_duracao_nao_pode_ser_vazio));
            valido = false;
        }

        if (!valido) {

            UtilsGui.avisoErro(this, mensagem.toString());

            editTextDescricaoServico.requestFocus();

            return;
        }

        if(modo == EDITAR && descricao.equals(servicoEditar.getDescricao())
            && preco.equals(String.valueOf(servicoEditar.getPreco()))
            && duracao.equals(String.valueOf(servicoEditar.getDuracao()))){
            cancelar();
            finish();
        }

        Intent intent = new Intent();

        SalaoDeUnhasDatabase database = SalaoDeUnhasDatabase.getDatabase(this);

        if (modo == NOVO) {

            Servico servico = new Servico(descricao,
                    Double.parseDouble(preco), Integer.parseInt(duracao));

            long novoID = database.getServicoDao().insert(servico);

            if (novoID <= 0) {
                UtilsGui.avisoErro(this,
                        getString(R.string.erro_ao_inserir_o_servico_no_banco));
                return;
            }

            servico.setId(novoID);

            intent.putExtra(ID, servico.getId());

        }  else {

                Servico servicoAlterado = new Servico(descricao,
                        Double.parseDouble(preco), Integer.parseInt(duracao));

                servicoAlterado.setId(servicoEditar.getId());

                int qtdLinhas = database.getServicoDao().update(servicoAlterado);

                if (qtdLinhas == 0) {
                    UtilsGui.avisoErro(this,
                            getString(R.string.erro_ao_atualizar_servico_no_banco));
                    return;
                }

                intent.putExtra(ID, servicoAlterado.getId());

            }

            Toast.makeText(this,getString(R.string.servico_cadastrado_com_sucesso)
                    ,Toast.LENGTH_SHORT).show();

            setResult(Activity.RESULT_OK, intent);

            finish();

        }  // fim salvar

    private void limpar() {

        editTextDescricaoServico.setText(null);
        editTextPrecoServico.setText(null);
        editTextDuracaoServico.setText(null);
        editTextDescricaoServico.requestFocus();

        Toast.makeText(this,
                getString(R.string.todos_os_campos_foram_limpos)
                , Toast.LENGTH_SHORT).show();
    }

    private void cancelar() {

        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.cadastro_servicos_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuItemSelecionado = item.getItemId();

        if (menuItemSelecionado == R.id.menuItemSalvar_cadastro_de_servicos) {
            salvar();
            return true;
        }
        if (menuItemSelecionado == R.id.menuItemLimpar_cadastro_de_servicos) {
            limpar();
            return true;
        }
        if(menuItemSelecionado == android.R.id.home){
            cancelar();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}