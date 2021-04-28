package siac.com.texto.storagesd;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class StorageSDBuilder {

    private static Context context;
    private static SdInterface sdInterface;
    private static Log log;

    private static String folderName = "ManipulaTexto";
    private static String fileName = "Log.txt";

    public StorageSDBuilder(Context context){
        this.context = context.getApplicationContext();
    }

    public StorageSDBuilder setFolderAndFileName(String folderName, String fileName){
        this.folderName = folderName;
        this.fileName = fileName;
        return this;
    }

    public String getFolderName(){
        return folderName;
    }

    public String getFileName(){
        return fileName;
    }

    public StorageSDBuilder setLog(Log log){
        this.log = log;
        return this;
    }

    public Log getLog(){
        if(log == null){
            log = new Log() {
                @Override
                public void onLog(String message) {
                    System.out.println("StorageSD LOGS >>> "+message);
                }
            };
        }
        return log;
    }

    public SdInterface getSdInterface(){
        if(sdInterface == null){
            sdInterface = new Sd(log, folderName, fileName);
        }
        return sdInterface;
    }

    public void build(){
        StorageSD.build(this);
        log.onLog("Build suecessfull");
        testaPermisao();
    }

    public static void testaPermisao() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            log.onLog("A aplicação precisa da permissão para manipular o SD Card no AndroidManifest.xml\n\n <uses-permission android:name=\"android.permission.READ_EXTERNAL_STORAGE\" />\n <uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\" />");
        }
    }
}
