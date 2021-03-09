package siac.com.componentes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import siac.com.componentes.extras.SizeText;

/**
 * Created by multimeet on 18/01/21.
 */

public class ProgressIndeterminate {

    private Dialog dialog;
    private Context context;
    private TextView messageTextView;
    private ConstraintLayout constraintLayout;
    private GeometricProgressView progressBarUi;
    private int background = 0;
    private boolean multColor = false;

    public ProgressIndeterminate(Context context) {
        this.context = context;
        dialog = new Dialog(context);
    }

    public ProgressIndeterminate create(String message) {

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.view_custon_progress_dialog);

        dialog.getWindow().getAttributes().windowAnimations = R.style.scale_fade_in_out;
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.rectangle_back);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        int width = 700; // DEFAULT
        if (displayMetrics != null) {
            width = displayMetrics.widthPixels;
        }

        dialog.getWindow().setLayout(width - 100, 300);

        constraintLayout = dialog.findViewById(R.id.rl);
        constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.white));

        progressBarUi = dialog.findViewById(R.id.progressBarUi);

        messageTextView = dialog.findViewById(R.id.msg);
        messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.medium_text)); // TEXTSIZE DEFAULT
        messageTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf"));
        messageTextView.setText(message);

        return this;
    }

    public ProgressIndeterminate setBackgroundColor(int color) {
        if (constraintLayout != null) {
            constraintLayout.setBackgroundColor(context.getResources().getColor(color));
        }
        return this;
    }

    public ProgressIndeterminate setTextSize(SizeText sizeText) {
        messageTextView = dialog.findViewById(R.id.msg);
        switch (sizeText) {
            case SMALL:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.small_text));
                break;

            case MEDIUM:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.medium_text));
                break;

            case LARGE:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.large_text));
                break;

            case XLARGE:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.extra_large_text));
                break;
        }
        return this;
    }

    public ProgressIndeterminate cancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    public boolean isShowing(){
       return dialog.isShowing();
    }

    public void setMessage(String message){
        messageTextView.setText(message);
    }

    public ProgressIndeterminate multColor(boolean multColor){
        this.multColor = multColor;
        return this;
    }

    public ProgressIndeterminate show() {
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (multColor) {
                        //progressBarUi.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                        progressBarUi.setColor(Color.RED);
                        Thread.sleep(1000);
                        //progressBarUi.getIndeterminateDrawable().setColorFilter(Color.MAGENTA, PorterDuff.Mode.MULTIPLY);
                        progressBarUi.setColor(Color.MAGENTA);
                        Thread.sleep(1000);
                       // progressBarUi.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                        progressBarUi.setColor(Color.GREEN);
                        Thread.sleep(1000);
                       // progressBarUi.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                        progressBarUi.setColor(Color.BLUE);
                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        return this;
    }

    public static ProgressIndeterminate show(Context context, String mesagem){
        return new ProgressIndeterminate(context).create(mesagem).show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
