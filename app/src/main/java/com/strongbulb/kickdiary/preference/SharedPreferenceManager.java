package com.strongbulb.kickdiary.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.strongbulb.kickdiary.Constants;

/**
 * Created by Kangjeongu on 2017. 4. 16..
 */

public class SharedPreferenceManager {

    private final static String TAG = SharedPreferenceManager.class.getSimpleName();

    private static SharedPreferenceManager mInstance = null;

    private SharedPreferences mPreferences = null;
    private SharedPreferences.Editor mEditor = null;

    private Context mContext;

    public SharedPreferenceManager() {
    }

    public static SharedPreferenceManager getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreferenceManager.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreferenceManager();
                }
            }
        }

        return mInstance;
    }


    public void init(Context ctx) {

        mContext = ctx;
        if (mPreferences == null) {
            mPreferences = ctx.getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        }

        mEditor = mPreferences.edit();

    }

    public boolean isInit() {
        return mPreferences != null;
    }

    public void tempSaveDiary(String title, String content, String date) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Constants.PrefVal.TITLE, title);
        editor.putString(Constants.PrefVal.CONTENT, content);
        editor.putString(Constants.PrefVal.DATE, date);
        editor.commit();
    }

    public void tempDeleteDiary() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Constants.PrefVal.TITLE, "");
        editor.putString(Constants.PrefVal.CONTENT, "");
        editor.putString(Constants.PrefVal.DATE, "");
        editor.commit();
    }

    public String getTitle(){
        return mPreferences.getString(Constants.PrefVal.TITLE, "");
    }
    public String getContent(){
        return mPreferences.getString(Constants.PrefVal.CONTENT, "");
    }
    public String getDate(){
        return mPreferences.getString(Constants.PrefVal.DATE, "");
    }

    public void setGetOffWorkTime(String getOffWorkTime) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Constants.PrefVal.GETOFFWORKTIME, getOffWorkTime);
        editor.commit();
    }

    public String getGetOffWorkTime(){
        return mPreferences.getString(Constants.PrefVal.GETOFFWORKTIME, "");
    }

}
