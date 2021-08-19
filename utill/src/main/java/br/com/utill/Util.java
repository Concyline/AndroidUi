package br.com.utill;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import br.com.utill.listeners.OnListnerAlertSimCancelar;
import br.com.utill.listeners.OnListnerOk;
import siac.com.util.R;

public class Util {

    public static void abaixaTeclado(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void toastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void toastShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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

    public static void alertOk(Activity context, String mensagem) {
        alertOkInter(context, mensagem, null);
    }

    public static void alertOk(Activity context, String mensagem, final OnListnerOk onListnerOk) {
        alertOkInter(context, mensagem, onListnerOk);
    }

    private static void alertOkInter(final Activity context, String mensagem, @NonNull final OnListnerOk onListnerOk) {
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

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

   /* public static void setBar(AppCompatActivity context, String title) {
        setBar(context, title, "");
    }*/

    /*public static void setBar(AppCompatActivity context, String title, String subtitle) {
        context.getSupportActionBar().setDisplayShowTitleEnabled(true);
        context.getSupportActionBar().setDisplayShowHomeEnabled(true);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context.getSupportActionBar().setDisplayUseLogoEnabled(true);

        context.getSupportActionBar().setTitle(title);
        if (subtitle != null) {
            context.getSupportActionBar().setSubtitle(subtitle);
        }
    }*/

 /*   public static void setBar(AppCompatActivity context, String title, String subtitle) {
        setBar(context, title, subtitle, 1);
    }


    public static void setBar(AppCompatActivity context, String title, String subtitle, float elevation) {
        ActionBar actionBar = context.getSupportActionBar();

        actionBar.setElevation(elevation);

        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));

        actionBar.setTitle(Html.fromHtml("<font face='sans-serif' color='#212121'>"+title+"</font>"));
        if (subtitle != null) {
            actionBar.setSubtitle(Html.fromHtml("<font color='#212121'>"+subtitle+"</font>"));
        }
    }*/


}
