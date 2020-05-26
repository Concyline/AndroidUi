package siac.com.componentes.Util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import siac.com.componentes.R;

public class Util {

    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat formatDataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public static void fadeIn(Context context, View button) {
        Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        button.startAnimation(in);
    }

    public static void shake(Context context, View button) {
        Animation in = AnimationUtils.loadAnimation(context, R.anim.shake);
        button.startAnimation(in);
    }

    public static String dateToStr(Date date) {
        return format.format(date);
    }

    public static String dateHora(Date date) {
        return formatDataHora.format(date);
    }
}
