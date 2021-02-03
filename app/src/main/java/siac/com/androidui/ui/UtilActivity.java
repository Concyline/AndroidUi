package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import br.com.utill.Util;
import br.com.utill.listeners.OnListnerAlertSimCancelar;
import br.com.utill.listeners.OnListnerOk;
import siac.com.androidui.R;


public class UtilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util);
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
}