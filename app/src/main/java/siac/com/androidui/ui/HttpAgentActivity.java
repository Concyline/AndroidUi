package siac.com.androidui.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.httpagent.HTTP;
import br.com.httpagent.HttpAgent;
import br.com.httpagent.StringCallback;
import br.com.utill.BackgroundTask;
import siac.com.androidui.R;

public class HttpAgentActivity extends AppCompatActivity {

    EditText urlEditText;
    Button button16;

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

        button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executInBackground();
            }
        });
    }

    int cont = 0;
    private ProgressDialog progressDialog;

    private void executInBackground() {

        BackgroundTask.with(this) // Activity|FragmentActivity(v4)|Fragment|Fragment(v4)
                .assign(new BackgroundTask.TaskDescription() {
                    @Override
                    public Object onBackground() {
                        // Do what you want to do on background thread.
                        // If you want to post something to MainThread,
                        // just call SugarTask.post(YOUR_MESSAGE).

                        // Return your finally result(Nullable).

                        do {
                            cont++;

                            Message message = Message.obtain();
                            message.obj = cont;

                            BackgroundTask.post(message);

                            if (cont == 10) {
                                cont = 10 / 0;
                            }

                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cont < 15);

                        return "Finalizado com sucesso!";
                    }
                })
                .preExecute(new BackgroundTask.PreExecuteListener() {
                    @Override
                    public void onPreExecute() {
                        progressDialog = ProgressDialog.show(HttpAgentActivity.this, "Aguarde", "Carregando os clientes...", false, false);
                    }
                })
                .handle(new BackgroundTask.MessageListener() {
                    @Override
                    public void handleMessage(@NonNull Message message) {
                        // Receive message in MainThread which sent from WorkerThread,
                        // update your UI just in time.
                        button16.setText("Message " + message.obj);
                    }
                })
                .finish(new BackgroundTask.FinishListener() {
                    @Override
                    public void onFinish(@Nullable Object result) {
                        // If WorkerThread finish without Exception and lifecycle safety,
                        // deal with your WorkerThread result at here.
                        button16.setText(result.toString());
                    }
                })
                .broken(new BackgroundTask.BrokenListener() {
                    @Override
                    public void onBroken(@NonNull Exception e) {
                        // If WorkerThread finish with Exception and lifecycle safety,
                        // deal with Exception at here.
                        e.printStackTrace();
                        button16.setText(e.getMessage());

                        if(progressDialog != null){
                            progressDialog.dismiss();
                        }
                    }
                })
                .execute();
    }
}