package siac.com.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ProgressButton extends FrameLayout {

    // COMPONENTES
    private Button masterButton;
    private ProgressBar masterProgressBar;

    // ATRIBUTOS
    private int progressColor;
    private int progressSize;
    private String text;

    private int currentTextColor;

    private int PRETO = -16777216;

    public ProgressButton(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public ProgressButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public ProgressButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProgressButton, defStyleAttr, 0);

            text = getString(typedArray, R.styleable.ProgressButton_text, "");

            progressColor = typedArray.getColor(R.styleable.ProgressButton_progressColor, PRETO);
            progressSize = getProgressSize(typedArray.getString(R.styleable.ProgressButton_progressSize));
            return;
        }

    }

    private String getString(TypedArray typedArray, int index, String defaultValue) {
        String value = typedArray.getString(index);
        return value == null ? defaultValue : value;
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
        inflate(getContext(), R.layout.view_progress_button_view_ui, this);
        masterButton = findViewById(R.id.masterButton);
        masterProgressBar = findViewById(R.id.masterProgressBar);
        setup();
    }

    private void setup() {

        masterButton.setText(text);
        currentTextColor = masterButton.getCurrentTextColor();

        // PROGRESSBAR
        masterProgressBar.getIndeterminateDrawable().setColorFilter(new PorterDuffColorFilter(progressColor, PorterDuff.Mode.SRC_IN));
        masterProgressBar.getLayoutParams().height = progressSize;
        masterProgressBar.getLayoutParams().width = progressSize;
    }

    public void setProgres() {
        masterButton.setTextColor(getResources().getColor(android.R.color.transparent));
        masterProgressBar.setVisibility(View.VISIBLE);
    }

    public void removeProgres() {
        masterButton.setTextColor(currentTextColor);
        masterProgressBar.setVisibility(View.INVISIBLE);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        masterButton.setOnClickListener(onClickListener);
    }
}
