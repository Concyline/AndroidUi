package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import siac.com.androidui.R;
import siac.com.componentes.EditTexCurrency;
import siac.com.componentes.EditTextTitle;

public class ComponentesActivity extends AppCompatActivity {

    EditTextTitle multilineEditTextLegenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes);

        final EditTextTitle senhaEditTextLegenda = findViewById(R.id.senhaEditTextLegenda);
        senhaEditTextLegenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senhaEditTextLegenda.mostraSenha();
            }
        });

        EditTextTitle editTextLegendaSelection = findViewById(R.id.editTextLegendaSelection);
        editTextLegendaSelection.setSelection(editTextLegendaSelection.getString().length() - 2);

        final EditTexCurrency editTexCurrencyLegenda = findViewById(R.id.editTexFinaceirotLegenda);

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double aqui = editTexCurrencyLegenda.getDouble();

                System.out.println("" + editTexCurrencyLegenda.getDouble());
            }
        });

    }
}
