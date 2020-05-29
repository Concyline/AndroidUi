package siac.com.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import siac.com.leitor.LeitorActivity;

public class LerQrBarCodeActivity extends AppCompatActivity {

    EditText retornoEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ler_qr_bar_code);

        retornoEditText = findViewById(R.id.retornoEditText);

        Button lerQrTesteButton = findViewById(R.id.lerQrTesteButton);
        lerQrTesteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LeitorActivity.class);
                String codigo = "C=7898958119652;L=50962;V=30/09/2019";
                intent.putExtra("teste_componete_cardview",codigo);
                startActivityForResult(intent, 0);
            }
        });

        Button lerQrButton = findViewById(R.id.lerQrButton);
        lerQrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LeitorActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        retornoEditText.setText("");
        if (data != null) {
            retornoEditText.setText(data.getStringExtra("CODIGO"));
        }
    }
}
