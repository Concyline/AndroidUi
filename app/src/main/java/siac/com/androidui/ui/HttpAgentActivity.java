package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.httpagent.httpagent.HTTP;
import br.com.httpagent.httpagent.HttpAgent;
import br.com.httpagent.httpagent.StringCallback;
import siac.com.androidui.R;

public class HttpAgentActivity extends AppCompatActivity {

    EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_agent);

        urlEditText = findViewById(R.id.urlEditText);

        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new HttpAgent(HttpAgentActivity.this, urlEditText.getText().toString().trim(), HTTP.GET)
                        //new HttpAgent(MainActivity.this,"http://10.0.2.2:8080/SiacAPI/Login", HTTP.POST)
                        //new HttpAgent(MainActivity.this,"http://10.0.2.2:8080/SiacAPI/Minutas", HTTP.GET)
                        //.headers("Authorization", "Bearer " + token, "Content-Type", "application/json")
                        .headers("Content-Type", "application/json")
                        //.setTokenBearer(token)
                        //.withBody(content)
                        .goString(new StringCallback() {
                            @Override
                            protected void onDone(boolean success, String stringResults) {

                                EditText editText = findViewById(R.id.editTextTextMultiLine);

                                if (success) {
                                    editText.setText(stringResults);
                                } else {
                                    editText.setText(getErrorMessage());
                                }
                            }
                        });

            }
        });
    }
}