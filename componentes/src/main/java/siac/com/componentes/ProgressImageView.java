package siac.com.componentes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;

import siac.com.componentes.Util.Mascara;
import siac.com.componentes.Util.Util;
import siac.com.componentes.Util.ValidaCNPJ;
import siac.com.componentes.Util.ValidaCPF;

import static siac.com.componentes.Util.Constantes.cnpj;
import static siac.com.componentes.Util.Constantes.cpf;
import static siac.com.componentes.Util.InputType.setInputType;
import static siac.com.componentes.Util.Util.fadeIn;
import static siac.com.componentes.Util.Util.shake;


public class ProgressImageView extends FrameLayout {

    // COMPONENTES
    private ImageView masterImageView;
    private ProgressBar masterProgressBar;

    // ATRIBUTOS
    private Drawable src;
    private int progressColor;
    private int progressSize;

    private int PRETO = -16777216;

    public ProgressImageView(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public ProgressImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public ProgressImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressImageView, defStyleAttr, 0);

            src = typedArray.getDrawable(R.styleable.ProgressImageView_src);
            progressColor = typedArray.getColor(R.styleable.ProgressImageView_progressColor, PRETO);
            progressSize = getProgressSize(typedArray.getString(R.styleable.ProgressImageView_progressSize));
            return;
        }

    }

    private int getProgressSize(String value) {
        try {
            String sp = value.replace("dip", "");
            return (int) Float.parseFloat(sp);
        } catch (Exception e) {
            return 100;
        }
    }

    private void init() {
        inflate(getContext(), R.layout.view_progress_image_view_ui, this);
        masterImageView = findViewById(R.id.masterImageView);
        masterProgressBar = findViewById(R.id.masterProgressBar);
        setup();
    }

    private void setup() {

        if (src != null) {
            masterImageView.setImageDrawable(src);
        }

        // PROGRESSBAR
        masterProgressBar.getIndeterminateDrawable().setColorFilter(new PorterDuffColorFilter(progressColor, PorterDuff.Mode.SRC_IN));
        masterProgressBar.getLayoutParams().height = progressSize;
        masterProgressBar.getLayoutParams().width = progressSize;
    }

    public void setProgres(){
        masterImageView.setVisibility(View.INVISIBLE);
        masterProgressBar.setVisibility(View.VISIBLE);
    }

    public void removeProgres(){
        masterImageView.setVisibility(View.VISIBLE);
        masterProgressBar.setVisibility(View.INVISIBLE);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        masterImageView.setOnClickListener(onClickListener);
    }
}
