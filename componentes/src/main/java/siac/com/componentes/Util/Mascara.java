package siac.com.componentes.Util;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class Mascara {

    private EditText editText;
    private String mascara;
    private TextWatcher textWatcher;
    private int maxNumberLength;
    private static boolean isUpdating;
    static int positioning[];

    public Mascara(EditText editText, String mascara) {
        this.editText = editText;
        this.mascara = mascara;

        setMascara();
    }

    public void changeMask(String mascara) {
        if (textWatcher != null) {
            editText.removeTextChangedListener(textWatcher);
            this.mascara = mascara;

            setMascara();
        }
    }

    public void setMascara() {
        maxNumberLength = quantidadeNumeros();

        editText.setText(mascara);
        editText.setSelection(1);

        editText.setKeyListener(keylistenerNumber);

        if (textWatcher != null) {
            editText.removeTextChangedListener(textWatcher);
            textWatcher = null;
        }

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void afterTextChanged(Editable s) {
                String current = s.toString();

                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                String number = retiraCaracteres(current);

                if (editText.getTag() != null && editText.getTag().equals("telefone")) {
                    if (number.length() <= 8) {
                        mascara = "    -    ";
                    } else if (number.length() == 9) {
                        mascara = "     -    ";
                    } else {
                        mascara = "     -    ";
                        number = number.substring(0, 9);
                    }

                } else {
                    if (number.length() > maxNumberLength) {
                        number = number.substring(0, maxNumberLength);
                    }
                }

                positioning = positions();

                String paddedNumber = colocaEspacosRespectivos(number, maxNumberLength);

                String res = "";
                int contNumber = 0;

                for (int i = 0; i < mascara.toCharArray().length; i++) {

                    char c = mascara.toCharArray()[i];

                    if (String.valueOf(c).equals(" ")) {
                        res += paddedNumber.toCharArray()[contNumber];
                        contNumber++;
                    } else {
                        res += c;
                    }
                }

                isUpdating = true;
                editText.setText(res);
                editText.setSelection(positioning[number.length()]);
            }
        };

        editText.addTextChangedListener(textWatcher);
    }

    private int quantidadeNumeros() {
        int count = 0;
        for (char c : mascara.toCharArray()) {
            if (String.valueOf(c).equals(" ")) {
                count++;
            }
        }
        return count;
    }

    private int[] positions() {

        List<Integer> positioning = new ArrayList<>();
        positioning.add(0);

        int count = 1;
        for (char c : mascara.toCharArray()) {
            if (String.valueOf(c).equals(" ")) {
                positioning.add(count);
            }
            count++;
        }

        int[] retorno = new int[positioning.size()];
        for (int i = 0; i < positioning.size(); i++) {
            retorno[i] = positioning.get(i);
        }
        return retorno;
    }

    protected String colocaEspacosRespectivos(String number, int maxLength) {
        String padded = new String(number);
        for (int i = 0; i < maxLength - number.length(); i++)
            padded += " ";
        return padded;
    }

    private static final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();

    private static class KeylistenerNumber extends NumberKeyListener {

        public int getInputType() {
            return InputType.TYPE_CLASS_NUMBER
                    | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;

        }

        @Override
        protected char[] getAcceptedChars() {
            return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        }
    }

    private static String retiraCaracteres(String current) {
        return current.replaceAll("[^0-9]*", "");
    }
}
