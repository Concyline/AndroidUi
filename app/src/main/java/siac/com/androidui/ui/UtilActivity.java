package siac.com.androidui.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import br.com.utill.BackgroundTask;
import br.com.utill.Util;
import br.com.utill.listeners.OnListnerAlertSimCancelar;
import br.com.utill.listeners.OnListnerOk;
import siac.com.androidui.R;


public class UtilActivity extends AppCompatActivity {

    Button button16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);

        button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executInBackground();
            }
        });
    }

    public void abaixateclado(View view) {
        Util.abaixaTeclado(getBaseContext(), view);
    }

    public void toastLong(View view) {
        Util.toastLong(getBaseContext(),"Long Toast");
    }

    public void toastShort(View view) {
        Util.toastShort(getBaseContext(), "Short Toast");
    }

    public void fadeIn(View view) {
        Util.fadeIn(getBaseContext(), view);
    }

    public void alertOk(View view) {
        Util.alertOk(UtilActivity.this, "Message");
    }

    public void alertOkListener(View view) {
        Util.alertOk(UtilActivity.this, "Message", new OnListnerOk() {
            @Override
            public void ok() {

            }
        });
    }

    public void alertSimCancelarListener(View view) {
        Util.alertSimCancelar(UtilActivity.this, "Message", new OnListnerAlertSimCancelar() {
            @Override
            public void sim() {
                // aqui
            }

            @Override
            public void cancelar() {

            }
        });
    }

    public void setActionBar(View view) {
        Util.setBar(UtilActivity.this, "Title", "Subtitle");
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
                        progressDialog = ProgressDialog.show(UtilActivity.this, "Aguarde", "Carregando os clientes...", false, false);
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