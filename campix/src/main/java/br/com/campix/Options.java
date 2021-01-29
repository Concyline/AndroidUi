package br.com.campix;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Options implements Serializable {

    private int requestCode = -1;
    private String path = "Pix/Camera";
    private String fileName;
    private int height = 0, width = 0;
    private boolean frontfacing = false;
    public static final int SCREEN_ORIENTATION_UNSET = -2;
    public static final int SCREEN_ORIENTATION_UNSPECIFIED = -1;
    public static final int SCREEN_ORIENTATION_LANDSCAPE = 0;
    public static final int SCREEN_ORIENTATION_PORTRAIT = 1;
    public static final int SCREEN_ORIENTATION_USER = 2;
    public static final int SCREEN_ORIENTATION_BEHIND = 3;
    public static final int SCREEN_ORIENTATION_SENSOR = 4;
    public static final int SCREEN_ORIENTATION_NOSENSOR = 5;
    public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
    public static final int SCREEN_ORIENTATION_SENSOR_PORTRAIT = 7;
    public static final int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = 8;
    public static final int SCREEN_ORIENTATION_REVERSE_PORTRAIT = 9;
    public static final int SCREEN_ORIENTATION_FULL_SENSOR = 10;
    public static final int SCREEN_ORIENTATION_USER_LANDSCAPE = 11;
    public static final int SCREEN_ORIENTATION_USER_PORTRAIT = 12;
    public static final int SCREEN_ORIENTATION_FULL_USER = 13;
    public static final int SCREEN_ORIENTATION_LOCKED = 14;

    private Options() {
    }


    public Options setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileName(){
        return fileName;
    }

    public static Options init() {
        return new Options();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isFrontfacing() {
        return this.frontfacing;
    }

    public Options setFrontfacing(boolean frontfacing) {
        this.frontfacing = frontfacing;
        return this;
    }

    private void check() {
        if (this == null) {
            throw new NullPointerException("call init() method to initialise Options class");
        }
    }

    public int getRequestCode() {
        if (this.requestCode < 0) {
            throw new NullPointerException("requestCode in Options class is null");
        }
        return requestCode;
    }

    public Options setRequestCode(int requestcode) {
        check();
        this.requestCode = requestcode;
        return this;
    }

    public String getPath() {
        return this.path;
    }

    public Options setPath(String path) {
        check();
        this.path = path;
        return this;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScreenOrientation {
    }

}

