package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.utill.geolocation.EasyLocationFetch;
import br.com.utill.geolocation.GeoLocationModel;
import siac.com.androidui.R;

public class EasyLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_location);


        findViewById(R.id.button23).setOnClickListener(v -> {

            EditText editText = findViewById(R.id.editTextTextMultiLine2);
            GeoLocationModel geoLocationModel = new EasyLocationFetch(this).getLocationData();

            if(geoLocationModel == null){
                editText.setText("O sistema não conseguio recuperar a localização!");
                return;
            }

            StringBuilder builder = new StringBuilder();

            builder.append("Address:   "+geoLocationModel.getAddress() +"\r\n");
            builder.append("City:      "+geoLocationModel.getCity() +"\r\n");
            builder.append("Lattitude: "+geoLocationModel.getLattitude() +"\r\n");
            builder.append("Longitude: "+geoLocationModel.getLongitude() +"\r\n");

            editText.setText(builder.toString());

        });

    }
}