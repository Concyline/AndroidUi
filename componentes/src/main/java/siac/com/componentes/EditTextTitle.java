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
import android.text.InputFilter;
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

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Date;

import siac.com.componentes.Util.Mascara;
import siac.com.componentes.Util.Util;
import siac.com.componentes.Util.ValidaCNPJ;
import siac.com.componentes.Util.ValidaCPF;

import static siac.com.componentes.Util.Constantes.cnpj;
import static siac.com.componentes.Util.Constantes.cpf;
import static siac.com.componentes.Util.InputType.setInputType;
import static siac.com.componentes.Util.Util.fadeIn;
import static siac.com.componentes.Util.Util.shake;


public class EditTextTitle extends FrameLayout {

    // COMPONENTES
    private TextView legendaTextView;
    private EditText editText;
    private ImageView iconLeftImageView, iconRigthImageView, requiredImageView;

    // ATRIBUTOS
    private String title = "title";
    private String hint = "";
    private String text = "";
    private ColorStateList colorTitle;
    private float tamTitle = 13;
    private float tamTextEditText = 16;
    private int inputType = 0;
    private String mascara;
    private int lines = 1;
    private int maxLength = 0;
    private int coricon;
    private boolean enabled;
    private boolean focusable;
    private boolean requestfocus;
    private boolean requerido;
    private boolean iconRigthVisible;
    private String tag;
    private Drawable iconLeft;
    private Drawable iconRigth;
    private Mascara mascaraDinamica;
    private int mascaraVigente = cnpj;
    private Date dateResult;
    private String legendaRequerido;


    public EditTextTitle(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public EditTextTitle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public EditTextTitle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditTextLegenda, defStyleAttr, 0);
            title = typedArray.getString(R.styleable.EditTextLegenda_title);
            colorTitle = typedArray.getColorStateList(R.styleable.EditTextLegenda_colorTitle);
            tamTitle = getSizeFontLegendaEditText(typedArray.getString(R.styleable.EditTextLegenda_tamTitle));
            tamTextEditText = getSizeFontTextEditText(typedArray.getString(R.styleable.EditTextLegenda_tamTextEditText));

            mascara = typedArray.getString(R.styleable.EditTextLegenda_mascara);
            inputType = typedArray.getInteger(R.styleable.EditTextLegenda_inputType, 0);
            lines = typedArray.getInteger(R.styleable.EditTextLegenda_lines, 1);
            maxLength = typedArray.getInteger(R.styleable.EditTextLegenda_maxLength, 0);
            hint = typedArray.getString(R.styleable.EditTextLegenda_hint);
            text = typedArray.getString(R.styleable.EditTextLegenda_text);

            iconLeft = typedArray.getDrawable(R.styleable.EditTextLegenda_iconLeft);
            iconRigth = typedArray.getDrawable(R.styleable.EditTextLegenda_iconRigth);
            coricon = typedArray.getColor(R.styleable.EditTextLegenda_coricon, 0);

