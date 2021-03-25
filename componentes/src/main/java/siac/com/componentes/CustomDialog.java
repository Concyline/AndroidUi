package siac.com.componentes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class CustomDialog {

    private android.app.Dialog dialog;
    private androidx.appcompat.widget.Toolbar toolbar;
    private String toolbarTitle = "Dialog";
    private String toolbarSubTitle = "";
    private Activity activity;
    private LinearLayout container;
    private boolean cancelable = true;
    private int layoutResId = -1;
    private int menuToolbarResId = -1;
    private int backgroundResource = R.drawable.bg_dialog_round_ui;
    private int HEIGTH_PERCENTAGE = -2;

    public CustomDialog(Activity activity) {
        this.activity = activity;
        dialog = new android.app.Dialog(activity, R.style.CustomDialogTheme);
    }

    public CustomDialog setContentView(int layoutResId) {
        this.layoutResId = layoutResId;
        return this;
    }

    public CustomDialog setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public CustomDialog setMenuToolbar(int menuToolbarResId){
        this.menuToolbarResId = menuToolbarResId;
        return this;
    }

    public CustomDialog setToolbarTitle(String toolbarTitle){
        this.toolbarTitle = toolbarTitle;
        return this;
    }

    public CustomDialog setToolbarSubTitle(String toolbarSubTitle){
        this.toolbarSubTitle = toolbarSubTitle;
        return this;
    }

    public CustomDialog setBackgroundResource(DWindow dWindow) {
        backgroundResource = dWindow.equals(DWindow.ROUND) ? R.drawable.bg_dialog_round_ui : R.drawable.bg_dialog_square_ui;
        return this;
    }

    public CustomDialog setHeight(DLayoutParams dLayoutParams) {
        switch (dLayoutParams) {
            case WRAP_CONTENT: {
                HEIGTH_PERCENTAGE = -2;
                break;
            }
            case MATCH_PARENT: {
                HEIGTH_PERCENTAGE = 90;
                break;
            }
        }
        return this;
    }

    @SuppressLint("ResourceAsColor")
    public CustomDialog create() throws Exception{

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.base_dialog_ui);
        dialog.setCancelable(cancelable);
        dialog.getWindow().getAttributes().windowAnimations = R.style.scale_fade_in_out;

        toolbar = dialog.findViewById(R.id.toolbar);
        container = dialog.findViewById(R.id.container);

        toolbar.setTitle(toolbarTitle);
        if(!toolbarSubTitle.equals("")) {
            toolbar.setSubtitle(toolbarSubTitle);
        }
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setSubtitleTextColor(android.graphics.Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.outline_arrow_back_white_48dp);

        toolbar.setNavigationOnClickListener(new android.view.View.OnClickListener() {
            @Override public void onClick(android.view.View view) {
                if(cancelable) {
                    dismiss();
                }
            }
        });

        if(menuToolbarResId > 0) {
            toolbar.inflateMenu(menuToolbarResId);
        }

        addLayoutContainer();

        /////////////////////////////////////////////////////////////////////
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (width < height) {
            //dialog.getWindow().setLayout((width * 90 / 100), HEIGTH_PERCENTAGE == -2 ? WindowManager.LayoutParams.WRAP_CONTENT : (height * HEIGTH_PERCENTAGE / 100));
            dialog.getWindow().setLayout((width * 90 / 100), HEIGTH_PERCENTAGE == -2 ? WindowManager.LayoutParams.WRAP_CONTENT : (height * HEIGTH_PERCENTAGE / 100));
        } else {
            dialog.getWindow().setLayout((width * 60 / 100), HEIGTH_PERCENTAGE == -2 ? WindowManager.LayoutParams.WRAP_CONTENT : (height * HEIGTH_PERCENTAGE / 100));
        }

        dialog.getWindow().getDecorView().setBackgroundResource(backgroundResource);

        return this;
    }

    private void addLayoutContainer() throws Exception{
        if(layoutResId < 0){
            throw new Exception("Main layout not found");
        }

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        android.view.View view = inflater.inflate(layoutResId, null);
        container.addView(view);
    }

    public MenuItem menu(int id) throws Exception{
        MenuItem menuItem = null;
        if(toolbar != null){
           menuItem =  toolbar.getMenu().findItem(id);
        }

        if(menuItem == null){
            throw new Exception("Menu item not found!");
        }
        return menuItem;
    }

    public androidx.appcompat.widget.Toolbar toolbar(){
        return toolbar;
    }

    public android.app.Dialog dialog(){
        return dialog;
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public enum DWindow {
        SQUARE,
        ROUND
    }

    public enum DLayoutParams {
        WRAP_CONTENT,
        MATCH_PARENT
    }

}
