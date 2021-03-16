package siac.com.componentes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditTextCalendar extends FrameLayout {

    private SimpleDateFormat format;

    // COMPONENTES
    private TextView legendaTextView;
    private EditText editText;
    private ImageView horaImageView, dataImageView;

    // ATRIBUTOS
    private String title = "title";
    private ColorStateList colorTitle;

    private float tamTitle = 13;
    private boolean hora;
    private boolean inicializa;
    private Date dataColetada;

    public EditTextCalendar(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public EditTextCalendar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public EditTextCalendar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditTextCalendarLegenda, defStyleAttr, 0);
            title = typedArray.getString(R.styleable.EditTextCalendarLegenda_title);
            colorTitle = typedArray.getColorStateList(R.styleable.EditTextCalendarLegenda_colorTitle);
            tamTitle = getSizeFontLegendaEditText(typedArray.getString(R.styleable.EditTextCalendarLegenda_tamTitle));
            hora = typedArray.getBoolean(R.styleable.EditTextCalendarLegenda_hora, false);
            inicializa = typedArray.getBoolean(R.styleable.EditTextCalendarLegenda_inicializa, false);

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
        inflate(getContext(), R.layout.view_edittext_calendar_legenda_ui, this);
        legendaTextView = findViewById(R.id.legendaTextView);
        editText = findViewById(R.id.editText);
        horaImageView = findViewById(R.id.horaImageView);
        dataImageView = findViewById(R.id.dataImageView);
        setup();
    }

    private void setup() {
        legendaTextView.setText(title);
        if (colorTitle != null) {
            legendaTextView.setTextColor(colorTitle);
        }
        legendaTextView.setTextSize(tamTitle);

        if (hora) {
            format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            editText.setHint("  /  /      :  :  ");

        } else {
            format = new SimpleDateFormat("dd/MM/yyyy");
            editText.setHint("  /  /    ");
            horaImageView.setVisibility(GONE);

        }

        if (inicializa) {
            dataColetada = new Date();
            editText.setText(format.format(dataColetada));
        }

        horaImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(v);
                dataColetada = new Date();
                editText.setText(format.format(dataColetada));
            }
        });

        dataImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(v);

                createDialogData(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar date = Calendar.getInstance();
                        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth,
                                date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), Calendar.SECOND);

                        dataColetada = calendar.getTime();
                        editText.setText(format.format(dataColetada));
                    }
                }).show();
            }
        });
    }

    public Date getDate() {
        return dataColetada;
    }

    public Date getDate(boolean valida) {
        if (dataColetada != null) {
            return dataColetada;
        }

        return new Date();
    }

    public void setDate(Date date) {
        this.dataColetada = date;
        if (format != null) {
            editText.setText(format.format(dataColetada));
        }
    }

    public void fadeIn(View button) {
        Animation in = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        button.startAnimation(in);
    }

    public Dialog createDialogData(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar calendar = Calendar.getInstance();
        return new DatePickerDialog(getContext(), mDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }
}
