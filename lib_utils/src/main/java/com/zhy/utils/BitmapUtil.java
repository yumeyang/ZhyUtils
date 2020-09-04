package com.zhy.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

public class BitmapUtil {

    public static Bitmap getViewBitmap(View view) {
        Bitmap shot = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(shot);
        view.draw(c);
        return shot;
    }

    public static Bitmap scaleMatrix(Bitmap bitmap, float scaleW, float scaleH) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleW, scaleH);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }
}
