package com.strongbulb.kickdiary;

import com.strongbulb.kickdiary.backup.Backup;
import com.strongbulb.kickdiary.backup.GoogleDriveBackup;
import com.strongbulb.kickdiary.preference.SharedPreferenceManager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by JeonGuKang on 2017-01-02.
 */

public class ApplicationClass extends MultiDexApplication {

    public static  boolean DEBUG;
    private static volatile ApplicationClass mInstance = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = ApplicationClass.this;
        DEBUG = isDebuggable(this);
        SharedPreferenceManager.getInstance().init(getApplicationContext());
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
            .name("kickdiary.realm")
            .schemaVersion(Constants.REALM_VERSION)
            .build();
        Realm.setDefaultConfiguration(config);
    }

    @NonNull
    public Backup getBackup() {
        return new GoogleDriveBackup();
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
