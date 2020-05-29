package siac.com.leitor;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;
import com.orhanobut.hawk.Hawk;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import siac.com.leitor.util.Util;

public class LeitorActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private LinearLayout container;
    private Menu menu;
    private String teste = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor);

        try {
            Hawk.init(getBaseContext()).build();
            setBar("Leitor");
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
        if(testaPermisao()) {
            mScannerView = new ZXingScannerView(this);
            teste = getIntent().getStringExtra("teste_componete_cardview") == null ? "" : getIntent().getStringExtra("teste_componete_cardview");

            // LIGA O PADRAO
            mScannerView.setFlash(Hawk.get("flash", false));

            container = (LinearLayout) findViewById(R.id.containerLinearLayout);
            container.addView(mScannerView);

            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    public boolean testaPermisao() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Log.e("ERRO","A aplicação precisa da permissão da câmera no AndroidManifest.xml\n\n <uses-permission android:name=\"android.permission.CAMERA\" />");
            return false;
        } else {
            return true;
        }
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
        if (Hawk.get("flash", false)) {
            menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_off_white_48dp);
        } else {
            menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_on_white_48dp);
        }

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return itemSelected(item, 1, 0);
    }

    public boolean itemSelected(MenuItem item, int tipoMenu, int position) {

        if (item.getItemId() == android.R.id.home) {
            if (!teste.equals("")) {
                Intent intent = new Intent();
                //String codigo = "00000751";
                intent.putExtra("CODIGO", teste);
                setResult(0, intent);
            }
            finish();
        }

        if (item.getItemId() == R.id.ligarFlash) {
            if (!Hawk.get("flash", false)) {
                Hawk.put("flash", true);
                mScannerView.setFlash(true);
                menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_off_white_48dp);
            } else {
                Hawk.put("flash", false);
                mScannerView.setFlash(false);
                menu.findItem(R.id.ligarFlash).setIcon(R.drawable.outline_flash_on_white_48dp);
            }
        }

        return true;
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent intent = new Intent();
        intent.putExtra("CODIGO", rawResult.getText());
        setResult(0, intent);
        finish();
        mScannerView.resumeCameraPreview(this);
    }
}
