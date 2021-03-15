package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import siac.com.androidui.R;
import siac.com.componentes.EditTexCurrencySubtitle;
import siac.com.componentes.EditTextSubtitle;

public class ComponentesActivity extends AppCompatActivity {

    EditTextSubtitle multilineEditTextLegenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes);

        final EditTextSubtitle senhaEditTextLegenda = findViewById(R.id.senhaEditTextLegenda);
        senhaEditTextLegenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senhaEditTextLegenda.mostraSenha();
            }
        });

        EditTextSubtitle editTextLegendaSelection = findViewById(R.id.editTextLegendaSelection);
        editTextLegendaSelection.setSelection(editTextLegendaSelection.getString().length() - 2);

        final EditTexCurrencySubtitle editTexCurrencyLegenda = findViewById(R.id.editTexFinaceirotLegenda);

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(""+editTexCurrencyLegenda.getDouble());
            }
        });

    }
}
