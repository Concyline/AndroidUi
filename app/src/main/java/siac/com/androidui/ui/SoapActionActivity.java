package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.httpagent.soapaction.JSoapCallback;
import br.com.httpagent.soapaction.JsoapError;
import br.com.httpagent.soapaction.SOAPManager;
import siac.com.androidui.R;
import siac.com.androidui.entidades.Cidade;
import siac.com.androidui.entidades.Parametros;
import siac.com.androidui.entidades.Response;

public class SoapActionActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soap_action);

        list = findViewById(R.id.list);

        get();
    }

    private void get() {
        String url = "http://10.0.2.2:2193/Integracao.asmx";
        String namespace = "http://tempuri.org/";
        String method = "GetCidades";
        String soap_action = "http://tempuri.org/GetCidades";

        SOAPManager.get(namespace, url, method, soap_action, new Parametros("01/01/1000"), Response.class, new JSoapCallback() {

            @Override
            public void onSuccess(Object result) {
                Response res = (Response) result;
                setAdapter(res.result);
            }

            @Override
            public void onError(int error) {
                switch (error) {
                    case JsoapError.NETWORK_ERROR:
                        Log.v("JSoapExample", "Network error");
                        break;
                    case JsoapError.PARSE_ERROR:
                        Log.v("JSoapExample", "Parsing error");
                        break;
                    default:
                        Log.v("JSoapExample", "Unknown error");
                        break;
                }
            }

        });
    }

    private void setAdapter(Cidade[] array){
        ArrayAdapter<Cidade> itemsAdapter = new ArrayAdapter<Cidade>(SoapActionActivity.this, android.R.layout.simple_list_item_1, array);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list.setAdapter(itemsAdapter);
            }
        });

    }

    private void getClienteFile() {
        String url = "http://10.0.2.2:2193/Integracao.asmx";
        String namespace = "http://tempuri.org/";
        String method = "GetClientesFile";
        String soap_action = "http://tempuri.org/GetClientesFile";

        SOAPManager.get(namespace, url, method, soap_action, new Parametros("01/01/1000", "A SIAC"), String.class, new JSoapCallback() {

            @Override
            public void onSuccess(Object result) {
                String res = (String) result;
                System.out.println("There!");
            }

            @Override
            public void onError(int error) {
                switch (error) {
                    case JsoapError.NETWORK_ERROR:
                        Log.v("JSoapExample", "Network error");
                        break;
                    case JsoapError.PARSE_ERROR:
                        Log.v("JSoapExample", "Parsing error");
                        break;
                    default:
                        Log.v("JSoapExample", "Unknown error");
                        break;
                }
            }

        });
    }
}