package br.com.campix;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.FileCallback;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.controls.Audio;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Mode;
import com.otaliastudios.cameraview.size.AspectRatio;
import com.otaliastudios.cameraview.size.SizeSelector;
import com.otaliastudios.cameraview.size.SizeSelectors;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.campix.utility.PermUtil;
import br.com.campix.utility.Utility;
import br.com.campix.utility.WorkFinish;

public class Pix extends AppCompatActivity {

    private CameraView camera;
    private Options options = null;
    private int status_bar_height = 0;
    private int colorPrimaryDark;
    private String selectionImage;

    private static final String OPTIONS = "options";
    public static String IMAGE_RESULTS = "image_results";

    private ImageView clickme;
    private FrameLayout flash;
    private ImageView front;
    private int flashDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utility.setupStatusBarHidden(this);
        Utility.hideStatusBar(this);

        setContentView(R.layout.activity_main_lib);

        try {
            options = (Options) getIntent().getSerializableExtra(OPTIONS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("Options is null");
        }

        instanciarViews();
        setLisneters();

        initialize();
    }

    @Override 
    protected void onRestart() {
        super.onRestart();
        camera.open();
        camera.setMode(Mode.PICTURE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.open();
        camera.setMode(Mode.PICTURE);
    }

    @Override
    protected void onPause() {
        camera.close();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.destroy();
    }

    private void instanciarViews(){
        camera = findViewById(R.id.camera_view);
        clickme = findViewById(R.id.clickme);
        flash = findViewById(R.id.flash);
        front = findViewById(R.id.front);
    }

    private void setLisneters(){

        clickme.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    findViewById(R.id.clickmebg).setVisibility(View.GONE);
                    findViewById(R.id.clickmebg).animate().scaleX(1f).scaleY(1f).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    clickme.animate().scaleX(1f).scaleY(1f).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    findViewById(R.id.clickmebg).setVisibility(View.VISIBLE);
                    findViewById(R.id.clickmebg).animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                    clickme.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                }
                return false;
            }
        });

        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ObjectAnimator oj = ObjectAnimator.ofFloat(camera, "alpha", 1f, 0f, 0f, 1f);
                oj.setStartDelay(200l);
                oj.setDuration(600l);
                oj.start();
                camera.takePicture();
            }
        });

        final ImageView iv = (ImageView) flash.getChildAt(0);
        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int height = flash.getHeight();
                iv.animate()
                        .translationY(height)
                        .setDuration(100)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                                iv.setTranslationY(-(height / 2));
                                if (flashDrawable == R.drawable.ic_flash_auto_black_24dp) {
                                    flashDrawable = R.drawable.ic_flash_off_black_24dp;
                                    iv.setImageResource(flashDrawable);
                                    camera.setFlash(Flash.OFF);

                                } else if (flashDrawable == R.drawable.ic_flash_off_black_24dp) {
                                    flashDrawable = R.drawable.ic_flash_on_black_24dp;
                                    iv.setImageResource(flashDrawable);
                                    camera.setFlash(Flash.ON);

                                } else {
                                    flashDrawable = R.drawable.ic_flash_auto_black_24dp;
                                    iv.setImageResource(flashDrawable);
                                    camera.setFlash(Flash.AUTO);
                                }
                                iv.animate().translationY(0).setDuration(50).setListener(null).start();
                            }
                        })
                        .start();
            }
        });

        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(front, "scaleX", 1f, 0f).setDuration(150);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(front, "scaleX", 0f, 1f).setDuration(150);

                oa1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        front.setImageResource(R.drawable.ic_photo_camera);
                        oa2.start();
                    }
                });
                oa1.start();
                if (options.isFrontfacing()) {
                    options.setFrontfacing(false);
                    camera.setFacing(Facing.BACK);
                } else {
                    camera.setFacing(Facing.FRONT);
                    options.setFrontfacing(true);
                }
            }
        });
    }

    public static void start(final Fragment context, final Options options) {
        PermUtil.checkForCamaraWritePermissions(context, new WorkFinish() {
            @Override
            public void onWorkFinish(Boolean check) {
                Intent i = new Intent(context.getActivity(), Pix.class);
                i.putExtra(OPTIONS, options);
                context.startActivityForResult(i, options.getRequestCode());
            }
        });
    }

    public static void start(final FragmentActivity context, final Options options) {

        if(options == null){
            throw new NullPointerException("Options is null");
        }

        PermUtil.checkForCamaraWritePermissions(context, new WorkFinish() {
            @Override
            public void onWorkFinish(Boolean check) {
                Intent i = new Intent(context, Pix.class);
                i.putExtra(OPTIONS, options);
                context.startActivityForResult(i, options.getRequestCode());
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void initialize(){

        WindowManager.LayoutParams params = getWindow().getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        Utility.getScreenSize(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        status_bar_height = Utility.getStatusBarSizePort(Pix.this);
        setRequestedOrientation(Options.SCREEN_ORIENTATION_PORTRAIT);
        colorPrimaryDark = ResourcesCompat.getColor(getResources(), R.color.colorPrimaryPix, getTheme());

        camera.setMode(Mode.PICTURE);
        camera.setAudio(Audio.OFF);

        SizeSelector width = SizeSelectors.minWidth(Utility.WIDTH);
        SizeSelector height = SizeSelectors.minHeight(Utility.HEIGHT);
        SizeSelector dimensions = SizeSelectors.and(width, height); // Matches sizes bigger than width X height
        SizeSelector ratio = SizeSelectors.aspectRatio(AspectRatio.of(1, 2), 0); // Matches 1:2 sizes.
        SizeSelector ratio3 = SizeSelectors.aspectRatio(AspectRatio.of(2, 3), 0); // Matches 2:3 sizes.
        SizeSelector ratio2 = SizeSelectors.aspectRatio(AspectRatio.of(9, 16), 0); // Matches 9:16 sizes.
        SizeSelector result = SizeSelectors.or(
                SizeSelectors.and(ratio, dimensions),
                SizeSelectors.and(ratio2, dimensions),
                SizeSelectors.and(ratio3, dimensions)
        );
        camera.setPictureSize(result);
        camera.setLifecycleOwner(Pix.this);

        if (options.isFrontfacing()) {
            camera.setFacing(Facing.FRONT);
        } else {
            camera.setFacing(Facing.BACK);
        }

        camera.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(@NonNull PictureResult result) {

                File dir = Environment.getExternalStoragePublicDirectory(options.getPath());
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String fileName = options.getFileName() == null ? "IMG_"
                        + new SimpleDateFormat("yyyyMMdd_HHmmSS", Locale.ENGLISH).format(new Date())
                        + ".jpg" : options.getFileName()+".jpg";

                File photo = new File(dir, fileName);

                /*File photo = new File(dir, "IMG_"
                        + new SimpleDateFormat("yyyyMMdd_HHmmSS", Locale.ENGLISH).format(new Date())
                        + ".jpg");*/

                result.toFile(photo, new FileCallback() {
                    @Override
                    public void onFileReady(@Nullable File photo) {
                        Utility.vibe(Pix.this, 50);
                        clickme.animate().scaleX(1.2f).scaleY(1.2f).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator()).start();

                        selectionImage = photo.getAbsolutePath();
                        Utility.scanPhoto(Pix.this, photo);
                        returnObject();
                    }
                });

            }
        });

        flashDrawable = R.drawable.ic_flash_off_black_24dp;
    }

    public void returnObject() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(IMAGE_RESULTS, selectionImage);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
