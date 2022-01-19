package br.com.utill;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import siac.com.util.R;

public class ActionBarCuston {

    private static ActionBarCuston actionBarCuston;
    private static AppCompatActivity context;
    private static String barColor = "#000000";
    private static String titleColor = "#FFFFFF";
    private static String subTitleColor;

    public static ActionBarCuston getInstance(AppCompatActivity context) {
        if (actionBarCuston == null) {
            actionBarCuston = new ActionBarCuston(context);
        }
        return actionBarCuston;
    }

    public ActionBarCuston(AppCompatActivity context) {
        this.context = context;
    }

    public ActionBarCuston setBarColor(String barColor) {
        this.barColor = barColor;
        return this;
    }

    public ActionBarCuston setBarColor(int barColor) {
        try {
            this.barColor = String.format("#%06x", ContextCompat.getColor(context, barColor) & 0xffffff);
        } catch (Exception e) {
            e.printStackTrace();
            this.barColor = "#000000";
        }
        return this;
    }

    public ActionBarCuston setTitleColor(String titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public ActionBarCuston setTitleColor(int titleColor) {
        try {
            this.titleColor = String.format("#%06x", ContextCompat.getColor(context, titleColor) & 0xffffff);
        } catch (Exception e) {
            e.printStackTrace();
            this.titleColor = "#FFFFFF";
        }
        return this;
    }

    public ActionBarCuston setSubTitleColor(String subTitleColor) {
        this.subTitleColor = subTitleColor;
        return this;
    }

    public ActionBarCuston setSubTitleColor(int subTitleColor) {
        try {
            this.subTitleColor = String.format("#%06x", ContextCompat.getColor(context, subTitleColor) & 0xffffff);
        } catch (Exception e) {
            e.printStackTrace();
            this.subTitleColor = "#FFFFFF";
        }
        return this;
    }

    public void setBar( String title) {
        setBar( title, "");
    }

    public void setBar(String title, String subtitle) {
        setBar(title, subtitle, 1);
    }


    public void setBar(String title, String subtitle, float elevation) {
        ActionBar actionBar = context.getSupportActionBar();

        actionBar.setElevation(elevation);

        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(barColor)));

        actionBar.setTitle(Html.fromHtml("<font face='sans-serif' color='" + titleColor + "'>" + title + "</font>"));
        if (subtitle != null) {
            subTitleColor = subTitleColor != null ? subTitleColor : titleColor;
            actionBar.setSubtitle(Html.fromHtml("<font color='" + subTitleColor + "'>" + subtitle + "</font>"));
        }
    }
}
