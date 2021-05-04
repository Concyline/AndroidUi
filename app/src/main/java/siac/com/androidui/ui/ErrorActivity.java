package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.com.error.uce.UnCaughtException;
import siac.com.androidui.R;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        //new UnCaughtException.Builder(this).build();

        int a = 10 / 0;
    }
}