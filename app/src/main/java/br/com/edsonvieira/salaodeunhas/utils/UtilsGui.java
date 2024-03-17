package br.com.edsonvieira.salaodeunhas.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import br.com.edsonvieira.salaodeunhas.R;

public class UtilsGui {


    public static void aviso(Context context,int idMensagem,
                             DialogInterface.OnClickListener listener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.aviso);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage(idMensagem);

        builder.setNeutralButton(R.string.ok, listener);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void avisoErro(Context context, String mensagem){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.erro);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(mensagem);

        builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    public static void confirmaAcao(Context contexto,
                                    String mensagem,
                                    DialogInterface.OnClickListener listener){

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        builder.setTitle(R.string.confirmacao);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(mensagem);

        builder.setPositiveButton(R.string.sim, listener);
        builder.setNegativeButton(R.string.nao, listener);

        AlertDialog alert = builder.create();
        alert.show();
    }





}
