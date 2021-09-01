package siac.com.componentes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinnerTitle extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private Spinner spinner;

    // ATRIBUTOS
    private String title = "title";
    private ColorStateList colorTitle;

    private float tamTitle = 13;
    private int entries = 0;


    public SpinnerTitle(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public SpinnerTitle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public SpinnerTitle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    CharSequence[] charSequencesArray;

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SpinnerLegenda, defStyleAttr, 0);
            title = typedArray.getString(R.styleable.SpinnerLegenda_title);
            colorTitle = typedArray.getColorStateList(R.styleable.SpinnerLegenda_colorTitle);
            tamTitle = getSizeFontLegendaEditText(typedArray.getString(R.styleable.SpinnerLegenda_tamTitle));

            charSequencesArray = typedArray.getTextArray(R.styleable.SpinnerLegenda_entries);
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
            float dimenPix = getResources().getDimension(R.dimen.tamLegendaEditTextUi);
            return dimenPix / scaleRatio;
        }
    }

    private void init() {
        inflate(getContext(), R.layout.view_spinner_legenda_ui, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        spinner = findViewById(R.id.spinner);
        setup();
    }

    private void setup() {
        legendaTextView.setText(title);
        if(colorTitle != null) {
            legendaTextView.setTextColor(colorTitle);
        }
        legendaTextView.setTextSize(tamTitle);

        setEntries();
    }


    public Object getSelectedItem() {
        return spinner.getSelectedItem();
    }

    public String getSelectedItemString() {
        return spinner.getSelectedItem().toString();
    }

    public void setAdapter(ArrayAdapter<Object> adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void setAdapter(SpinnerAdapter adapter) {
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.view_spinner_item_ui, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void setSelection(int selection){
        spinner.setSelection(selection);
    }

    public void setSelection(int selection, boolean aways){
        spinner.setSelection(selection, aways);
    }

    public void setLegenda(String text) {
        legendaTextView.setText(text);
    }

}
