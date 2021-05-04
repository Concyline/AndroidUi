package br.com.error.uce;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import br.com.error.R;
import br.com.error.uce.permision.PermUtil;
import br.com.error.uce.permision.WorkFinish;

public class DefaultActivity extends AppCompatActivity {

    private File txtFile;
    private String strCurrentErrorLog;
    private DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");

        findViewById(R.id.button_close_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnCaughtException.closeApplication(DefaultActivity.this);
            }
        });

        findViewById(R.id.button_copy_error_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyErrorToClipboard();
            }
        });

        findViewById(R.id.button_share_error_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareErrorLog();
            }
        });

        findViewById(R.id.button_save_error_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveErrorLogToFile();
            }
        });

        findViewById(R.id.button_share_email_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareEmailLog();
            }
        });

        findViewById(R.id.button_view_error_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(DefaultActivity.this)
                        .setTitle("Erros encontrados")
                        .setMessage(getAllErrorDetailsFromIntent(DefaultActivity.this, getIntent()))
                        .setPositiveButton("Copiar Log & Fechar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        copyErrorToClipboard();
                                        dialog.dismiss();
                                    }
                                })
                        .setNeutralButton("Fechar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                        .show();
                TextView textView = dialog.findViewById(android.R.id.message);
                if (textView != null) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                }
            }
        });
    }

    public String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    private String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private String getActivityLogFromIntent(Intent intent) {
        return intent.getStringExtra(UnCaughtException.EXTRA_ACTIVITY_LOG);
    }

    private String getStackTraceFromIntent(Intent intent) {
        return intent.getStringExtra(UnCaughtException.EXTRA_STACK_TRACE);
    }

    private void saveErrorLogToFile() {
        PermUtil.checkForCamaraWritePermissions(this, new WorkFinish() {
            @Override
            public void onWorkFinish(Boolean check) {

                Boolean isSDPresent = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
                if (isSDPresent) {
                    Date currentDate = new Date();

                    String errorLogFileName = "Error_" + dateFormat.format(currentDate);
                    String errorLog = getAllErrorDetailsFromIntent(DefaultActivity.this, getIntent());
                    String fullPath = Environment.getExternalStorageDirectory() + "/UnCaughtException/";
                    FileOutputStream outputStream;
                    try {
                        File file = new File(fullPath);
                        file.mkdir();

                        txtFile = new File(fullPath + errorLogFileName + ".txt");

                        if(file.exists()) {
                            if (!txtFile.exists()) {
                                txtFile.createNewFile();
                            }
                        }

                        outputStream = new FileOutputStream(txtFile);
                        outputStream.write(errorLog.getBytes());
                        outputStream.close();
                        if (txtFile.exists()) {
                            Toast.makeText(DefaultActivity.this, "File Saved Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        Toast.makeText(DefaultActivity.this, "Storage Permission Not Found", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    private void shareEmailLog(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("message/rfc822");

        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String string = preferences.getString("mails", "[]");

        String[] array = gson.fromJson(string, String[].class);

        intent.putExtra(Intent.EXTRA_EMAIL  , array);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Errors found");

        String errorInformation = getAllErrorDetailsFromIntent(DefaultActivity.this, getIntent());
        intent.putExtra(Intent.EXTRA_TEXT, errorInformation );

        startActivity(Intent.createChooser(intent, "Email via..."));
    }

    private void shareErrorLog() {
        String errorLog = getAllErrorDetailsFromIntent(DefaultActivity.this, getIntent());
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, "Application Crash Error Log");
        share.putExtra(Intent.EXTRA_TEXT, errorLog);
        startActivity(Intent.createChooser(share, "Share Error Log"));
    }

    private void copyErrorToClipboard() {
        String errorInformation = getAllErrorDetailsFromIntent(DefaultActivity.this, getIntent());
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText("View Error Log", errorInformation);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(DefaultActivity.this, "Error Log Copied", Toast.LENGTH_SHORT).show();
        }
    }

    private String getAllErrorDetailsFromIntent(Context context, Intent intent) {

        if (TextUtils.isEmpty(strCurrentErrorLog)) {

            String LINE_SEPARATOR = "\n";
            StringBuilder errorReport = new StringBuilder();
            errorReport.append("***** UnCaughtException");
            errorReport.append("\n***** AndroidUi\n");

            errorReport.append("\n***** DEVICE INFO \n");
            errorReport.append("Locale: ").append(Locale.getDefault()).append(LINE_SEPARATOR);
            errorReport.append("Brand: ").append(Build.BRAND).append(LINE_SEPARATOR);
            errorReport.append("Board: ").append(Build.BOARD).append(LINE_SEPARATOR);
            errorReport.append("Device: ").append(Build.DEVICE).append(LINE_SEPARATOR);
            errorReport.append("Model: ").append(Build.MODEL).append(LINE_SEPARATOR);
            errorReport.append("Manufacturer: ").append(Build.MANUFACTURER).append(LINE_SEPARATOR);
            errorReport.append("Product: ").append(Build.PRODUCT).append(LINE_SEPARATOR);
            errorReport.append("SDK: ").append(Build.VERSION.SDK).append(LINE_SEPARATOR);
            errorReport.append("Android Version: ").append(Build.VERSION.RELEASE).append(LINE_SEPARATOR);
            errorReport.append("Host: ").append(Build.HOST).append(LINE_SEPARATOR);
            errorReport.append("ID: ").append(Build.ID).append(LINE_SEPARATOR);
            errorReport.append("Type: ").append(Build.TYPE).append(LINE_SEPARATOR);
            StatFs stat = getStatFs();
            errorReport.append("Total Internal memory: ").append(getTotalInternalMemorySize(stat)).append(LINE_SEPARATOR);
            errorReport.append("Available Internal memory: ").append(getAvailableInternalMemorySize(stat)).append(LINE_SEPARATOR);

            errorReport.append("\n***** APP INFO \n");
            try {
                PackageManager pm = context.getPackageManager();
                PackageInfo pi;
                pi = pm.getPackageInfo(context.getPackageName(), 0);
                errorReport.append("Version: ").append(pi.versionName).append('\n');
                errorReport.append("Package: ").append(pi.packageName).append('\n');
            } catch (Exception e) {
                Log.e("CustomExceptionHandler", "Error", e);
                errorReport.append("Could not get Version information for ").append(context.getPackageName());
            }

            Date currentDate = new Date();
            String firstInstallTime = getFirstInstallTimeAsString(context, dateFormat);

            if (!TextUtils.isEmpty(firstInstallTime)) {
                errorReport.append("Installed On: ").append(firstInstallTime).append(LINE_SEPARATOR);
            }
            String lastUpdateTime = getLastUpdateTimeAsString(context, dateFormat);
            if (!TextUtils.isEmpty(lastUpdateTime)) {
                errorReport.append("Updated On: ").append(lastUpdateTime).append(LINE_SEPARATOR);
            }
            errorReport.append("Current Date: ").append(dateFormat.format(currentDate)).append(LINE_SEPARATOR);

            errorReport.append("\n***** ERROR LOG \n");
            errorReport.append(getStackTraceFromIntent(intent));
            errorReport.append(LINE_SEPARATOR);

            String activityLog = getActivityLogFromIntent(intent);
            errorReport.append(LINE_SEPARATOR);
            if (activityLog != null) {
                errorReport.append("\n***** USER ACTIVITIES \n");
                errorReport.append("User Activities: ").append(activityLog).append(LINE_SEPARATOR);
            }
            errorReport.append("\n***** END OF LOG *****\n");
            strCurrentErrorLog = errorReport.toString();
            return strCurrentErrorLog;

        } else {
            return strCurrentErrorLog;
        }
    }

    private StatFs getStatFs() {
        File path = Environment.getDataDirectory();
        return new StatFs(path.getPath());
    }

    private long getAvailableInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    private long getTotalInternalMemorySize(StatFs stat) {
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    private String getFirstInstallTimeAsString(Context context, DateFormat dateFormat) {
        long firstInstallTime;
        try {
            firstInstallTime = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            return dateFormat.format(new Date(firstInstallTime));
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    private String getLastUpdateTimeAsString(Context context, DateFormat dateFormat) {
        long lastUpdateTime;
        try {
            lastUpdateTime = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
            return dateFormat.format(new Date(lastUpdateTime));
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}