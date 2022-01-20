package siac.com.androidui.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

import siac.com.androidui.R;
import siac.com.componentes.CDialog;
import siac.com.componentes.CustomDialog;
import siac.com.componentes.HelpButton;
import siac.com.componentes.KeyBoardDialog;
import siac.com.componentes.ProgressButton;
import siac.com.componentes.ProgressIndeterminate;
import siac.com.componentes.ProgressImageView;
import siac.com.componentes.extras.AnimateDialog;
import siac.com.componentes.extras.PositionDialog;
import siac.com.componentes.extras.SizeDialog;
import siac.com.componentes.extras.SizeText;
import siac.com.componentes.extras.TypeDialog;
import siac.com.componentes.extras.WindowFormat;

public class ComponentesDoisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes_dois);

        final ProgressImageView progressImageView = findViewById(R.id.progressImageView);
        progressImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressImageView.setProgres();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressImageView.removeProgres();
                    }
                }, 2000);
            }
        });

        final ProgressImageView lupaProgressImageView = findViewById(R.id.lupaProgressImageView);
        lupaProgressImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lupaProgressImageView.setProgres();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lupaProgressImageView.removeProgres();
                    }
                }, 2000);
            }
        });

        final ProgressButton progressButton = findViewById(R.id.progressButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressButton.setProgres();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressButton.removeProgres();
                    }
                }, 2000);
            }
        });

        final ProgressButton progressButtonOk = findViewById(R.id.progressButtonOk);
        progressButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressButtonOk.setProgres();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressButtonOk.removeProgres();
                    }
                }, 2000);
            }
        });



        /* Custon Dialog */


        findViewById(R.id.ovalButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CDialog(ComponentesDoisActivity.this)
                        .createAlert("success !",
                                WindowFormat.BACKGROUND_OVAL,
                                TypeDialog.SUCCESS,
                                SizeDialog.XLARGE)
                        .setAnimation(AnimateDialog.SCALE_FROM_LEFT_TO_RIGHT)
                        .setDuration(3000)  // in milliseconds
                        .setTextSize(SizeText.SMALL)
                        .setBackgroundColor(R.color.pink)
                        .show();

            }
        });

        findViewById(R.id.bitimapButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CDialog(ComponentesDoisActivity.this)
                        .createAlert("WARNING! custon mensagem",
                                WindowFormat.BACKGROUND_RECTANGLE,
                                getBitmapFromAsset(),
                                TypeDialog.WARNING,
                                SizeDialog.XLARGE)
                        .setAnimation(AnimateDialog.SCALE_FROM_BOTTOM_TO_TOP)
                        .setDuration(3000)  // in milliseconds
                        .setTextSize(SizeText.XLARGE)
                        .setPosition(PositionDialog.POSITION_CENTER)
                        .setBackDimness(0.9f) // less Than One
                        .setBackgroundColor(R.color.pink)
                        .show();

            }
        });

        findViewById(R.id.drawableButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CDialog(ComponentesDoisActivity.this).createAlert("success !",
                        WindowFormat.BACKGROUND_OVAL,
                        getDrawable(),
                        TypeDialog.SUCCESS,
                        SizeDialog.XLARGE)
                        .setAnimation(AnimateDialog.SCALE_FROM_BOTTOM_TO_TOP)
                        .setDuration(3000)   // in milliseconds
                        .setTextSize(SizeText.LARGE)
                        .show();

            }
        });

        findViewById(R.id.errorButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CDialog(ComponentesDoisActivity.this).createAlert("Error message !",
                        WindowFormat.BACKGROUND_OVAL,
                        TypeDialog.ERROR,
                        SizeDialog.XLARGE)
                        .setAnimation(AnimateDialog.SCALE_FROM_LEFT_TO_RIGHT)
                        .setDuration(3000)   // in milliseconds
                        .setTextSize(SizeText.LARGE)
                        .show();

            }
        });

        findViewById(R.id.snackBarButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CDialog(ComponentesDoisActivity.this)
                        .createAlertSneckBar("Info SnackBar Button",
                                TypeDialog.WARNING,
                                SizeDialog.SMALL)
                        .setDuration(10000)
                        .show();

            }
        });

        findViewById(R.id.listenerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CDialog(ComponentesDoisActivity.this)
                        .createAlertSneckBar("Info SnackBar",
                                TypeDialog.INFO,
                                SizeDialog.MEDIUM)
                        .show(new CDialog.CDialogListener() {
                            @Override
                            public void onDismiss() {
                                System.out.println("aqui");
                            }
                        });
            }
        });


        findViewById(R.id.progressDialogButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressIndeterminate progressDialog = new ProgressIndeterminate(ComponentesDoisActivity.this).
                        create("Atenção!")
                        .multColor(true)
                        //.setTextSize(SizeText.MEDIUM)
                        .cancelable(false);

                progressDialog.show();
                progressDialog.dismiss();
                progressDialog.isShowing();
                progressDialog.setMessage("Text");
                progressDialog.setBackgroundColor(R.color.pink);


                //progressDialog.dismiss();
                //progressDialog.isShowing();
                //progressDialog.setMessage("Text");
                //progressDialog.setBackgroundColor(R.color.pink);

                //ProgressIndeterminate pi = ProgressIndeterminate.show(ComponentesDoisActivity.this, "OK");

            }
        });

        HelpButton helpButton = findViewById(R.id.helpButton);
        helpButton.setActivity(this);
        helpButton.setHelpMsg("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley");

        final EditText editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    KeyBoardDialog customDialog = new KeyBoardDialog(ComponentesDoisActivity.this);
                    customDialog.setBackgroundResource(KeyBoardDialog.DWindow.ROUND)
                            .setCancelable(true)
                            .setJustNumber(false)
                            .setBackgroundColor(R.color.background)
                            .create();

                    customDialog.show(editTextNumber.getText().toString(), new KeyBoardDialog.OnDismissListener() {
                        @Override
                        public void dismiss(String value) {
                            editTextNumber.setText(value);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public Bitmap getBitmapFromAsset() {
        InputStream imageStream = getResources().openRawResource(R.raw.lamp);
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        return bitmap;
    }

    public Drawable getDrawable() {
        Drawable myDrawable = getResources().getDrawable(R.drawable.checked_1);
        return myDrawable;
    }

    public void vibrate(View button) {
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        button.startAnimation(in);
    }
}
