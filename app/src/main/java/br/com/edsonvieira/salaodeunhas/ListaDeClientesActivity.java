package br.com.edsonvieira.salaodeunhas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ListaDeClientesActivity extends AppCompatActivity {


    public static void nova(AppCompatActivity activity){

        Intent intent = new Intent(activity, ListaDeClientesActivity.class);

        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_clientes);

        setTitle(getString(R.string.clientes));


    }
}