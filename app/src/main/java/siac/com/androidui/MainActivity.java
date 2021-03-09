package siac.com.androidui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.util.ArrayList;

import br.com.utill.Util;
import siac.com.androidui.ui.CamPixActivity;
import siac.com.androidui.ui.ComponentesActivity;
import siac.com.androidui.ui.ComponentesDoisActivity;
import siac.com.androidui.ui.ComponentesTresActivity;
import siac.com.androidui.ui.HawkActivity;
import siac.com.androidui.ui.LerQrBarCodeActivity;
import siac.com.androidui.ui.ManipulaTextoActivity;
import siac.com.androidui.ui.Premisao;
import siac.com.androidui.ui.SwipLayoutActivity;
import siac.com.androidui.ui.UtilActivity;
import siac.com.androidui.ui.ZoomFrameImageViewActivity;
import siac.com.permision.PermissionHandler;
import siac.com.permision.Permissions;
import siac.com.shortcut.IReceiveStringExtra;
import siac.com.shortcut.Shortcut;
import siac.com.shortcut.ShortcutUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validaPermisoes();
        addShortCut();

        Util.setBar(this, "Teste", "Subtitle");

    }

    private void addShortCut(){
       ShortcutUtils shortcutUtils = new ShortcutUtils(this);

       Shortcut dynamicShortcut = new Shortcut.ShortcutBuilder()
                .setShortcutIcon(R.drawable.round_device_hub_white_48dp)
                .setShortcutId("dynamicShortcutId")
                .setShortcutLongLabel("ALL Devices")
                .setShortcutShortLabel("ALL Devices")
                .setIntentAction("dynamicShortcutIntentAction")
                .setIntentStringExtraKey("dynamicShortcutKey")
                .setIntentStringExtraValue("all")
                .build();


        shortcutUtils.addDynamicShortCut(dynamicShortcut, new IReceiveStringExtra() {
            @Override
            public void onReceiveStringExtra(String stringExtraKey, String stringExtraValue) {
                String intent = getIntent().getStringExtra(stringExtraKey);
                if (intent != null) {
                    if (intent.equals("all")) {
                        System.out.println("OKOKOKOKOKOKO");
                    }
                }
            }
        });
    }

    private void validaPermisoes() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        Permissions.check(MainActivity.this, permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {

                try {
                    findViewById(R.id.componentesButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ComponentesActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.componentesDoisButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ComponentesDoisActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.textoButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ManipulaTextoActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.leitorButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), LerQrBarCodeActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.permision).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Premisao premisao = new Premisao();

                            premisao.validaPermisoes(getApplicationContext(), new String[]{ Manifest.permission.CAMERA}, new Premisao.onOk() {
                                @Override
                                public void ok(boolean flag) {
                                    if(flag){
                                        System.out.println("aqui");
                                    }
                                }
                            });

                        }
                    });

                    findViewById(R.id.abaixaTeclado).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), UtilActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.campix).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), CamPixActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.zoomFrameButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ZoomFrameImageViewActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.halkButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), HawkActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.SwipeLayoutButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), SwipLayoutActivity.class);
                            startActivity(intent);
                        }
                    });

                    findViewById(R.id.componentesTresButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), ComponentesTresActivity.class);
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
