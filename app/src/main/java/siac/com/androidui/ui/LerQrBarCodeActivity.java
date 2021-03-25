package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import siac.com.androidui.R;
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
                intent.putExtra(LeitorActivity.CODE_TEST,codigo);
                startActivityForResult(intent, 123);
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

        if(requestCode == 123) {
            retornoEditText.setText("");
            if (data != null) {
                retornoEditText.setText(data.getStringExtra("CODIGO"));
            }
        }
    }
}
