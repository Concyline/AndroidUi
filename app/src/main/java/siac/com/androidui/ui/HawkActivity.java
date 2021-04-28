package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import br.com.hawk.Encryption;
import br.com.hawk.Hawk;
import br.com.hawk.LogInterceptor;
import br.com.hawk.NoEncryption;
import siac.com.androidui.R;
import siac.com.componentes.EditTextTitle;

public class HawkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halk);

        Hawk.init(this)
                .setEncryption(new AjaEncryption())
                .setLogInterceptor(new LogInterceptor() {
                    @Override
                    public void onLog(String message) {
                        System.out.println("LOG HALK: "+message);
                    }
                })
                .build();

        final EditTextTitle setEditTextLegenda = findViewById(R.id.setEditTextLegenda);
        final EditTextTitle valuesEditTextLegenda = findViewById(R.id.valuesEditTextLegenda);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("VAL",setEditTextLegenda.getString());
            }
        });

        findViewById(R.id.readDataButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valuesEditTextLegenda.setText(Hawk.get("VAL").toString());
            }
        });


    }

    public class AjaEncryption implements Encryption {
        @Override public boolean init() {
            return true;
        }

        @Override public String encrypt(String key, String value) throws Exception {
            return value;
        }

        @Override public String decrypt(String key, String value) throws Exception {
            return value;
        }
    }
}