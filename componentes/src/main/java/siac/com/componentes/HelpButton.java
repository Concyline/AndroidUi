package siac.com.componentes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static siac.com.componentes.Util.Util.fadeIn;


public class HelpButton extends FrameLayout {

    // COMPONENTES
    private ImageView imageView;
    private String helpMsg = "";
    private Activity activity;
    private int color;

    public HelpButton(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public HelpButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public HelpButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HelpButton, defStyleAttr, 0);

            color = typedArray.getInteger(R.styleable.HelpButton_color, 0);
            helpMsg = typedArray.getString(R.styleable.HelpButton_helpMsg);
            return;
        }

    }

    private void init() {
        inflate(getContext(), R.layout.view_question_button_view_ui, this);
        imageView = findViewById(R.id.imageView);
        /*masterButton = findViewById(R.id.masterButton);
        masterProgressBar = findViewById(R.id.masterProgressBar);
        */

        setup();
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }

    public void setHelpMsg(String helpMsg){
        this.helpMsg = helpMsg;
    }

    private void setup(){

        imageView.setColorFilter(color != 0 ? color : R.color.cyan ,android.graphics.PorterDuff.Mode.MULTIPLY);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(getContext(), v);

                if(activity == null){
                    System.out.println("setActivity is null");
                    return;
                }

                new HelpDialog(activity).create().show();
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public class HelpDialog {

        private android.app.Dialog dialog;
        private androidx.appcompat.widget.Toolbar toolbar;
        private String toolbarTitle = "Atenção";
        private Activity activity;
        private TextView textView, okTextView;
        private boolean cancelable = true;
        private int layoutResId = -1;
        private int menuToolbarResId = -1;
        private int backgroundResource = R.drawable.bg_dialog_round_ui;
        private int HEIGTH_PERCENTAGE = -2;

        public HelpDialog(Activity activity) {
            this.activity = activity;
            dialog = new android.app.Dialog(activity, R.style.CustomDialogTheme);
        }

        public HelpDialog setHeight(CustomDialog.DLayoutParams dLayoutParams) {
            switch (dLayoutParams) {
                case WRAP_CONTENT: {
                    HEIGTH_PERCENTAGE = -2;
                    break;
                }
                case MATCH_PARENT: {
                    HEIGTH_PERCENTAGE = 90;
                    break;
                }
            }
            return this;
        }

        @SuppressLint("ResourceAsColor")
        public HelpDialog create() {

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.help_dialog_ui);
            dialog.setCancelable(cancelable);
            dialog.getWindow().getAttributes().windowAnimations = R.style.scale_fade_in_out;

            toolbar = dialog.findViewById(R.id.toolbar);
            textView = dialog.findViewById(R.id.textView);
            okTextView = dialog.findViewById(R.id.okTextView);

            toolbar.setTitle(toolbarTitle);
            toolbar.setTitleTextColor(android.graphics.Color.WHITE);
            toolbar.setSubtitleTextColor(android.graphics.Color.WHITE);
            toolbar.setNavigationIcon(R.drawable.outline_arrow_back_white_48dp);

            toolbar.setNavigationOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    dismiss();
                }
            });

            okTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    fadeIn(getContext(), v);
                    dismiss();
                }
            });

            if (menuToolbarResId > 0) {
                toolbar.inflateMenu(menuToolbarResId);
            }

            textView.setText(helpMsg);

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

        public void setNavigationOnClickListener(android.view.View.OnClickListener onClickListener){
            toolbar.setNavigationOnClickListener(onClickListener);
        }

        public MenuItem menu(int id) throws Exception {
            MenuItem menuItem = null;
            if (toolbar != null) {
                menuItem = toolbar.getMenu().findItem(id);
            }

            if (menuItem == null) {
                throw new Exception("Menu item not found!");
            }
            return menuItem;
        }

        public androidx.appcompat.widget.Toolbar toolbar() {
            return toolbar;
        }

        public android.app.Dialog dialog() {
            return dialog;
        }

        public void show() {
            dialog.show();
        }

        public void dismiss() {
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }
}
