package siac.com.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import siac.com.componentes.EditTextLegenda;

public class ComponentesActivity extends AppCompatActivity {

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
    }
}
