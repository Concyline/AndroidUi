package siac.com.componentes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class TextViewTitle extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private TextView descricaoTextView;

    // ATRIBUTOS
    private String title = "title";
    private ColorStateList colorTitle;
    private float tamTitle = 11;

    private String descricao;
    private ColorStateList corDescricao;
    private float tamDescricao;
    private boolean singleLine;
    private Context context;


    public TextViewTitle(@NonNull Context context) {
        super(context);
        this.context = context;
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public TextViewTitle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public TextViewTitle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextViewLegenda, defStyleAttr, 0);
            title = typedArray.getString(R.styleable.TextViewLegenda_title);
            colorTitle = typedArray.getColorStateList(R.styleable.TextViewLegenda_colorTitle);
            tamTitle = getSizeFontLegenda(typedArray.getString(R.styleable.TextViewLegenda_tamTitle));

            descricao = typedArray.getString(R.styleable.TextViewLegenda_descricao);
            corDescricao = typedArray.getColorStateList(R.styleable.TextViewLegenda_corDescricao);
            tamDescricao = getSizeFontDescricao(typedArray.getString(R.styleable.TextViewLegenda_tamDescricao));
            singleLine = typedArray.getBoolean(R.styleable.TextViewLegenda_singleLine, false);
            return;
        }
        descricao = "descricao";
        tamDescricao = 16;
    }

    private float getSizeFontLegenda(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.tamLegendaUi);
            return dimenPix / scaleRatio;
        }
    }

    private float getSizeFontDescricao(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.tamDescricaoUi);
            return dimenPix / scaleRatio;
        }
    }

    private void init() {
        inflate(getContext(), R.layout.view_textview_legenda_ui, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        descricaoTextView = findViewById(R.id.descricaoTextView);
        setup();
    }

    private void setup() {
        legendaTextView.setText(title);
        if(colorTitle != null) {
            legendaTextView.setTextColor(colorTitle);
        }
        legendaTextView.setTextSize(tamTitle);

        descricaoTextView.setText(descricao);
        if(corDescricao != null) {
            descricaoTextView.setTextColor(corDescricao);
        }
        descricaoTextView.setTextSize(tamDescricao);

        descricaoTextView.setSingleLine(singleLine);

        //descricaoTextView.setSingleLine();
    }

    public void setDescricao(String descricao) {
        descricaoTextView.setText(descricao);
    }

    public void setCorDescricao(int color){
        descricaoTextView.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setFont(String path){
        Typeface face=Typeface.createFromAsset(context.getAssets(),path);
        legendaTextView.setTypeface(face);
        descricaoTextView.setTypeface(face);
    }

}
