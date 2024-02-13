package br.com.edsonvieira.salaodeunhas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CadastroDeClienteActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEndereco;
    private  EditText editTextCelular;
    private EditText editTextInstagram;

    private CheckBox checkBoxMicose;
    private CheckBox checkBoxUnhaEncravada;
    private CheckBox checkBoxMancha;
    private CheckBox checkBoxUnhaQuebradica;

    private CheckBox checkBoxDescolamento;
    private CheckBox checkBoxOutro;

    private RadioGroup radioGroupDiabetes;
    private RadioGroup radioGroupFumante;
    private RadioGroup radioGroupAtividade;
    private RadioButton radioButtonSimDiabetes;
    private RadioButton radioButtonNaoDiabetes;
    private RadioButton radioButtonSimFumante;
    private RadioButton radioButtonNaoFumante;
    private RadioButton radioButtonSimRealizaAtividade;
    private RadioButton radioButtonNaoRealizaAtividade;

    private Spinner spinnerProduto;

    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_cliente);


        editTextNome = findViewById(R.id.editTexNome);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextCelular = findViewById(R.id.editTextCelular);
        editTextInstagram = findViewById(R.id.editTextInstagram);
        checkBoxMicose = findViewById(R.id.checkBoxMicose);
        checkBoxUnhaEncravada = findViewById(R.id.checkBoxUnhaEncravada);
        checkBoxMancha = findViewById(R.id.checkBoxMancha);
        checkBoxUnhaQuebradica = findViewById(R.id.checkBoxUmhasQuebradica);
        checkBoxDescolamento = findViewById(R.id.checkBoxDescolamento);
        checkBoxOutro = findViewById(R.id.checkBoxOutro);
        radioGroupDiabetes = findViewById(R.id.radioGroupDiabetes);
        radioGroupFumante = findViewById(R.id.radioGroupFumante);
        radioGroupAtividade = findViewById(R.id.radioGroupAtividade);
        radioButtonSimDiabetes = findViewById(R.id.radioButtonSimDiabetes);
        radioButtonNaoDiabetes = findViewById(R.id.radioButtonNaoDiabetes);
        radioButtonSimFumante = findViewById(R.id.radioButtonSimFumante);
        radioButtonNaoFumante = findViewById(R.id.radioButtonNaoFumante);
        radioButtonSimRealizaAtividade = findViewById(R.id.radioButtonSimRealizaAtividade);
        radioButtonNaoRealizaAtividade = findViewById(R.id.radioButtonNaoRealizaAtividade);

        spinnerProduto = findViewById(R.id.spinnerProduto);

        scrollView = findViewById(R.id.scrollView);

        popularSpinner();

    } //fim onCreate

    private void popularSpinner() {

        ArrayList<String> listaDeProdutos = new ArrayList<>();

        listaDeProdutos.add(getString(R.string.nenhum));
        listaDeProdutos.add(getString(R.string.esmalte));
        listaDeProdutos.add(getString(R.string.acetona));
        listaDeProdutos.add(getString(R.string.base));
        listaDeProdutos.add(getString(R.string.leo_secante));
        listaDeProdutos.add(getString(R.string.gel));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,listaDeProdutos);


        spinnerProduto.setAdapter(adapter);

    }
    public void limparCampos(View view){

        editTextNome.setText(null);
        editTextEndereco.setText(null);
        editTextCelular.setText(null);
        editTextInstagram.setText(null);
        checkBoxMicose.setChecked(false);
        checkBoxUnhaEncravada.setChecked(false);
        checkBoxMancha.setChecked(false);
        checkBoxUnhaQuebradica.setChecked(false);
        checkBoxDescolamento.setChecked(false);
        checkBoxOutro.setChecked(false);
        radioGroupDiabetes.clearCheck();
        radioGroupFumante.clearCheck();
        radioGroupAtividade.clearCheck();
        spinnerProduto.setSelection(0);

        Toast.makeText(this,
                R.string.todos_os_campos_foram_limpos,
        Toast.LENGTH_LONG).show();

        editTextNome.requestFocus();

        scrollView.smoothScrollTo(0, 0);
        //scrollView.fullScroll(ScrollView.FOCUS_UP);

    }
    public void salvar(View view){

        if(validarCampos()){
            Toast.makeText(this,
                    R.string.dados_cadastrados_com_sucesso,
            Toast.LENGTH_LONG).show();
        }

    }
    private boolean validarCampos(){

        boolean valido = true;
        StringBuilder mensagem= new StringBuilder();
        View campoVazio = null;

        if(editTextNome.getText().toString().trim().isEmpty()){
            mensagem.append(getString(R.string.o_nome_deve_ser_preenchido)).append("\n");
            campoVazio = editTextNome;
            valido = false;
        }
        if(editTextEndereco.getText().toString().trim().isEmpty()){
            mensagem.append(getString(R.string.o_endere_o_deve_ser_preenchido)).append("\n");
            if(campoVazio == null){
                campoVazio = editTextEndereco;
            }
            valido = false;
        }
        if(editTextCelular.getText().toString().trim().isEmpty()){
            mensagem.append(getString(R.string.o_celular_deve_ser_preenchido)).append("\n");
            if(campoVazio == null){
                campoVazio = editTextCelular;
            }
            valido = false;
        }
        if(radioGroupDiabetes.getCheckedRadioButtonId() == -1){
            mensagem.append(getString(R.string.informe_se_possui_ou_n_o_diabetes)).append("\n");
            valido = false;
        }
        if(radioGroupFumante.getCheckedRadioButtonId() == -1){
            mensagem.append(getString(R.string.informe_se_o_cliente_fumante)).append("\n");
            valido = false;
        }
        if(radioGroupAtividade.getCheckedRadioButtonId() == -1){
            mensagem.append(getString(R.string.informe_se_o_cliente_realiza_alguma_atividade));
            valido = false;
        }
        if (!valido) {
            Toast.makeText(this, mensagem.toString(), Toast.LENGTH_LONG).show();
            if(campoVazio != null){
                campoVazio.requestFocus();
                scrollView.smoothScrollTo(0, 0);;
            }
        }

        /* observação: os campos instagram, checkbox problema nas unhas,
            e spinner alergia a produto, nao sao obrigatorios por isso nao inclui validação
        */

        return valido;
    }

}