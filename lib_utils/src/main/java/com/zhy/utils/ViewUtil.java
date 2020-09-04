package com.zhy.utils;

import android.view.View;

public class ViewUtil {

    public static boolean isOverLapping(View a, View b) {

        float x1 = a.getX();
        float y1 = a.getY();
        int w1 = a.getWidth();
        int h1 = a.getHeight();

        float x2 = b.getX();
        float y2 = b.getY();
        int w2 = b.getWidth();
        int h2 = b.getHeight();

        if (x1 <= x2 && x1 + w1 <= x2) {
            return false;
        } else if (x2 <= x1 && x2 + w2 <= x1) {
            return false;
        } else if (y1 <= y2 && y1 + h1 <= y2) {
            return false;
        } else if (y2 <= y1 && y2 + h2 <= y1) {
            return false;
        } else {
            return true;
        }
    }
}
