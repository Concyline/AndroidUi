package siac.com.androidui.ui;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;



import java.util.ArrayList;

import siac.com.androidui.R;
import siac.com.permision.PermissionHandler;
import siac.com.permision.Permissions;

public class Premisao {

    public void validaPermisoes(Context context, final String[] permisao, final onOk ok) {
        String[] permissions = permisao;

        Permissions.check(context, permissions, null, null, new PermissionHandler() {
            @Override
            public void onGranted() {
                 ok.ok(true);
            }

            @Override
            public void onDenied(final Context context, ArrayList<String> deniedPermissions) {
                new AlertDialog.Builder(context)
                        .setTitle("Atenção")
                        .setMessage("O aplicativo não tem as permições necessárias para prosseguir!")
                        .setPositiveButton("Pegar as permições?", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                validaPermisoes(context, permisao, ok);

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ok.ok(false);
                            }
                        })
                        .setIcon(R.drawable.ic_error_outline_black_24dp
                        )
                        .show();
            }
        });
    }

    public interface onOk {
        void ok(boolean flag);
    }
}
