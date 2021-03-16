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
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import siac.com.componentes.Util.Mascara;
import siac.com.componentes.Util.Util;
import siac.com.componentes.Util.ValidaCNPJ;
import siac.com.componentes.Util.ValidaCPF;
import siac.com.componentes.currency.CurrencyEditText;

import static siac.com.componentes.Util.Constantes.cnpj;
import static siac.com.componentes.Util.Constantes.cpf;
import static siac.com.componentes.Util.Util.fadeIn;
import static siac.com.componentes.Util.Util.shake;


public class EditTexCurrency extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private CurrencyEditText editText;
    private ImageView requiredImageView;

    // ATRIBUTOS
    private String title = "title";
    private String hint = "";
    private String text = "";
    private ColorStateList colorTitle;
    private float tamTitleEditText = 13;
    private float tamTextEditText = 16;
    private int maxLength = 0;
    private boolean enabled;
    private boolean focusable;
    private boolean requestfocus;
    private boolean requerido;
    private String tag;
    private String legendaRequerido;
    private boolean mShowSymbol;
    private String mLocale = "";
    private Locale locale;


    public EditTexCurrency(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public EditTexCurrency(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public EditTexCurrency(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditTextCurrencyLegenda, defStyleAttr, 0);
            title = typedArray.getString(R.styleable.EditTextCurrencyLegenda_title);
            colorTitle = typedArray.getColorStateList(R.styleable.EditTextCurrencyLegenda_colorTitle);
            tamTitleEditText = getSizeFontLegendaEditText(typedArray.getString(R.styleable.EditTextCurrencyLegenda_tamTitleEditText));
            tamTextEditText = getSizeFontTextEditText(typedArray.getString(R.styleable.EditTextCurrencyLegenda_tamTextEditText));

            maxLength = typedArray.getInteger(R.styleable.EditTextCurrencyLegenda_maxLength, 0);
            hint = typedArray.getString(R.styleable.EditTextCurrencyLegenda_hint);
            text = typedArray.getString(R.styleable.EditTextCurrencyLegenda_text);

            enabled = typedArray.getBoolean(R.styleable.EditTextCurrencyLegenda_enabled, true);
            focusable = typedArray.getBoolean(R.styleable.EditTextCurrencyLegenda_focusable, true);
            requestfocus = typedArray.getBoolean(R.styleable.EditTextCurrencyLegenda_requestfocus, false);
            requerido = typedArray.getBoolean(R.styleable.EditTextCurrencyLegenda_requerido, false);
            legendaRequerido = getString(typedArray, R.styleable.EditTextCurrencyLegenda_legendaRequerido, "Item requerido no formulário");
            tag = typedArray.getString(R.styleable.EditTextCurrencyLegenda_tag);

            mShowSymbol = typedArray.getBoolean(R.styleable.EditTextCurrencyLegenda_showSymbol, false);
            mLocale = typedArray.getString(R.styleable.EditTextCurrencyLegenda_locale);

            if (mLocale == null) {
                locale = getDefaultLocale();
            } else {
                if (mLocale.contains("-")) {
                    mLocale = mLocale.replace("-", "_");
                }

                String[] l = mLocale.split("_");
                if (l.length > 1) {
                    locale = new Locale(l[0], l[1]);
                } else {
                    locale = new Locale("", mLocale);
                }
            }
            return;
        }

    }

    private Locale getDefaultLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return getContext().getResources().getConfiguration().getLocales().get(0);
        else
            return getContext().getResources().getConfiguration().locale;
    }

    private String getString(TypedArray typedArray, int index, String defaultValue) {
        String value = typedArray.getString(index);
        return value == null ? defaultValue : value;
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

    private float getSizeFontTextEditText(String value) {
        try {
            String sp = value.replace("sp", "");
            return Float.parseFloat(sp);
        } catch (Exception e) {
            float scaleRatio = getResources().getDisplayMetrics().density;
            float dimenPix = getResources().getDimension(R.dimen.txtEditUi);
            return dimenPix / scaleRatio;
        }
    }

    private void init() {
        inflate(getContext(), R.layout.view_edittext_financeiro_legenda_ui, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        editText = findViewById(R.id.editText);
        requiredImageView = findViewById(R.id.requiredImageView);

        setup();
    }

    private void setup() {
        legendaTextView.setText(title);
        if (colorTitle != null) {
            legendaTextView.setTextColor(colorTitle);
        }
        legendaTextView.setTextSize(tamTitleEditText);

        editText.setHint(hint);
        editText.setText(text != null ? text : "");
        editText.setEnabled(enabled);
        editText.setFocusable(focusable);
        editText.setTextSize(tamTextEditText);
        editText.setTag(tag);
        editText.setText("0.00");
        editText.setSelection(editText.getText().length());
        editText.showSymbol(mShowSymbol);

        if (locale != null) {
            editText.setLocale(locale);
        }

        if (maxLength > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }

        if (requestfocus) {
            setFocus();
        }

        if (requerido) {
            setRequerido();
        }
    }

    private boolean flag = true;

    private void whatch() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                flag = true;
            }
        }, 1000);
    }

    private void setRequerido() {
        requiredImageView.setVisibility(View.VISIBLE);
        requiredImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(getContext(), v);
                popupDisplay(v, legendaRequerido);
            }
        });
    }

    public void popupDisplay(final View destino, final String legenda) {

        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.popup_layout_ui, null);
        ((TextView) view.findViewById(R.id.textView)).setText(legenda);

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(view);

        popupWindow.showAsDropDown(destino, -40, -15);

    }

    public boolean validaPreenchido() {
        if (requerido) {
            return editText.getText().toString().length() > 0 ? true : false;
        }
        return true;
    }

    private String retiraCaracteres(String current) {
        return current.replaceAll("[^0-9]*", "");
    }

    public void setError() {
        requiredImageView.setColorFilter(Color.RED);
        shake(getContext(), requiredImageView);
    }

    public void removeError() {
        requiredImageView.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN));
    }

    boolean controle = true;


    public void setSelection(int index) {
        editText.setSelection(index);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        editText.setOnClickListener(onClickListener);
    }

    public Dialog createDialogData(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar calendar = Calendar.getInstance();
        return new DatePickerDialog(getContext(), mDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setFocus() {
        editText.requestFocus();
    }

    public void setEnabled(boolean enabled) {
        editText.setEnabled(enabled);
    }

    public String getTag() {
        return editText.getTag() != null ? editText.getTag().toString() : "";
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setLegenda(String text) {
        legendaTextView.setText(text);
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

    public Double getDouble() {
        try {

            String dado = editText.getText().toString().trim();

            if (locale.toString().equals("en_US")) {
                dado = dado.replace("$", "");
                dado = dado.replace(",", "");

            } else if (locale.toString().equals("pt_BR")) {
                dado = dado.replace("R$", "");
                dado = dado.replace(".", "");
                dado = dado.replace(",", ".");
            }

            return Double.parseDouble(dado);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public void setFilters(InputFilter[] filters) {
        editText.setFilters(filters);
    }

    public void setHint(String text) {
        editText.setHint(text);
    }
}
