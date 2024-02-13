package br.com.edsonvieira.salaodeunhas;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroDeServicosActivity extends AppCompatActivity {

    public static final String DESCRICAO = "DESCRICAO";
    public static final String PRECO = "PRECO";

    public static final String DURACAO = "DURACAO";

    private EditText editTextDescricaoServico;
    private EditText editTextPrecoServico;
    private EditText editTextDuracaoServico;

    public static void novoServico(AppCompatActivity activity,
                                   ActivityResultLauncher<Intent>launcher){

        Intent intent = new Intent(activity,CadastroDeServicosActivity.class);

        launcher.launch(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_servicos);

        editTextDescricaoServico = findViewById(R.id.editTextDescricaoServico);
        editTextPrecoServico = findViewById(R.id.editTextPrecoServico);
        editTextDuracaoServico = findViewById(R.id.editTextDuracaoServico);

    }

    public void salvar(View view) {

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
            Toast.makeText(this, mensagem.toString(), Toast.LENGTH_LONG).show();
            editTextDescricaoServico.requestFocus();
        }

        Intent intent = new Intent();

        intent.putExtra(CadastroDeServicosActivity.DESCRICAO, descricao);
        intent.putExtra(CadastroDeServicosActivity.PRECO, preco);
        intent.putExtra(CadastroDeServicosActivity.DURACAO, duracao);

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    public void limpar(View view){
        editTextDescricaoServico.setText(null);
        editTextPrecoServico.setText(null);
        editTextDuracaoServico.setText(null);
        editTextDescricaoServico.requestFocus();
    }

}