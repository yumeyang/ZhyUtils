package com.zhy.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtil {

    public static void deleteDir(File dir) {
        if (dir == null)
            return;

        if (!dir.exists()) {
            return;
        }

        if (!dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                deleteDir(file);
            }
        }
    }

    public static File getPicturesFile(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return context.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
        } else {
            return context.getFilesDir();
        }
    }
}
