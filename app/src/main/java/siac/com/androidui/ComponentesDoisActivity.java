package siac.com.androidui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import siac.com.componentes.ProgressButton;
import siac.com.componentes.ProgressImageView;

public class ComponentesDoisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes_dois);

        final ProgressImageView progressImageView = findViewById(R.id.progressImageView);
        progressImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressImageView.setProgres();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressImageView.removeProgres();
                    }
                }, 2000);
            }
        });

        final ProgressImageView lupaProgressImageView = findViewById(R.id.lupaProgressImageView);
        lupaProgressImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lupaProgressImageView.setProgres();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lupaProgressImageView.removeProgres();
                    }
                }, 2000);
            }
        });

        final ProgressButton progressButton = findViewById(R.id.progressButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressButton.setProgres();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressButton.removeProgres();
                    }
                }, 2000);
            }
        });

        final ProgressButton progressButtonOk = findViewById(R.id.progressButtonOk);
        progressButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressButtonOk.setProgres();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressButtonOk.removeProgres();
                    }
                }, 2000);
            }
        });




    }

    public void vibrate(View button) {
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        button.startAnimation(in);
    }
}