            enabled = typedArray.getBoolean(R.styleable.EditTextLegenda_enabled, true);
            focusable = typedArray.getBoolean(R.styleable.EditTextLegenda_focusable, true);
            requestfocus = typedArray.getBoolean(R.styleable.EditTextLegenda_requestfocus, false);
            requerido = typedArray.getBoolean(R.styleable.EditTextLegenda_requerido, false);
            legendaRequerido = getString(typedArray, R.styleable.EditTextLegenda_legendaRequerido, "Item requerido no formulário");
            iconRigthVisible = typedArray.getBoolean(R.styleable.EditTextLegenda_iconRigthVisible, false);
            tag = typedArray.getString(R.styleable.EditTextLegenda_tag);
            return;
        }

    }

    private String getString(TypedArray typedArray, int index, String defaultValue){
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
        inflate(getContext(), R.layout.view_edittext_legenda_ui, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        editText = findViewById(R.id.editText);
        iconLeftImageView = findViewById(R.id.iconLeftImageView);
        iconRigthImageView = findViewById(R.id.iconRigthImageView);
        requiredImageView = findViewById(R.id.requiredImageView);
        setup();
    }

    private void setup() {
        legendaTextView.setText(title);
        if(colorTitle != null) {
            legendaTextView.setTextColor(colorTitle);
        }
        legendaTextView.setTextSize(tamTitle);

        editText.setHint(hint);
        editText.setText(text != null ? text : "");
        editText.setEnabled(enabled);
        editText.setFocusable(focusable);
        editText.setTextSize(tamTextEditText);
        editText.setTag(tag);

        if (maxLength > 0) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }

        if (requestfocus) {
            setFocus();
        }

        if (requerido) {
            setRequerido();
        }

        setIconRigthVisible(iconRigthVisible);

        setIcon();
        setInputType(editText, inputType, lines);
        if(inputType == 0x00000081){
            setListnerSenha();
        }

        if (mascara != null) {
            mascaraDinamica = new Mascara(editText, mascara);
        }
    }

    private void setIcon() {
        if (iconLeft != null) {
            iconLeftImageView.setVisibility(View.VISIBLE);
            iconLeftImageView.setColorFilter(new PorterDuffColorFilter(coricon, PorterDuff.Mode.SRC_IN));
            iconLeftImageView.setImageDrawable(iconLeft);
        }

        if (iconRigth != null) {
            iconRigthImageView.setVisibility(View.VISIBLE);
            iconRigthImageView.setColorFilter(new PorterDuffColorFilter(coricon, PorterDuff.Mode.SRC_IN));
            iconRigthImageView.setImageDrawable(iconRigth);
        }
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

    public void setIconRigthVisible(boolean visible){
        if(visible){
            iconRigthImageView.setVisibility(View.VISIBLE);
        }else{
            iconRigthImageView.setVisibility(View.GONE);
        }
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
            if (mascara != null) {
                return retiraCaracteres(editText.getText().toString()).length() > 0 ? true : false;
            } else {
                return editText.getText().toString().length() > 0 ? true : false;
            }
        }
        return true;
    }

    private String retiraCaracteres(String current) {
        return current.replaceAll("[^0-9]*", "");
    }

    public boolean validaCpfCnpj() {
        if (mascaraVigente == cpf) {
            return ValidaCPF.isCPF(getString());
        }
        if (mascaraVigente == cnpj) {
            return ValidaCNPJ.isCNPJ(getString());
        }

        return false;
    }

    public void setError() {
        requiredImageView.setColorFilter(Color.RED);
        shake(getContext(), requiredImageView);
    }

    public void removeError() {
        requiredImageView.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN));
    }

    boolean controle = true;
    public void mostraSenha(){
        if(controle) {
            controle = false;
            setInputType(editText, 0x00000000, lines);

            iconRigth = getResources().getDrawable(R.drawable.round_visibility_off_black_48dp);
        }else {
            controle = true;
            setInputType(editText, 0x00000081, lines);

            iconRigth = getResources().getDrawable(R.drawable.round_visibility_black_48dp);
        }
        setIcon();
        editText.setSelection(editText.getText().length());
    }

    public void setSelection(int index){
        editText.setSelection(index);
    }

    private void setListnerSenha(){
        iconRigthImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(getContext(), v);
                mostraSenha();
            }
        });
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        editText.setOnClickListener(onClickListener);
    }

    public void setOnClickListenerIconLeft(OnClickListener onClickListener) {
        iconLeftImageView.setOnClickListener(onClickListener);
    }

    public void setOnClickListenerIconRigth(OnClickListener onClickListener) {
        iconRigthImageView.setOnClickListener(onClickListener);
    }

    public Date getDate(){
        if(dateResult != null){
            return dateResult;
        }
        return null;
    }

    public void setDate(Date date){
        dateResult = date;
        editText.setText(Util.dateToStr(dateResult));
    }

    public void setDateHour(Date date){
        dateResult = date;
        editText.setText(Util.dateHora(dateResult));
    }

    public Dialog createDialogData(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar calendar = Calendar.getInstance();
        return new DatePickerDialog(getContext(), mDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public void setMascara(int mascara) {
        mascaraVigente = mascara;

        if (mascara == cpf) {
            legendaTextView.setText("CPF");
            mascaraDinamica.changeMask("   .   .   /  ");

        } else if (mascara == cnpj) {
            legendaTextView.setText("CNPJ");
            mascaraDinamica.changeMask("  .   .   /    -  ");

        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setFocus() {
        editText.requestFocus();
    }

    public void setEnabled(boolean enabled) {
        editText.setEnabled(enabled);
        iconLeftImageView.setEnabled(enabled);
        iconRigthImageView.setEnabled(enabled);
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
            return Double.parseDouble(editText.getText().toString().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public void setFilters(InputFilter[] filters){
        editText.setFilters(filters);
    }

    public void setHint(String text) {
        editText.setHint(text);
    }
}
