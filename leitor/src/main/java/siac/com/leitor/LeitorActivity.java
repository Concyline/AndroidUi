package siac.com.leitor;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;
import com.orhanobut.hawk.Hawk;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import siac.com.leitor.util.Util;

public class LeitorActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private LinearLayout container;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor);

        try {
            Hawk.init(getBaseContext()).build();

            setBar("Leitor");

            mScannerView = new ZXingScannerView(this);

            // LIGA O PADRAO
            mScannerView.setFlash(Hawk.get("flash", false));

            container = (LinearLayout) findViewById(R.id.containerLinearLayout);
            container.addView(mScannerView);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void setBar(String title) {
        setBar(title, "");
    }

    public void setBar(String title, String subtitle) {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setTitle(Html.fromHtml("<small>" + title + "</small>"));
        if (subtitle != null) {
            getSupportActionBar().setSubtitle(subtitle);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_leitor, menu);
        this.menu = menu;

        /////////// ARRUMAR ESSA LOGICA
        if(Hawk.get("flash", false)){
            menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_off_white_48dp);
        }else{
            menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_on_white_48dp);
        }

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return itemSelected(item, 1, 0);
    }

    public boolean itemSelected(MenuItem item, int tipoMenu, int position) {
        switch (item.getItemId()) {
            case android.R.id.home:
                 //FLUXO NORMAL
                 finish();

                // FLUXO TESTE
                //String qrCode = "C=7898958119652;L=50962;V=30/09/2019";
                /*String qrCode = "C=00000751;L=51021;V=31/12/2019;F=01/01/2019";
                String codigoBarras = "00000751";

                Intent intent = new Intent();
                intent.putExtra("CODIGO", qrCode );
                //intent.putExtra("CODIGO", codigoBarras );
                setResult(0, intent);
                finish();*/
                //mScannerView.resumeCameraPreview(this);
                break;
            case 2131165305:

                if(!Hawk.get("flash", false)){
                    Hawk.put("flash", true);
                    mScannerView.setFlash(true);
                    menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_off_white_48dp);
                }else{
                    Hawk.put("flash", false);
                    mScannerView.setFlash(false);
                    menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_on_white_48dp);
                }
                break;
        }
        return true;
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent intent = new Intent();
        intent.putExtra("CODIGO", rawResult.getText() );
        setResult(0, intent);
        finish();
        mScannerView.resumeCameraPreview(this);
    }
}
