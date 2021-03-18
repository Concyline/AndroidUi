package br.com.calculadora;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.dynamicanimation.animation.SpringAnimation;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public abstract class CalculatorDialog implements View.OnClickListener {

    DecimalFormat df = new DecimalFormat("#.###", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    private Button mCurrentButton;
    private TextView history, nowtext;
    private Activity context;
    private ImageView img_done, luzImageView;
    private Switch luzSwitch;
    private AlertDialog.Builder alertDialog;
    private View view;
    private final char thousandSprt = ',';
    private SharedPreferences preferences;

    public CalculatorDialog(Activity context) {

        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);


        alertDialog = new AlertDialog.Builder(context);
        view = context.getLayoutInflater().inflate(R.layout.alrt_calculator, null);
        alertDialog.setView(view);
        alertDialog.create();
        history = view.findViewById(R.id.history);
        nowtext = view.findViewById(R.id.nowtext);
        img_done = view.findViewById(R.id.done);

        nowtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nowtext.removeTextChangedListener(this);
                try {
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
                    symbols.setGroupingSeparator(thousandSprt);
                    formatter.setDecimalFormatSymbols(symbols);

                    nowtext.setText(formatter.format(Long.parseLong(charSequence.toString().replace(thousandSprt + "", ""))));

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    nowtext.addTextChangedListener(this);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

   /* public static void easyCalculate(Activity c, final TextView et_price, boolean round) {
        easyCalculate(c, et_price, ",", false, round);
    }*/

    /*public static void easyCalculate(Activity c, final TextView et_price, String spliter, final boolean absRslt, final boolean round) {
        double value = 0;
        try {
            value = Double.parseDouble(et_price.getText().toString().trim().replaceAll(spliter, ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        new CalculatorDialog(c) {
            @Override
            public void onResult(Double result) {
//                NumberFormat nf = NumberFormat.getInstance();
                try {
                    double number = result;
                    if (round)
                        number = ((absRslt ? Math.abs(Math.round(number)) : Math.round(number)));
                    else
                        number = (absRslt ? Math.abs(number) : number);
                    et_price.setText(df.format(number));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.setValue(value).showDIalog();
    }*/

    Button[] arrayButton;

    public CalculatorDialog showDIalog() {

        final AlertDialog ad = alertDialog.show();

        Button digit_0 = view.findViewById(R.id.digit_0);
        Button digit_00 = view.findViewById(R.id.digit_00);
        Button digit_000 = view.findViewById(R.id.digit_000);
        Button digit_1 = view.findViewById(R.id.digit_1);
        Button digit_2 = view.findViewById(R.id.digit_2);
        Button digit_3 = view.findViewById(R.id.digit_3);
        Button digit_4 = view.findViewById(R.id.digit_4);
        Button digit_5 = view.findViewById(R.id.digit_5);
        Button digit_6 = view.findViewById(R.id.digit_6);
        Button digit_7 = view.findViewById(R.id.digit_7);
        Button digit_8 = view.findViewById(R.id.digit_8);
        Button digit_9 = view.findViewById(R.id.digit_9);
        Button eq = view.findViewById(R.id.eq);
        Button op_div = view.findViewById(R.id.op_div);
        Button op_mul = view.findViewById(R.id.op_mul);
        Button op_sub = view.findViewById(R.id.op_sub);
        Button op_add = view.findViewById(R.id.op_add);
        Button dot = view.findViewById(R.id.dot);

        view.findViewById(R.id.clr).setOnClickListener(this);
        luzSwitch = view.findViewById(R.id.luzSwitch);
        luzImageView = view.findViewById(R.id.luzImageView);

        arrayButton = new Button[]{digit_0, digit_00, digit_000, digit_1, digit_2, digit_3,
                digit_4, digit_5, digit_6, digit_7, digit_8, digit_9, eq, op_div, op_mul, op_sub, op_add, dot};

        setOnClickListener();

        boolean isChecked = getShared();

        luzSwitch.setChecked(isChecked);
        setState(ad, isChecked);


        view.findViewById(R.id.dlteAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZeroCalculator();
            }
        });
        view.findViewById(R.id.clr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearAnumber();
            }
        });
        img_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onResult(Double.parseDouble(nowtext.getText().toString().trim().replace(thousandSprt + "", "")));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    ad.dismiss();
                }
            }
        });

        luzSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setState(ad, isChecked);
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (width < height) {//istade
            ad.getWindow().setLayout((width * 90 / 100), (height * 70 / 100));
        } else { //khabide
            ad.getWindow().setLayout((width * 60 / 100), (height * 90 / 100));
        }

        return this;
    }

    private void setState(AlertDialog ad, boolean isChecked) {
        if (isChecked) {
            ad.getWindow().getDecorView().setBackgroundResource(R.drawable.bg_dialog_day);
        } else {
            ad.getWindow().getDecorView().setBackgroundResource(R.drawable.bg_dialog_nigth);
        }

        setOnColorButton(isChecked);
        setShared(isChecked);
    }

    private void setShared(boolean isChecked) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isChecked", isChecked);
        editor.apply();
    }

    private boolean getShared() {
        return preferences.getBoolean("isChecked", false);
    }

    private void setOnClickListener() {
        if (arrayButton != null) {
            for (Button button : arrayButton) {
                button.setOnClickListener(this);
            }
        }
    }

    private void setOnColorButton(boolean isDay) {
        if (arrayButton != null) {
            for (Button button : arrayButton) {
                if (isDay) {
                    button.setTextColor(ContextCompat.getColor(context, R.color.pad_advanced_text_color_normal));
                } else {
                    button.setTextColor(ContextCompat.getColor(context, R.color.pad_text_color_normal));
                }
            }
        }

        if (isDay) {
            history.setTextColor(ContextCompat.getColor(context, R.color.history_input_day));
            nowtext.setTextColor(ContextCompat.getColor(context, R.color.display_color_day));
            luzImageView.setImageResource(R.drawable.round_lightbulb_yelow_48dp);
        } else {
            history.setTextColor(ContextCompat.getColor(context, R.color.history_input_nigth));
            nowtext.setTextColor(ContextCompat.getColor(context, R.color.display_color_nigth));
            luzImageView.setImageResource(R.drawable.round_lightbulb_black_48dp);
        }
    }


    public CalculatorDialog ClearAnumber() {
        String S_Nowtext = nowtext.getText().toString();
        String S_history = history.getText().toString();
        if (TextUtils.isEmpty(nowtext.getText()) || S_Nowtext.length() <= 1) {
            nowtext.setText("0");
            if (S_history.length() > 1)
                history.setText(S_history.substring(0, S_history.length() - 1));
            else history.setText("0");
        } else {
            history.setText(S_history.substring(0, S_history.length() - 1));
            nowtext.setText(S_Nowtext.substring(0, S_Nowtext.length() - 1));
        }
        return this;
    }

    public CalculatorDialog ZeroCalculator() {
        if (nowtext != null && history != null) {
            nowtext.setText("0");
            history.setText("0");
        }
        return this;
    }

    public String eval(final String str) {
        return df.format(new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `−` term
            // term = factor | term `×` factor | term `÷` factor
            // factor = `+` factor | `−` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('−') || eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('×')) x *= parseFactor(); // multiplication
                    else if (eat('÷')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('−') || eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse());
    }

    public abstract void onResult(Double result);

    @Override
    public void onClick(View view) {

        img_done.setColorFilter(Color.parseColor("#80ffffff"), PorterDuff.Mode.SRC_IN);
        img_done.setEnabled(false);
        mCurrentButton = (Button) view;
        String S_Nowtext = nowtext.getText().toString();
        String S_history = history.getText().toString();
        String S_btn = mCurrentButton.getText().toString();
        char lastChar = S_history.charAt(S_history.length() - 1);
        int ViewID = view.getId();
        if (ViewID == R.id.eq) {
            try {
                String natije;
                if (Character.isDigit(S_history.charAt(S_history.length() - 1)))
                    natije = eval(S_history);
                else natije = eval(S_history.substring(0, S_history.length() - 1));
                nowtext.setText(natije);
                history.setText(natije);
                img_done.setColorFilter(null);
                img_done.setEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (ViewID == R.id.op_add || ViewID == R.id.op_sub || ViewID == R.id.op_mul || ViewID == R.id.op_div) {
            char perlastChar = (S_history.length() >= 2) ? S_history.charAt(S_history.length() - 2) : 0;
            if (Character.isDigit(lastChar)) {
                nowtext.setText("0");
                if (S_history.trim().equals("0") && S_btn.trim().equals(context.getString(R.string.op_sub)))
                    history.setText(S_btn);
                else
                    history.append(S_btn);
            } else {
                if (S_btn.trim().equals(context.getString(R.string.op_sub))) {

                    if (lastChar != context.getString(R.string.op_sub).toCharArray()[0])
                        history.append(S_btn);
                } else {
                    if (!Character.isDigit(perlastChar))
                        history.setText((S_history.substring(0, S_history.length() - 2)) + S_btn);
                    else
                        history.setText((S_history.substring(0, S_history.length() - 1)) + S_btn);
                }
            }
        } else if (ViewID == R.id.dot) {
            if (Character.isDigit(lastChar) && !S_Nowtext.contains(".")) {
                history.append(".");
                nowtext.append(".");
            }
        } else {
            if (S_Nowtext.trim().equals("0"))
                nowtext.setText(S_btn);
            else
                nowtext.append(S_btn);
            /////
            if (S_history.trim().equals("0"))
                history.setText(S_btn);
            else
                history.append(S_btn);
        }
    }

    public CalculatorDialog setValue(double input) {
        history.setText(df.format(input));
        nowtext.setText(df.format(input));
        return this;

    }
}


