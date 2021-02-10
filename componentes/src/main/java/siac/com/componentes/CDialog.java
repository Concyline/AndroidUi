package siac.com.componentes;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import siac.com.componentes.extras.AnimateDialog;
import siac.com.componentes.extras.PositionDialog;
import siac.com.componentes.extras.SizeDialog;
import siac.com.componentes.extras.SizeText;
import siac.com.componentes.extras.TypeDialog;
import siac.com.componentes.extras.WindowFormat;

/**
 * Created by multimeet on 18/01/21.
 */

public class CDialog {

    private Dialog dialog;
    private Context context;
    private TextView messageTextView;
    private ImageView imageView;
    private ConstraintLayout constraintLayout;

    private int size = 0;
    private int background = 0;
    private int duration = 3000; // DEFAULT

    public CDialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
    }

    public CDialog createAlertSneckBar(String message, TypeDialog alertType, SizeDialog sizeDialog) {

        size = prepareSizeBackBround(sizeDialog);

        prepareWindowSneckBar(message);

        imageView = dialog.findViewById(R.id.icn);
        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_dialog));

        prepareBackGroundColorImage(alertType);

        return this;
    }

    public CDialog createAlert(String message, WindowFormat windowFormat, TypeDialog typeDialog, SizeDialog givenSize) {

        prepareBackGroundType(windowFormat);

        size = prepareSizeBackBround(givenSize);

        prepareWindow(message);

        imageView = dialog.findViewById(R.id.icn);
        imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_dialog));

        prepareBackGroundColorImage(typeDialog);

        return this;
    }

    public CDialog createAlert(String message, WindowFormat windowFormat, Bitmap icon, TypeDialog alertType, SizeDialog givenSize) {

        prepareBackGroundType(windowFormat);

        size = prepareSizeBackBround(givenSize);

        prepareWindow(message);

        imageView = dialog.findViewById(R.id.icn);
        imageView.setImageBitmap(icon);

        prepareBackGroundColor(alertType);

        return this;
    }

    public CDialog createAlert(String message, WindowFormat windowFormat, Drawable icon, TypeDialog alertType, SizeDialog givenSize) {

        prepareBackGroundType(windowFormat);

        size = prepareSizeBackBround(givenSize);

        prepareWindow(message);

        imageView = dialog.findViewById(R.id.icn);
        imageView.setImageDrawable(icon);

        prepareBackGroundColor(alertType);

        return this;
    }

    private void prepareWindowSneckBar(String message) {
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.view_custon_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.scale_from_left_to_right;
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.round_back);

        dialog.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, size);
        messageTextView = dialog.findViewById(R.id.msg);

        messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.large_text)); // TEXTSIZE DEFAULT
        messageTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf"));
        messageTextView.setText(message);

        dialog.getWindow().getAttributes().windowAnimations = R.style.slide_from_bottom_to_bottom;
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;

        constraintLayout = dialog.findViewById(R.id.rl);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void prepareWindow(String message) {
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.view_custon_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.scale_from_left_to_right;

        dialog.getWindow().setLayout(size, size);
        messageTextView = dialog.findViewById(R.id.msg);

        messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.large_text)); // TEXTSIZE DEFAULT
        messageTextView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf"));
        messageTextView.setText(message);

        constraintLayout = dialog.findViewById(R.id.rl);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void prepareBackGroundType(WindowFormat windowFormat) {
        switch (windowFormat) {
            case BACKGROUND_OVAL:
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.round_back);
                break;
            case BACKGROUND_RECTANGLE:
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.rectangle_back);
                break;
            default:
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.round_back);
        }
    }

    private int prepareSizeBackBround(SizeDialog sizeDialog) {

        int size = 0;

        switch (sizeDialog) {
            case SMALL:
                size = context.getResources().getInteger(R.integer.small_dialog);
                break;
            case MEDIUM:
                size = context.getResources().getInteger(R.integer.medium_dialog);
                break;

            case LARGE:
                size = context.getResources().getInteger(R.integer.large_dialog);
                break;

            case XLARGE:
                size = context.getResources().getInteger(R.integer.big_dialog);
                break;
        }

        return size;
    }

    private void prepareBackGroundColor(TypeDialog alertType) {
        switch (alertType) {
            case SUCCESS:
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSuccess));
                break;

            case WARNING:
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWarning));
                break;

            case ERROR:
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorError));
                break;

            case INFO:
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorInfo));
                break;
        }
    }

    private void prepareBackGroundColorImage(TypeDialog typeDialog) {
        switch (typeDialog) {
            case SUCCESS:
                imageView.setImageResource(R.drawable.checked_1);
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorSuccess));
                break;

            case WARNING:
                imageView.setImageResource(R.drawable.warning);
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWarning));
                break;

            case ERROR:
                imageView.setImageResource(R.drawable.cancel1);
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorError));
                break;

            case INFO:
                imageView.setImageResource(R.drawable.info);
                constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.colorInfo));
                break;
        }
    }

    public CDialog setDuration(int inMilleseconds) {
        this.duration = inMilleseconds;
        return this;
    }

    public CDialog setBackgroundColor(int color) {
        if (constraintLayout != null) {
            constraintLayout.setBackgroundColor(context.getResources().getColor(color));
        }
        return this;
    }

    public CDialog setTextSize(SizeText sizeText) {
        messageTextView = dialog.findViewById(R.id.msg);
        switch (sizeText) {
            case SMALL:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.small_text));
                break;

            case MEDIUM:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.medium_text));
                break;

            case LARGE:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.large_text));
                break;

            case XLARGE:
                messageTextView.setTextSize((float) context.getResources().getInteger(R.integer.extra_large_text));
                break;
        }
        return this;
    }

    public CDialog setPosition(PositionDialog positionDialog) {
        switch (positionDialog) {
            case POSITION_BOTTOM:
                dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
                break;
            case POSITION_TOP:
                dialog.getWindow().getAttributes().gravity = Gravity.TOP;
                break;
            case POSITION_CENTER:
                dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
                break;
        }

        return this;
    }

    public CDialog setBackDimness(float lessThanOne) {
        dialog.getWindow().setDimAmount(lessThanOne);
        return this;
    }

    public CDialog setAnimation(AnimateDialog animateDialog) {
        int selectedAnimation = 0;
        switch (animateDialog) {

            // Scale

            case SCALE_FROM_BOTTOM_TO_BOTTOM:
                selectedAnimation = R.style.scale_from_bottom_to_bottom;
                break;

            case SCALE_FROM_BOTTOM_TO_TOP:
                selectedAnimation = R.style.scale_from_bottom_to_top;
                break;

            case SCALE_FROM_TOP_TO_BOTTOM:
                selectedAnimation = R.style.scale_from_top_to_bottom;
                break;

            case SCALE_FROM_TOP_TO_TOP:
                selectedAnimation = R.style.scale_from_top_to_top;
                break;

            case SCALE_TO_BOTTOM_FROM_BOTTOM:
                selectedAnimation = R.style.scale_to_bottom_from_bottom;
                break;

            case SCALE_TO_TOP_FROM_BOTTOM:
                selectedAnimation = R.style.scale_to_top_from_bottom;
                break;

            case SCALE_TO_TOP_FROM_TOP:
                selectedAnimation = R.style.scale_to_top_from_top;
                break;

            case SCALE_FROM_LEFT_TO_LEFT:
                selectedAnimation = R.style.scale_from_left_to_left;
                break;
            case SCALE_FROM_LEFT_TO_RIGHT:
                selectedAnimation = R.style.scale_from_left_to_right;
                break;

            case SCALE_FROM_RIGHT_TO_LEFT:
                selectedAnimation = R.style.scale_from_right_to_left;
                break;

            case SCALE_FROM_RIGHT_TO_RIGHT:
                selectedAnimation = R.style.scale_from_right_to_right;
                break;

            case SCALE_TO_LEFT_FROM_LEFT:
                selectedAnimation = R.style.scale_to_left_from_left;
                break;

            case SCALE_TO_RIGHT_FROM_LEFT:
                selectedAnimation = R.style.scale_to_right_from_left;
                break;

            case SCALE_TO_RIGHT_FROM_RIGHT:
                selectedAnimation = R.style.scale_to_right_from_right;
                break;

            // Slide

            case SLIDE_FROM_BOTTOM_TO_BOTTOM:
                selectedAnimation = R.style.slide_from_bottom_to_bottom;
                break;
            case SLIDE_FROM_BOTTOM_TO_TOP:
                selectedAnimation = R.style.slide_from_bottom_to_top;
                break;

            case SLIDE_FROM_TOP_TO_BOTTOM:
                selectedAnimation = R.style.slide_from_top_to_bottom;
                break;

            case SLIDE_FROM_TOP_TO_TOP:
                selectedAnimation = R.style.slide_from_top_to_top;
                break;

            case SLIDE_TO_BOTTOM_FROM_BOTTOM:
                selectedAnimation = R.style.slide_to_bottom_from_bottom;
                break;

            case SLIDE_TO_TOP_FROM_BOTTOM:
                selectedAnimation = R.style.slide_to_top_from_bottom;
                break;

            case SLIDE_TO_TOP_FROM_TOP:
                selectedAnimation = R.style.slide_to_top_from_top;
                break;


            case SLIDE_FROM_LEFT_TO_LEFT:
                selectedAnimation = R.style.slide_from_left_to_left;
                break;
            case SLIDE_FROM_LEFT_TO_RIGHT:
                selectedAnimation = R.style.slide_from_left_to_right;
                break;

            case SLIDE_FROM_RIGHT_TO_LEFT:
                selectedAnimation = R.style.slide_from_right_to_left;
                break;

            case SLIDE_FROM_RIGHT_TO_RIGHT:
                selectedAnimation = R.style.slide_from_right_to_right;
                break;

            case SLIDE_TO_LEFT_FROM_LEFT:
                selectedAnimation = R.style.slide_to_left_from_left;
                break;

            case SLIDE_TO_RIGHT_FROM_LEFT:
                selectedAnimation = R.style.slide_to_right_from_left;
                break;

            case SLIDE_TO_RIGHT_FROM_RIGHT:
                selectedAnimation = R.style.slide_to_right_from_right;
                break;


        }
        dialog.getWindow().getAttributes().windowAnimations = selectedAnimation;

        return this;
    }

    public void show() {
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, duration);
    }

    public void show(final CDialogListener onDismissListener) {
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                onDismissListener.onDismiss();
            }
        }, duration);
    }

    public interface CDialogListener{
        void onDismiss();
    }
}
