package siac.com.componentes.currency;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Currency;
import java.util.Locale;

import siac.com.componentes.R;

public class CurrencyEditText extends androidx.appcompat.widget.AppCompatEditText {
    private String mLocale = "";
    private boolean mShowSymbol;
    private int mDecimalPoints;

    private Locale locale;
    private DecimalFormat numberFormat;

    private String currencySymbol;
    private int fractionDigit;

    public CurrencyEditText(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        this.setKeyListener(DigitsKeyListener.getInstance("0123456789.-"));

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.currencyEditText, 0, 0);

        try {
            if (a.getString(R.styleable.currencyEditText_locale) == null)
                this.locale = getDefaultLocale();
            else this.mLocale = a.getString(R.styleable.currencyEditText_locale);

            if (a.getString(R.styleable.currencyEditText_showSymbol) != null)
                this.mShowSymbol = a.getBoolean(R.styleable.currencyEditText_showSymbol, false);

            if (a.getString(R.styleable.currencyEditText_decimalPoints) != null) {
                this.mDecimalPoints = a.getInt(R.styleable.currencyEditText_decimalPoints, 0);
                this.fractionDigit = mDecimalPoints;
            }

            if (mLocale.equals("")) {
                locale = getDefaultLocale();
            } else {
                if (mLocale.contains("-"))
                    mLocale = mLocale.replace("-", "_");

                String[] l = mLocale.split("_");
                if (l.length > 1) {
                    locale = new Locale(l[0], l[1]);
                } else {
                    locale = new Locale("", mLocale);
                }
            }

            initSettings();
        } finally {
            a.recycle();
        }

        this.addTextChangedListener(onTextChangeListener);
    }

    private void initSettings() {
        boolean success = false;
        while (!success) {
            try {
                if (fractionDigit == 0) {
                    fractionDigit = Currency.getInstance(locale).getDefaultFractionDigits();
                }

                DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);

                currencySymbol = symbols.getCurrencySymbol();

                DecimalFormat df = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
                numberFormat = new DecimalFormat(df.toPattern(), symbols);

                if (mDecimalPoints > 0) {
                    numberFormat.setMinimumFractionDigits(mDecimalPoints);
                }

                success = true;
            } catch (IllegalArgumentException e) {
                Log.e(getClass().getCanonicalName(), e.getMessage());
                locale = getDefaultLocale();
            }
        }
    }

    @SuppressWarnings("deprecation")
    private Locale getDefaultLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return getContext().getResources().getConfiguration().getLocales().get(0);
        else
            return getContext().getResources().getConfiguration().locale;
    }

    private void resetText() {
        String s = getText().toString();
        if (s.isEmpty()) {
            initSettings();
            return;
        }
        try {
            initSettings();
            s = format(s);
            removeTextChangedListener(onTextChangeListener);
            setText(s);
            setSelection(s.length());
            addTextChangedListener(onTextChangeListener);
        } catch (NumberFormatException e) {
            Log.e(getClass().getCanonicalName(), e.getMessage());
        } catch (NullPointerException e) {
            Log.e(getClass().getCanonicalName(), e.getMessage());
        }
    }

    private boolean negative = false;

    private TextWatcher onTextChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0)
                return;

            removeTextChangedListener(this);

            String text = s.toString();

            try {
                text = format(text);
            } catch (NumberFormatException e) {
                Log.e(getClass().getCanonicalName(), e.getMessage());
            } catch (NullPointerException e) {
                Log.e(getClass().getCanonicalName(), e.getMessage());
            }

            setText(text);
            setSelection(text.length());

            addTextChangedListener(this);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private String format(String text) throws NumberFormatException, NullPointerException {
        negative = text.contains("-") ? true : false;

        text = text.replaceAll("[^\\d]", "");

        String formating = numberFormat.format(Double.parseDouble(text) / Math.pow(10, fractionDigit)).replace(currencySymbol, "");

        if (negative) {
            formating = "-" + formating;
        }

        if (mShowSymbol) {
            formating = currencySymbol + formating;
        }

        if(formating.equals(currencySymbol+"-0,00")){
            formating = currencySymbol+"0.00";
        }

        if(formating.equals("-0,00")){
            formating = "0.00";
        }

        return formating;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        resetText();
    }

    public boolean showSymbol() {
        return this.mShowSymbol;
    }

    public void showSymbol(boolean showSymbol) {
        this.mShowSymbol = showSymbol;
        resetText();
    }

    public String getCurrencyText() {
        if (showSymbol())
            return getText().toString().replace(currencySymbol, "");
        else return getText().toString();
    }

    public int getFractionDigit() {
        return fractionDigit;
    }

    public void setDecimals(int decimalPoints) {
        this.mDecimalPoints = decimalPoints;
        this.fractionDigit = decimalPoints;
        resetText();
    }
}
