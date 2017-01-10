package com.bq.android.wheels.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by liuben on 16-12-21.
 */

public class FileManager {

    private static final String TAG = "FileManager";

    private static volatile FileManager mFileManager = null;

    private FileManager() {

    }

    public static FileManager getInstance() {
        if (mFileManager == null) {
            synchronized (FileManager.class) {
                if (mFileManager == null) {
                    mFileManager = new FileManager();
                }
            }
        }
        return mFileManager;
    }

    public static void internalStorageWrite(Context context, String fileName, InputStream inputStream) {
        getInstance()._internalWrite(context, fileName, inputStream);
    }

    public static void internalStorageWrite(Context context, String fileName, String content) {
        getInstance()._internalWrite(context, fileName, content);
    }

    public static String internalStorageRead(Context context, String fileName) {
        return getInstance()._internalRead(context, fileName);
    }

    public static void externalStorageWrite(Context context, String fileName, InputStream inputStream) {
        getInstance()._externalWrite(context, fileName, inputStream);
    }

    public static void externalStorageWrite(Context context, String fileName, String content) {
        getInstance()._externalWrite(context, fileName, content);
    }

    public static void externalStorageWrite(String fileDirection, String fileName, InputStream inputStream) {
        getInstance()._externalWrite(fileDirection, fileName, inputStream);
    }

    public static void externalStorageWrite(String fileDirection, String fileName, String content) {
        getInstance()._externalWrite(fileDirection, fileName, content);
    }

    public static String externalStorageRead(Context context, String fileName) {
        return getInstance()._externalRead(context, fileName);
    }

    public static String externalStorageRead(String fileDirection, String fileName) {
        return getInstance()._externalRead(fileDirection, fileName);
    }

    private void _internalWrite(Context context, String fileName, InputStream inputStream) {
        int length = 0;
        byte[] temp = new byte[2048];
        FileOutputStream fileOutputStream = null;

        try {
//            File file = new File(context.getFilesDir(), fileName);
//            fileOutputStream = new FileOutputStream(file);
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            while ((length = inputStream.read(temp)) > 0) {
                fileOutputStream.write(inputStream.read(temp, 0, length));
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeInputStream(inputStream);
            closeOutputStream(fileOutputStream);
        }
    }

    private void _internalWrite(Context context, String fileName, String content) {
        FileOutputStream fileOutputStream = null;
        try {
//            File file = new File(context.getFilesDir(), fileName);
//            fileOutputStream = new FileOutputStream(file);
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeOutputStream(fileOutputStream);
        }
    }

    private String _internalRead(Context context, String fileName) {
        StringBuffer buffer = new StringBuffer();
        String result = null;
        int length = 0;
        byte[] temp = new byte[2048];
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = context.openFileInput(fileName);
            while ((length = fileInputStream.read(temp)) != -1) {
                buffer.append(new String(temp, 0, length));
            }
            result = buffer.toString();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeInputStream(fileInputStream);
        }

        return result;
    }

    private void _externalWrite(Context context, String fileName, InputStream inputStream) {
        if (!isExternalStorageWritable()) {
            Log.e(TAG, "external storage is not writable");
            return;
        }
        FileOutputStream fileOutputStream = null;
        int length = 0;
        byte[] temp = new byte[2048];

        try {
            File file = new File(context.getExternalFilesDir(null).getAbsolutePath(), fileName);
            fileOutputStream = new FileOutputStream(file);
            while ((length = inputStream.read()) != -1) {
                fileOutputStream.write(inputStream.read(temp, 0, length));
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeInputStream(inputStream);
            closeOutputStream(fileOutputStream);
        }
    }

    private void _externalWrite(Context context, String fileName, String content) {
        if (!isExternalStorageWritable()) {
            Log.e(TAG, "external storage is not writable");
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(context.getExternalFilesDir(null).getAbsolutePath(), fileName);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeOutputStream(fileOutputStream);
        }
    }

    private void _externalWrite(String fileDirection, String fileName, InputStream inputStream) {
        if (!isExternalStorageWritable()) {
            Log.e(TAG, "external storage is not writable");
            return;
        }
        FileOutputStream fileOutputStream = null;
        int length = 0;
        byte[] temp = new byte[2048];

        try {
            File file = new File(fileDirection, fileName);
            fileOutputStream = new FileOutputStream(file);
            while ((length = inputStream.read(temp)) != -1) {
                fileOutputStream.write(inputStream.read(temp, 0, length));
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeInputStream(inputStream);
            closeOutputStream(fileOutputStream);
        }

    }

    private void _externalWrite(String fileDirection, String fileName, String content) {
        if (!isExternalStorageWritable()) {
            Log.e(TAG, "external storage is not writable");
            return;
        }
        FileOutputStream fileOutputStream = null;

        try {
            File file = new File(fileDirection, fileName);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeOutputStream(fileOutputStream);
        }
    }

    private String _externalRead(Context context, String fileName) {
        if (!isExternalStorageReadable()) {
            Log.e(TAG, "external storage is not readable");
            return null;
        }
        FileInputStream fileInputStream = null;
        int length = 0;
        byte[] temp = new byte[2048];
        StringBuffer stringBuffer = new StringBuffer();
        String result = null;

        try {
            File file = new File(context.getExternalFilesDir(null).getAbsolutePath(), fileName);
            fileInputStream = new FileInputStream(file);
            while ((length = fileInputStream.read(temp)) != -1) {
                stringBuffer.append(new String(temp, 0, length));
            }
            result = stringBuffer.toString();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeInputStream(fileInputStream);
        }

        return result;
    }

    private String _externalRead(String fileDirection, String fileName) {
        if (!isExternalStorageReadable()) {
            Log.e(TAG, "external storage is not readable");
            return null;
        }
        FileInputStream fileInputStream = null;
        int length = 0;
        byte[] temp = new byte[2048];

        StringBuffer stringBuffer = new StringBuffer();
        String result = null;

        try {
            File file = new File(fileDirection, fileName);
            fileInputStream = new FileInputStream(file);
            while ((length = fileInputStream.read(temp)) != -1) {
                stringBuffer.append(new String(temp, 0, length));
            }
            result = stringBuffer.toString();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeInputStream(fileInputStream);
        }

        return result;
    }

    private void closeInputStream(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeOutputStream(OutputStream out) {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1
            );
        }
    }
}