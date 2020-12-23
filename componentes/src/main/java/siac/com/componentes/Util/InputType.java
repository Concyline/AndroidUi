package siac.com.componentes.Util;

import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import static siac.com.componentes.Util.Constantes.none;
import static siac.com.componentes.Util.Constantes.numberDecimal;
import static siac.com.componentes.Util.Constantes.textCapCharacters;
import static siac.com.componentes.Util.Constantes.textEmailAddress;
import static siac.com.componentes.Util.Constantes.textMultiLine;
import static siac.com.componentes.Util.Constantes.textMultiLineAllCaps;
import static siac.com.componentes.Util.Constantes.textPassword;
import static siac.com.componentes.Util.Constantes.number;

public class InputType {

    public static void setInputType(EditText editText, int inputType, int lines) {
        switch (inputType) {
            case none: {
                editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
                return;
            }
            case textPassword: {
                editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
                return;
            }
            case numberDecimal: {
                editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
                return;
            }
            case textMultiLine: {
                editText.setGravity(Gravity.TOP | Gravity.LEFT);
                editText.setSingleLine(false);
                editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                editText.setLines(lines);
                editText.setMaxLines(lines);
                editText.setMovementMethod(new ScrollingMovementMethod());
                return;
            }
            case textMultiLineAllCaps: {
                editText.setGravity(Gravity.TOP | Gravity.LEFT);
                editText.setSingleLine(false);
                editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                editText.setLines(lines);
                editText.setMaxLines(lines);
                editText.setMovementMethod(new ScrollingMovementMethod());
                editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                return;
            }
            case textCapCharacters: {
                editText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                return;
            }
            case number: {
                editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
                return;
            }
            case textEmailAddress:{
                editText.setInputType(android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                return;
            }
        }
    }
}
