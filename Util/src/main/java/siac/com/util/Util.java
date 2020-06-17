package siac.com.util;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public class Util {

    public static void abaixaTeclado(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void toast(Context context, String msg) {
        android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_LONG).show();
    }

    public static void fadeIn(Context context, View button) {
        Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        button.startAnimation(in);
    }

    public static void load(Context context, View v) {
        Animation in = AnimationUtils.loadAnimation(context, R.anim.slide_in_top);
        v.startAnimation(in);
    }

    public static void rotate(Context context, View v) {
        Animation in = AnimationUtils.loadAnimation(context, R.anim.rotate);
        v.startAnimation(in);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void alertOk(Context context, String mensagem) {
        alertOkInter(context, mensagem, null);
    }

    public static void alertOk(Context context, String mensagem, final OnListnerOk onListnerOk) {
        alertOkInter(context, mensagem, onListnerOk);
    }


    private static void alertOkInter(final Context context, String mensagem, @NonNull final OnListnerOk onListnerOk) {
        try {
            new androidx.appcompat.app.AlertDialog.Builder(context)
                    .setTitle("Atenção")
                    .setMessage(mensagem)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (onListnerOk != null) {
                                onListnerOk.ok();
                            }

                        }
                    })
                    .setIcon(R.drawable.ic_error_outline_black_24dp
                    )
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alertSimCancelar(Context context, String mensagem, final OnListnerAlertSimCancelar simCancelar) {
        try {
            new androidx.appcompat.app.AlertDialog.Builder(context)
                    .setTitle("Atenção")
                    .setMessage(mensagem)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            simCancelar.sim();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            simCancelar.cancelar();
                        }
                    })
                    .setIcon(R.drawable.ic_error_outline_black_24dp
                    )
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
