package siac.com.androidui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

import siac.com.leitor.LeitorActivity;
import siac.com.texto.ManipulaTexto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button componentesButton = findViewById(R.id.componentesButton);
        componentesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ComponentesActivity.class);
                startActivity(intent);
            }
        });

        Button textoButton = findViewById(R.id.textoButton);
        textoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManipulaTextoActivity.class);
                startActivity(intent);
            }
        });

        Button leitorButton = findViewById(R.id.leitorButton);
        leitorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LerQrBarCodeActivity.class);
                startActivity(intent);
            }
        });

        //validaPermisoes();

    }

    private void validaPermisoes() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        Permissions.check(MainActivity.this, permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {

                try {

                    Button componentesButton = findViewById(R.id.componentesButton);
                    componentesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ComponentesActivity.class);
                            startActivity(intent);
                        }
                    });

                    Button textoButton = findViewById(R.id.textoButton);
                    textoButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ManipulaTextoActivity.class);
                            startActivity(intent);
                        }
                    });

                    Button leitorButton = findViewById(R.id.leitorButton);
                    leitorButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), LerQrBarCodeActivity.class);
                            startActivity(intent);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Atenção")
                        .setMessage("O aplicativo não tem as permições necessárias para prosseguir!")
                        .setPositiveButton("Pegar as permições?", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                validaPermisoes();

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setIcon(R.drawable.ic_error_outline
                        )
                        .show();
            }
        });
    }

}
