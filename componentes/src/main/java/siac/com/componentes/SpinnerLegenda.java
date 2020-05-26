package siac.com.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class SpinnerLegenda extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private Spinner spinner;

    // ATRIBUTOS
    private String legenda = "legenda";
    private int corLegenda = R.color.corLegenda;

    private float tamLegenda = 13;
    private int entries = 0;


    public SpinnerLegenda(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public SpinnerLegenda(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public SpinnerLegenda(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    CharSequence[] charSequencesArray;

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Styleable, defStyleAttr, 0);
            legenda = typedArray.getString(R.styleable.Styleable_legenda);
            corLegenda = typedArray.getInteger(R.styleable.Styleable_corLegenda, R.color.corLegenda);
            tamLegenda = getSizeFontLegendaEditText(typedArray.getString(R.styleable.Styleable_tamLegendaEditText));

            charSequencesArray = typedArray.getTextArray(R.styleable.Styleable_entries);
            if(charSequencesArray != null) {
                entries = charSequencesArray.length;
            }
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

    private void init() {
        inflate(getContext(), R.layout.view_spinner_legenda, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        spinner = findViewById(R.id.spinner);
        setup();
    }

    private void setup() {
        legendaTextView.setText(legenda);
        legendaTextView.setTextColor(ContextCompat.getColor(getContext(), corLegenda));
        legendaTextView.setTextSize(tamLegenda);

        setEntries();
    }


    public Object getSelectedItem() {
        return spinner.getSelectedItem();
    }

    public void setAdapter(ArrayAdapter<Object> adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        spinner.setOnItemSelectedListener(listener);
    }

    public void setEnabled(boolean enabled){
        spinner.setEnabled(enabled);
    }

    public void setEntries() {

        String[] array = new String[]{""};

        if (entries != 0) {
            array = new String[charSequencesArray.length];
            for (int i = 0; i < charSequencesArray.length; i++) {
                array[i] = String.valueOf(charSequencesArray[i]);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.view_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void setSelection(int selection){
        spinner.setSelection(0);
    }

}
