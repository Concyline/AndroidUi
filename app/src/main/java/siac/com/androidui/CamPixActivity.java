package siac.com.androidui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.campix.Options;
import br.com.campix.Pix;
import br.com.campix.utility.PermUtil;

public class CamPixActivity extends AppCompatActivity {

    private Options options;
    private int requestCodePicker = 100;
    private ImageView imageView;
    private RequestManager glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_pix);


        imageView = findViewById(R.id.imageView);

        options = Options.init()
                .setRequestCode(requestCodePicker)
                .setFrontfacing(false)
                .setPath("pix/photo");
        //.setFileName("teste");

        Pix.start(this, options);

    }

    private String newFileName() {
        return "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmSS", Locale.ENGLISH).format(new Date()) + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCodePicker) {
            if (resultCode == Activity.RESULT_OK) {

                String path = data.getStringExtra(Pix.IMAGE_PATH);
                File file = (File) data.getExtras().get(Pix.IMAGE_FILE);

                glide = Glide.with(CamPixActivity.this);
                glide.load(path).into(imageView);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS) {
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Pix.start(this, options);
            } else {
                Toast.makeText(this, "Approve permissions to open Pix ImagePicker", Toast.LENGTH_LONG).show();
            }
        }
    }
}