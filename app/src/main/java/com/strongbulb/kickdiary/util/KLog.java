package com.strongbulb.kickdiary.util;

import android.util.Log;

import com.strongbulb.kickdiary.ApplicationClass;

/**
 * Created by 강전구 on 2016-03-23.
 */
public class KLog {

    static final String TAG = "mylog";

    /** Log Level Error **/
    public static final void e(String message) {
        if (ApplicationClass.DEBUG) Log.e(TAG, buildLogMsg(message));
    }
    /** Log Level Warning **/
    public static final void w(String message) {
        if (ApplicationClass.DEBUG) Log.w(TAG, buildLogMsg(message));
    }
    /** Log Level Information **/
    public static final void i(String message) {
        if (ApplicationClass.DEBUG) Log.i(TAG, buildLogMsg(message));
    }
    /** Log Level Debug **/
    public static final void d(String message) {
        if (ApplicationClass.DEBUG) Log.d(TAG, buildLogMsg(message));
    }
    /** Log Level Verbose **/
    public static final void v(String message) {
        if (ApplicationClass.DEBUG) Log.v(TAG, buildLogMsg(message));
    }

    public static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);
        return sb.toString();
    }

}
