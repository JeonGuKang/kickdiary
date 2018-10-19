package com.strongbulb.kickdiary.util;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import java.util.Date;

/**
 * Created by JeonGuKang on 2017-03-30.
 */

public class Utils {

    public Utils() {
    }

    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, colorId));
        }
    }

    public static String getDate(Date inputdate) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm");
        return format.format(inputdate);
    }

    public static String getYearAndMonthAndDayOfDate(Date inputdate) {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return format.format(inputdate);
    }

    public static void testClassToString(Object df) {
        Gson gson = new Gson();
        String json = gson.toJson(df);
        KLog.i(" json =  " + json);
    }

    public static String monthOfDate(String textDate) {
        if(textDate == null || textDate.length() < 1) return "";
        return textDate.substring(5,7);
    }

    public static String dayOfDate(String textDate) {
        if(textDate == null || textDate.length() < 1) return "";
        return textDate.substring(8,10);
    }

}
