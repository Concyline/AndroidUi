package siac.com.componentes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class KeyBoardDialog {

    private Dialog dialog;
    private Activity activity;
    private boolean cancelable = true;
    private int backgroundResource = R.drawable.bg_dialog_round_ui;
    private int HEIGTH_PERCENTAGE = -2;
    private int backgroundColor = R.color.white;
    private boolean justNumber = true;
    private OnDismissListener onDismissListener;

    public KeyBoardDialog(Activity activity) {
        this.activity = activity;
        dialog = new Dialog(activity, R.style.CustomDialogTheme);
    }

    public KeyBoardDialog setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public KeyBoardDialog setJustNumber(boolean justNumber) {
        this.justNumber = justNumber;
        return this;
    }

    public KeyBoardDialog setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public KeyBoardDialog setBackgroundResource(DWindow dWindow) {
        backgroundResource = dWindow.equals(DWindow.ROUND) ? R.drawable.bg_dialog_round_ui : R.drawable.bg_dialog_square_ui;
        return this;
    }

    @SuppressLint("ResourceAsColor")
    public KeyBoardDialog create() throws Exception {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.base_key_board_dialog_ui);
        dialog.setCancelable(cancelable);
        dialog.getWindow().getAttributes().windowAnimations = R.style.scale_fade_in_out;

        LinearLayout baseContainerLinearLayout = dialog.findViewById(R.id.baseContainerLinearLayout);
        baseContainerLinearLayout.setBackgroundResource(backgroundColor);

        addLayoutContainer();

        /////////////////////////////////////////////////////////////////////
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (width < height) {
            dialog.getWindow().setLayout((width * 90 / 100), HEIGTH_PERCENTAGE == -2 ? WindowManager.LayoutParams.WRAP_CONTENT : (height * HEIGTH_PERCENTAGE / 100));
        } else {
            dialog.getWindow().setLayout((width * 60 / 100), HEIGTH_PERCENTAGE == -2 ? WindowManager.LayoutParams.WRAP_CONTENT : (height * HEIGTH_PERCENTAGE / 100));
        }

        dialog.getWindow().getDecorView().setBackgroundResource(backgroundResource);

        return this;
    }

    private Button umButton, doisButton, tresButton, quatroButton, cincoButton, seisButton, seteButton, oitoButton, noveButton, zeroButton, pontoButton;
    private ImageView apagaImageView, okImageView;
    private EditText numeroEditText;

    private void addLayoutContainer() throws Exception {

        numeroEditText = dialog.findViewById(R.id.numeroEditText);

        umButton = dialog.findViewById(R.id.umButton);
        umButton.setTag("1");
        umButton.setBackgroundResource(backgroundColor);

        doisButton = dialog.findViewById(R.id.doisButton);
        doisButton.setTag("2");
        doisButton.setBackgroundResource(backgroundColor);

        tresButton = dialog.findViewById(R.id.tresButton);
        tresButton.setTag("3");
        tresButton.setBackgroundResource(backgroundColor);

        quatroButton = dialog.findViewById(R.id.quatroButton);
        quatroButton.setTag("4");
        quatroButton.setBackgroundResource(backgroundColor);

        cincoButton = dialog.findViewById(R.id.cincoButton);
        cincoButton.setTag("5");
        cincoButton.setBackgroundResource(backgroundColor);

        seisButton = dialog.findViewById(R.id.seisButton);
        seisButton.setTag("6");
        seisButton.setBackgroundResource(backgroundColor);

        seteButton = dialog.findViewById(R.id.seteButton);
        seteButton.setTag("7");
        seteButton.setBackgroundResource(backgroundColor);

        oitoButton = dialog.findViewById(R.id.oitoButton);
        oitoButton.setTag("8");
        oitoButton.setBackgroundResource(backgroundColor);

        noveButton = dialog.findViewById(R.id.noveButton);
        noveButton.setTag("9");
        noveButton.setBackgroundResource(backgroundColor);

        zeroButton = dialog.findViewById(R.id.zeroButton);
        zeroButton.setTag("0");
        zeroButton.setBackgroundResource(backgroundColor);

        pontoButton = dialog.findViewById(R.id.pontoButton);
        pontoButton.setTag(".");
        pontoButton.setBackgroundResource(backgroundColor);

        if(justNumber){
            pontoButton.setVisibility(View.INVISIBLE);
        }

        apagaImageView = dialog.findViewById(R.id.apagaImageView);
        okImageView = dialog.findViewById(R.id.okImageView);

        Button[] arrayButton = new Button[]{umButton, doisButton, tresButton, quatroButton, cincoButton, seisButton, seteButton, oitoButton, noveButton, zeroButton, pontoButton};

        for (Button button : arrayButton) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fadeIn(v);

                    String value = v.getTag().toString();
                    String valueEdit = numeroEditText.getText().toString().trim();

                    if (value.equals(".")) {
                        if (!valueEdit.contains(".")) {

                            if (valueEdit.length() == 0) { /////// CORRIGIR
                                numeroEditText.setText("0");
                            }

                            valueEdit = numeroEditText.getText().toString().trim() + value;
                        }
                    } else {
                        valueEdit = (valueEdit.equals("0") ? "" : valueEdit) + value;
                    }

                    numeroEditText.setText(valueEdit);
                }
            });
        }

        apagaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(v);

                String value = numeroEditText.getText().toString().trim();

                if (value.length() > 0) {
                    value = value.substring(0, value.length() - 1);
                }

                numeroEditText.setText(value);
            }
        });

        okImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(v);

                String value = numeroEditText.getText().toString().trim();

                if(justNumber){
                    value = value.replaceAll("[^0-9]+", "");
                }

                onDismissListener.dismiss(value.length() == 0 ? "0" : value);
                dismiss();
            }
        });

    }

    public void fadeIn(View v) {
        Animation in = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in);
        v.startAnimation(in);
    }

    public Dialog dialog() {
        return dialog;
    }

    public void show() {
        dialog.show();
    }

    public void show(String valueInitial, OnDismissListener onDismissListener) {
        numeroEditText.setText(valueInitial);
        this.onDismissListener = onDismissListener;
        dialog.show();
    }


    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum DWindow {
        SQUARE,
        ROUND
    }

    public enum DLayoutParams {
        WRAP_CONTENT,
        MATCH_PARENT
    }

    public interface OnDismissListener {
        void dismiss(String value);
    }

}
