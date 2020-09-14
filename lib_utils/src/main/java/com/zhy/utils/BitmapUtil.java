package com.zhy.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class BitmapUtil {

    public static Bitmap getViewBitmap(View view) {
        Bitmap screenshot;
        screenshot = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(screenshot);
        view.draw(c);
        return screenshot;
    }

    public static boolean isOverLapping(float x1, float y1, int width1, int height1, float x2, float y2, int width2, int height2) {
        if (x1 <= x2 && x1 + width1 <= x2) {
            return false;
        } else if (x2 <= x1 && x2 + width2 <= x1) {
            return false;
        } else if (y1 <= y2 && y1 + height1 <= y2) {
            return false;
        } else if (y2 <= y1 && y2 + height2 <= y1) {
            return false;
        }
        return true;
    }

    public static Bitmap scaleMatrix(Bitmap bitmap, int adjust_width, int adjust_height) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scale_w = 1;
        if (width > adjust_width) {
            scale_w = (float) adjust_width / (float) width;
        }

        float scale_h = 1;
        if (height > adjust_height) {
            scale_h = (float) adjust_height / (float) height;
        }

        float scale = Math.min(scale_w, scale_h);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap adjustBitmap(String path, int adjust_width, int adjust_height) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opt);

        int pic_width = opt.outWidth;
        int pic_height = opt.outHeight;

        boolean to_scale = false;

        int scale_w = 1;
        if (pic_width > adjust_width) {
            scale_w = pic_width / adjust_width;
            to_scale = true;
        }

        int scale_h = 1;
        if (pic_height > adjust_height) {
            scale_h = pic_height / adjust_height;
            to_scale = true;
        }

        opt.inSampleSize = Math.max(scale_w, scale_h);
        opt.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, opt);
        if (to_scale) {
            return scaleMatrix(bitmap, adjust_width, adjust_height);
        } else {
            return bitmap;
        }
    }

    public static Bitmap getCornerBitmap(Bitmap source, float corner) {
        if (corner <= 0) {
            corner = 10;
        }
        try {
            Bitmap bitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

            Paint paint = new Paint();
            paint.setAntiAlias(true);

            Rect rect = new Rect(0, 0, source.getWidth(), source.getHeight());
            RectF rectF = new RectF(rect);

            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawRoundRect(rectF, corner, corner, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            canvas.drawBitmap(source, rect, rect, paint);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
