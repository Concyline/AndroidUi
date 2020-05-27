package siac.com.componentes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.Normalizer;

import static siac.com.componentes.Util.InputType.setInputType;

public class SearchLegenda extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private EditText editText;
    private ImageView imageView;

    // ATRIBUTOS
    private String legenda = "legenda";
    private String hint = "";
    private ColorStateList corLegenda;

    private float tamLegenda = 13;
    private float tamTextEditText = 16;
    private int inputType = 0;
    private int mascara = 0;
    private boolean enabled;
    private boolean focusable;
    private boolean requestfocus;
    private int coricon;


    public SearchLegenda(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public SearchLegenda(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public SearchLegenda(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Styleable, defStyleAttr, 0);
            legenda = typedArray.getString(R.styleable.Styleable_legenda);
            corLegenda = typedArray.getColorStateList(R.styleable.Styleable_corLegenda);
            tamLegenda = getSizeFontLegendaEditText(typedArray.getString(R.styleable.Styleable_tamLegendaEditText));
            tamTextEditText = getSizeFontTextEditText(typedArray.getString(R.styleable.Styleable_tamTextEditText));

            mascara = typedArray.getInteger(R.styleable.Styleable_mascara, 0);
            inputType = typedArray.getInteger(R.styleable.Styleable_inputType, 0);
            hint = typedArray.getString(R.styleable.Styleable_hint);

            enabled = typedArray.getBoolean(R.styleable.Styleable_enabled, true);
            focusable = typedArray.getBoolean(R.styleable.Styleable_focusable, true);
            requestfocus = typedArray.getBoolean(R.styleable.Styleable_requestfocus, false);
            coricon = typedArray.getColor(R.styleable.Styleable_coricon, 0);

            return;
        }

    }

    private float getSizeFontLegendaEditText(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.tamLegendaEditText);
            return dimenPix / scaleRatio;
        }
    }

    private float getSizeFontTextEditText(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.txtEdit);
            return dimenPix / scaleRatio;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        imageView.setOnClickListener(onClickListener);
    }

    public void setOnKeyListener(OnKeyListener onKeyListener) {
        editText.setOnKeyListener(onKeyListener);
    }

    private void init() {
        inflate(getContext(), R.layout.view_search_legenda, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);
        setup();
    }

    private void setup() {
        legendaTextView.setText(legenda);
        if (corLegenda != null) {
            legendaTextView.setTextColor(corLegenda);
        }
        legendaTextView.setTextSize(tamLegenda);

        editText.setHint(hint);
        editText.setEnabled(enabled);
        editText.setFocusable(focusable);
        editText.setTextSize(tamTextEditText);

        if (requestfocus) {
            editText.requestFocus();
        }

        setInputType(editText, inputType, 1);

        imageView.setColorFilter(new PorterDuffColorFilter(coricon, PorterDuff.Mode.SRC_IN));
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public String getString() {
        return editText.getText().toString().trim();
    }

    public String getStringUperCase() {
        return removerAcentos(editText.getText().toString().trim().toUpperCase());
    }

    private String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public Integer getInteger() {
        try {
            return Integer.parseInt(editText.getText().toString().trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public Double getdouble() {
        try {
            return Double.parseDouble(editText.getText().toString().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public void setHint(String text) {
        editText.setHint(text);
    }
}
