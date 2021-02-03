package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import siac.com.androidui.R;
import siac.com.componentes.ZoomFrameImageView;

public class ZoomFrameImageViewActivity extends AppCompatActivity {

    ZoomFrameImageView zoomFrameImageView;
    int index = -1;
    int[] array = new int[]{R.drawable.image_1, R.drawable.image_2, R.drawable.image_3};
     Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_frame_image_view);

        zoomFrameImageView = findViewById(R.id.fragmentloginKenBurnsView1);
        handler = new Handler();
        chama();
    }

    private void chama(){
        index++;

        if(index>= array.length){
            index = -1;
            zoomFrameImageView.restart();
            chama();
            //return;
        }

        setImage(array[index]);
    }

    private void setImage(final int image){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                zoomFrameImageView.setImageResource(image);
                chama();
            }
        }, 5000);
    }
}