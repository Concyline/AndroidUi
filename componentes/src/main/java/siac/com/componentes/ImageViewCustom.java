package siac.com.componentes;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;


public class ImageViewCustom extends androidx.appcompat.widget.AppCompatImageView implements View.OnLongClickListener {

    private OnLongClickListener mOnLongClickListener;

    public ImageViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnLongClickListener(this);
    }

    public ImageViewCustom(Context context) {
        super(context);
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        if (l == this) {
            super.setOnLongClickListener(l);
            return;
        }

        mOnLongClickListener = l;
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnLongClickListener != null) {
            if (!mOnLongClickListener.onLongClick(v)) {
                popupDisplay(v);
                return true;
            }
        } else {
            popupDisplay(v);
            return true;
        }

        return false;
    }

    PopupWindow popupWindow;

    public void popupDisplay(final View destino) {

        popupWindow = new PopupWindow(this);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.popup_layout_action_bar_ui, null);
        ((TextView) view.findViewById(R.id.textView)).setText(this.getTag().toString());

        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.setContentView(view);

        popupWindow.showAsDropDown(destino, 0, 70);

        fechaPopUp();

    }

    private void fechaPopUp() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, 2000);
    }
}
