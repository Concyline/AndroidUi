package siac.com.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import siac.com.leitor.LeitorActivity;
import siac.com.texto.ManipulaTexto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ManipulaTexto.init("OS","log.txt");

        ManipulaTexto manipulaTexto = ManipulaTexto.getInstance();

        manipulaTexto.erro("erro teste");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LeitorActivity.class);

                String codigo = "C=7898958119652;L=50962;V=30/09/2019";
                intent.putExtra("teste",codigo);

                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            System.out.println(data.getStringExtra("CODIGO"));
        }
    }
}
