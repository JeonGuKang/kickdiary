package com.strongbulb.kickdiary;

import android.content.Context;

/**
 * Created by JeonGuKang on 2017-01-06.
 */

public class Constants {

    /////Request Activity
    public final static String PREF_FILE_NAME                       = "KICKDIARY";
    static final int REALM_VERSION = 1;

    public static final class DB_Column{
        public static final String NO           = "no";
        public static final String TITLE        = "title";
        public static final String DESC         = "desc";
        public static final String CONTENT      = "content";
        public static final String LOCATION     = "location";
        public static final String IMG1         = "img1";
        public static final String IMG2         = "img2";
        public static final String IMG3         = "img3";
        public static final String IMG4         = "img4";
        public static final String IMG5         = "img5";
        public static final String PARENTNO     = "parentno";
        public static final String LAT          = "lat";
        public static final String LANG         = "lang";
        public static final String DATETIME     = "time";
        public static final String TYPE         = "type";
    }

    ///DataBase
    public static final String DATABASE_NAME = "kickdiary.db";
    public static final String SAVE_LIST_TABLE = "savelist";
    public static final String SAVE_LIST_LOG_TABLE = "savelistlog";
    public static final int DATABASE_VERSION = 1;

    public static final int    DB_MODE = Context.MODE_PRIVATE;

    public static final class StatusBarStyle {
        public static final int TRANSPARENT = 1;
        public static final int WHITE = 2;
    }

    public static final class Extras {
        public static final String DIARYDATA = "DIARYDATA";
        public static final String NO = "NO";
        public static final String TITLE = "TITLE";
        public static final String CONTENT = "CONTENT";
        public static final String DATE = "DATE";
    }

    public static final class ResultActivityCode{
        public static final int EDITDIARYACTIVITY = 100;
        public static final int DIARYLISTACTIVITY = 200;
    }

    public static final class PrefVal{
        public final static String TITLE                        = "TITLE";
        public final static String CONTENT                      = "CONTENT";
        public final static String DATE                         = "DATE";
        public final static String GETOFFWORKTIME               = "GETOFFWORKTIME";
    }

    public static final class DiaryType {
        public final static int DEFULT = 1;
    }

    public static final class GOOGLE_DRIVE {
        public final static String FOLDER_NAME = "KickDiary";
    }

}
