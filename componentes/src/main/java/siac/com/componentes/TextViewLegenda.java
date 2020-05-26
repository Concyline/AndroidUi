package siac.com.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class TextViewLegenda extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private TextView descricaoTextView;

    // ATRIBUTOS
    private String legenda;
    private int corLegenda;
    private float tamLegenda;

    private String descricao;
    private int corDescricao;
    private float tamDescricao;


    public TextViewLegenda(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public TextViewLegenda(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public TextViewLegenda(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Styleable, defStyleAttr, 0);
            legenda = typedArray.getString(R.styleable.Styleable_legenda);
            corLegenda = typedArray.getInteger(R.styleable.Styleable_corLegenda, R.color.corLegenda);
            tamLegenda = getSizeFontLegenda(typedArray.getString(R.styleable.Styleable_tamLegenda));

            descricao = typedArray.getString(R.styleable.Styleable_descricao);
            corDescricao = typedArray.getInteger(R.styleable.Styleable_corDescricao, R.color.corDescricao);
            tamDescricao = getSizeFontDescricao(typedArray.getString(R.styleable.Styleable_tamDescricao));
            return;
        }
        legenda = "legenda";
        corLegenda = R.color.corLegenda;
        tamLegenda = 11;
        descricao = "descricao";
        corDescricao = R.color.corDescricao;
        tamDescricao = 16;
    }

    private float getSizeFontLegenda(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.tamLegenda);
            return dimenPix / scaleRatio;
        }
    }

    private float getSizeFontDescricao(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.tamDescricao);
            return dimenPix / scaleRatio;
        }
    }

    private void init() {
        inflate(getContext(), R.layout.view_textview_legenda, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        descricaoTextView = findViewById(R.id.descricaoTextView);
        setup();
    }

    private void setup() {
        legendaTextView.setText(legenda);
        legendaTextView.setTextColor(ContextCompat.getColor(getContext(), corLegenda));
        legendaTextView.setTextSize(tamLegenda);

        descricaoTextView.setText(descricao);
        descricaoTextView.setTextColor(ContextCompat.getColor(getContext(), corDescricao));
        descricaoTextView.setTextSize(tamDescricao);
        descricaoTextView.setSingleLine();
    }

    public void setDescricao(String descricao) {
        descricaoTextView.setText(descricao);
    }

    public void setCorDescricao(int color){
        descricaoTextView.setTextColor(ContextCompat.getColor(getContext(), color));
    }

}
