package br.com.edsonvieira.salaodeunhas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MostrarDetalhesClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalhes_cliente);

        setTitle(getString(R.string.detalhes_do_cliente));


    }
}