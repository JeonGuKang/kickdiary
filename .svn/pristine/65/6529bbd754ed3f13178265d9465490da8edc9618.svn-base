package com.strongbulb.kickdiary;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.strongbulb.kickdiary.preference.SharedPreferenceManager;

/**
 * Created by JeonGuKang on 2017-01-02.
 */

public class ApplicationClass extends Application {

    public static  boolean DEBUG;
    private static volatile ApplicationClass mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = ApplicationClass.this;
        DEBUG = isDebuggable(this);
        SharedPreferenceManager.getInstance().init(getApplicationContext());

    }




    public static void showToast(String str) {
        Toast.makeText(ApplicationClass.getGlobalApplicationContext().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    public static ApplicationClass getGlobalApplicationContext() {
        if(mInstance == null)
            throw new IllegalStateException("");
        return mInstance;
    }

    //현재 릴리즈인지 디버그 모드인지 확인
    private boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
        /* debuggable variable will remain false */
        }

        return debuggable;
    }
}
