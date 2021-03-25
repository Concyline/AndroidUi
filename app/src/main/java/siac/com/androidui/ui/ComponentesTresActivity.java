package siac.com.androidui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import siac.com.androidui.MainActivity;
import siac.com.androidui.R;
import siac.com.componentes.CustomDialog;

public class ComponentesTresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes_tres);

        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    CustomDialog customDialog = new CustomDialog(ComponentesTresActivity.this);
                    customDialog.setContentView(R.layout.cadastro)
                            .setToolbarTitle("Log IN")
                            .setToolbarSubTitle("Enter the system")
                            .setToolbarColor(R.color.colorAccent)
                            .setMenuToolbar(R.menu.menu_bar)
                            .setBackgroundResource(CustomDialog.DWindow.ROUND)
                            .setHeight(CustomDialog.DLayoutParams.WRAP_CONTENT)
                            .setCancelable(true)
                            .create();

                    customDialog.menu(R.id.miCompose).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            System.out.println("MENU");
                            return false;
                        }
                    });

                    Dialog dialog = customDialog.dialog();

                    Button button = dialog.findViewById(R.id.button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("BUTTON");
                        }
                    });


                    customDialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}