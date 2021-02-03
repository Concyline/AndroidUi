package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.hawk.Hawk;
import siac.com.androidui.R;
import siac.com.componentes.EditTextLegenda;

public class HawkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halk);

        Hawk.init(this).build();

        final EditTextLegenda setEditTextLegenda = findViewById(R.id.setEditTextLegenda);
        final EditTextLegenda valuesEditTextLegenda = findViewById(R.id.valuesEditTextLegenda);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("VAL",setEditTextLegenda.getString());
            }
        });

        findViewById(R.id.readDataButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuesEditTextLegenda.setText(Hawk.get("VAL").toString());
            }
        });


    }
}