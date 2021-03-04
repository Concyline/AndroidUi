package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import siac.com.androidui.R;
import siac.com.componentes.EditTextLegenda;

public class ComponentesActivity extends AppCompatActivity {

    EditTextLegenda multilineEditTextLegenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes);

        final EditTextLegenda senhaEditTextLegenda = findViewById(R.id.senhaEditTextLegenda);
        senhaEditTextLegenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senhaEditTextLegenda.mostraSenha();
            }
        });

        EditTextLegenda editTextLegendaSelection = findViewById(R.id.editTextLegendaSelection);
        editTextLegendaSelection.setSelection(editTextLegendaSelection.getString().length() - 2);

    }
}
