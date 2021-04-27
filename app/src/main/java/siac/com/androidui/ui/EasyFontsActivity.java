package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.com.utill.easyfonts.EasyFonts;
import siac.com.androidui.R;

public class EasyFontsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_fonts);

        ((TextView)findViewById(R.id.textView1)).setTypeface(EasyFonts.robotoBold(this));
        ((TextView)findViewById(R.id.textView2)).setTypeface(EasyFonts.androidNationBold(this));
        ((TextView)findViewById(R.id.textView3)).setTypeface(EasyFonts.droidSerifBold(this));
        ((TextView)findViewById(R.id.textView4)).setTypeface(EasyFonts.walkwayBold(this));
        ((TextView)findViewById(R.id.textView5)).setTypeface(EasyFonts.ostrichBold(this));
        ((TextView)findViewById(R.id.textView6)).setTypeface(EasyFonts.caviarDreamsBold(this));
        ((TextView)findViewById(R.id.textView7)).setTypeface(EasyFonts.tangerineRegular(this));
        ((TextView)findViewById(R.id.textView8)).setTypeface(EasyFonts.recognition(this));
        ((TextView)findViewById(R.id.textView9)).setTypeface(EasyFonts.droidSerifRegular(this));
        ((TextView)findViewById(R.id.textView10)).setTypeface(EasyFonts.freedom(this));
        ((TextView)findViewById(R.id.textView11)).setTypeface(EasyFonts.funRaiser(this));
        ((TextView)findViewById(R.id.textView12)).setTypeface(EasyFonts.greenAvocado(this));
        ((TextView)findViewById(R.id.textView13)).setTypeface(EasyFonts.windSong(this));
        ((TextView)findViewById(R.id.textView14)).setTypeface(EasyFonts.ostrichBlack(this));
        ((TextView)findViewById(R.id.textView15)).setTypeface(EasyFonts.droidRobot(this));
    }
}