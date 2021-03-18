package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import br.com.calculadora.CalculatorDialog;
import siac.com.androidui.R;

public class CalculadoraDialogActivity extends AppCompatActivity {

    EditText editTextTextPersonName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_dialog);

        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);

        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalculator(editTextTextPersonName2);
            }
        });
    }

    public void showCalculator(final EditText editText) {
        try {
            new CalculatorDialog(this) {
                @Override
                public void onResult(Double result) {
                    editText.setText(result + "");
                }
            }.setValue(Double.parseDouble(editText.getText().toString().trim())).showDIalog();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}