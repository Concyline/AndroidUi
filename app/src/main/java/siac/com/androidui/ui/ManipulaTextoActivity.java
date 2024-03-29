package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import siac.com.androidui.R;
import siac.com.texto.storagesd.StorageSD;

public class ManipulaTextoActivity extends AppCompatActivity {

    EditText resultadoEditText, textoEditText;
    Button gravarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipula_texto);

        StorageSD
                .init(this)
                .setFolderAndFileName("Manipula", "Log.txt")
                .build();


        textoEditText = findViewById(R.id.textoEditText);
        resultadoEditText = findViewById(R.id.resultadoEditText);
        gravarButton = findViewById(R.id.gravarButton);

        gravarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    StorageSD.info(textoEditText.getText().toString());
                    textoEditText.setText("");
                    montaTela();
                } catch (Exception e) {
                    e.printStackTrace();
                    StorageSD.processaException(this.getClass().getName(), e);
                }
            }
        });

        montaTela();
    }

    private void montaTela() {
        //String all = log.getAll();
        String all = StorageSD.getAll();

        resultadoEditText.setText(all);
    }
}
