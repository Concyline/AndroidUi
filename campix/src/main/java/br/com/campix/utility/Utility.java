package br.com.campix.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Utility {

    public static int HEIGHT, WIDTH;

    public static void setupStatusBarHidden(AppCompatActivity appCompatActivity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = appCompatActivity.getWindow();
            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                w.setStatusBarColor(Color.TRANSPARENT);
            }
        }

    }

    public static void hideStatusBar(AppCompatActivity appCompatActivity) {

        synchronized (appCompatActivity) {
            appCompatActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }

    public static void getScreenSize(Activity activity) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        HEIGHT = displayMetrics.heightPixels;
        WIDTH = displayMetrics.widthPixels;

    }

    public static int getStatusBarSizePort(AppCompatActivity check) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            int result = 0;
            Resources res = check.getBaseContext().getResources();
            int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = check.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        }
        return 0;
    }

    public static void vibe(Context c, long l){
        ((Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(l);
    }

    public static void scanPhoto(Context pix, File photo) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            final Uri contentUri = Uri.fromFile(photo);
            scanIntent.setData(contentUri);
            pix.sendBroadcast(scanIntent);
        } else {
            pix.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse(photo.getAbsolutePath())));
        }

    }
}
