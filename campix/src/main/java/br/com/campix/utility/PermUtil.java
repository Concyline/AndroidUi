package br.com.campix.utility;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class PermUtil {

    public static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 9921;

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static boolean addPermission(List<String> permissionsList, String permission, Activity ac) {
        if (ac.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            return ac.shouldShowRequestPermissionRationale(permission);
        }
        return true;
    }


    public static void checkForCamaraWritePermissions(final FragmentActivity activity, WorkFinish workFinish) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            workFinish.onWorkFinish(true);

        } else {
            List<String> permissionsNeeded = new ArrayList<String>();
            final List<String> permissionsList = new ArrayList<String>();

            if (!addPermission(permissionsList, Manifest.permission.CAMERA, activity)) {
                permissionsNeeded.add("CAMERA");
            }

            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE, activity)) {
                permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
            }

            if (permissionsList.size() > 0) {
                activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            } else {
                workFinish.onWorkFinish(true);
            }
        }
    }


    public static void checkForCamaraWritePermissions(final Fragment fragment, WorkFinish workFinish) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            workFinish.onWorkFinish(true);

        } else {
            List<String> permissionsNeeded = new ArrayList<String>();
            final List<String> permissionsList = new ArrayList<String>();

            if (!addPermission(permissionsList, Manifest.permission.CAMERA, fragment.getActivity())) {
                permissionsNeeded.add("CAMERA");
            }

            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE, fragment.getActivity())) {
                permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
            }

            if (permissionsList.size() > 0) {
                fragment.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            } else {
                workFinish.onWorkFinish(true);
            }
        }
    }

}
