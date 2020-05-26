package siac.com.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import siac.com.texto.ManipulaTexto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ManipulaTexto.init("OS","log.txt");

        ManipulaTexto manipulaTexto = ManipulaTexto.getInstance();

        manipulaTexto.erro("erro teste");

    }
}
