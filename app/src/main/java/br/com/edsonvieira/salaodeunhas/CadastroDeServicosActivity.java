package br.com.edsonvieira.salaodeunhas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroDeServicosActivity extends AppCompatActivity {

    private EditText editTextDescricaoServico;
    private EditText editTextPrecoServico;
    private EditText editTextDuracaoServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_servicos);

        editTextDescricaoServico = findViewById(R.id.editTextDescricaoServico);
        editTextPrecoServico = findViewById(R.id.editTextPrecoServico);
        editTextDuracaoServico = findViewById(R.id.editTextDuracaoServico);

    } // fim onCreate

    public void limpar(View view){
        editTextDescricaoServico.setText(null);
        editTextPrecoServico.setText(null);
        editTextDuracaoServico.setText(null);
        editTextDescricaoServico.requestFocus();
    }





} //fim Activity