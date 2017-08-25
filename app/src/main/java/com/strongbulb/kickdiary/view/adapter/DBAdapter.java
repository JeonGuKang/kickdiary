package com.strongbulb.kickdiary.view.adapter;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.model.DiaryData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JeonGuKang on 2017-01-05.
 */

public class DBAdapter {

    protected static final String DATABASE_NAME = Constants.DATABASE_NAME;
    protected static final int DATABASE_VERSION = 1;

    public static final String[] SAVE_LIST_TABLE_COLUMNS = { Constants.DB_Column.NO, Constants.DB_Column.TITLE, Constants.DB_Column.CONTENT, Constants.DB_Column.TYPE, Constants.DB_Column.DATETIME };

    private Context mContext;
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase connection;

    private static DBAdapter instance;

    public void connect(Context context) {
        mContext = context;
        this.dbHelper = new DatabaseHelper(this.mContext.getApplicationContext());
        this.connection = this.dbHelper.getWritableDatabase();
    }

    private DBAdapter() {}

    public synchronized static DBAdapter getInstance() {
        if ( instance == null ) {
            instance = new DBAdapter();
        }
        return instance;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + Constants.SAVE_LIST_TABLE + " ("
                    + Constants.DB_Column.NO  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + Constants.DB_Column.TITLE + " VARCHAR(100) ,"
                    + Constants.DB_Column.CONTENT + " TEXT ,"
                    + Constants.DB_Column.TYPE + " INTEGER DEFAULT 1 ,"   ///1,일반
                    + Constants.DB_Column.DATETIME + " DATE DEFAULT (datetime('now','localtime')) "
                    +");");

//            db.execSQL("CREATE TABLE " + Constants.SAVE_LIST_LOG_TABLE + " ("
//                    + Constants.NO  + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    + Constants.PARENTNO + " INTEGER ,"
//                    + Constants.LAT + " VARCHAR(100),"
//                    + Constants.LANG + " VARCHAR(100),"
//                    + Constants.IMG1 + " VARCHAR(300),"
//                    + Constants.IMG2 + " VARCHAR(300),"
//                    + Constants.IMG3 + " VARCHAR(300),"
//                    + Constants.IMG4 + " VARCHAR(300),"
//                    + Constants.IMG5 + " VARCHAR(300),"
//                    + Constants.DATETIME + " DATE DEFAULT (datetime('now','localtime')) "
//                    +");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Constants.SAVE_LIST_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + Constants.SAVE_LIST_LOG_TABLE);
            onCreate(db);
        }
    }

    public void deleteAll() {
        connection.delete(Constants.SAVE_LIST_TABLE, null, null);
    }

    /**
     * DB접속을 종료합니다.
     * 하지만 이 메소드를 호출하게 되면 dbHelper.getWritableDatabase() 값이 null이 되어버리므로 주의.
     */
    public void close() {
        try {
            connection.close();
            dbHelper.close();
        } catch (Exception e) {}
    }

//    /**
//     * 북마크를 삭제합니다.
//     *
//     * @param no
//     * @return
//     */


//    /**
//     * 북마크를 삭제합니다.
//     *
//     * @param bookmark
//     */
//    public void deleteBookmark(BookmarkItem bookmark) {
//        String whereClause = Constants.NO+"=?";
//        String whereArgs[] = new String[] { String.valueOf(bookmark.getId()) };
//        connection.delete(TABLE_NAME, whereClause, whereArgs);
//    }

//    /**
//     * 지정한 유져의 북마크 리스트를 가져옵니다.
//     *
//     * @param name
//     * @return
//     */
//    public List<LocationItem> getBookmarks(String name) {
//        List<LocationItem> items = new ArrayList<LocationItem>();
//        Cursor cursor = connection.query(Constants.SAVE_LIST_TABLE, SAVE_LIST_TABLE_COLUMNS, Constants.NAME+"='"+name, null, null, null, null, null);
//        if ( cursor.getCount() == 0 ) {
//            return items;
//        }
//        cursor.moveToFirst();
//        int length = cursor.getCount();
//        for (int i=0; i<length; i++) {
//            items.add(new LocationItem(
//                    cursor.getString(cursor.getColumnIndex(Constants.NAME)),
//                    cursor.getString(cursor.getColumnIndex(Constants.LAT)),
//                    cursor.getString(cursor.getColumnIndex(Constants.LANG))
//            ));
//            if ( !cursor.moveToNext() ) {
//                break;
//            }
//        }
//        cursor.close();
//        return items;
//    }

//    /**
//     * 모든 위치 리스트를 가져옵니다.
//     *
//     * @return
//     */
    public ArrayList<DiaryData> getDiaryList() {
        ArrayList<DiaryData> items = new ArrayList<>();
        Cursor cursor = connection.query(Constants.SAVE_LIST_TABLE, SAVE_LIST_TABLE_COLUMNS,null, null, null, null, null, null);
        if ( cursor.getCount() == 0 ) {
            return items;
        }
        cursor.moveToFirst();
        int length = cursor.getCount();
        for (int i=0; i<length; i++) {
            items.add(new DiaryData(
                    cursor.getInt(cursor.getColumnIndex(Constants.DB_Column.NO)),
                    cursor.getString(cursor.getColumnIndex(Constants.DB_Column.TITLE)),
                    cursor.getString(cursor.getColumnIndex(Constants.DB_Column.CONTENT)),
                    cursor.getString(cursor.getColumnIndex(Constants.DB_Column.DATETIME)),
                    cursor.getInt(cursor.getColumnIndex(Constants.DB_Column.TYPE))
            ));
            if ( !cursor.moveToNext() ) {
                break;
            }
        }
        cursor.close();
        return items;
    }
//
//    /**
//     * 선택한 위치 리스트를 가져옵니다.
//     *
//     * @return
//     */
//    public List<LocationItemData> getSelectSaveLocationList(int no) {
//        List<LocationItemData> items = new ArrayList<LocationItemData>();
//        Cursor cursor = connection.query(Constants.SAVE_LIST_TABLE, SAVE_LIST_TABLE_COLUMNS, Constants.NO+"=? ", new String[]{String.valueOf(no)}, null, null, null, null);
//        if ( cursor.getCount() == 0 ) {
//            return items;
//        }
//        cursor.moveToFirst();
//        int length = cursor.getCount();
//        for (int i=0; i<length; i++) {
//            items.add(new LocationItemData(
//                    cursor.getString(cursor.getColumnIndex(Constants.LAT)),
//                    cursor.getString(cursor.getColumnIndex(Constants.LANG))
//            ));
//            items.get(i).setNo(cursor.getInt(cursor.getColumnIndex(Constants.NO)));
//            items.get(i).setTitle(cursor.getString(cursor.getColumnIndex(Constants.TITLE)));
//            items.get(i).setDesc(cursor.getString(cursor.getColumnIndex(Constants.DESC)));
//            items.get(i).setType(cursor.getInt(cursor.getColumnIndex(Constants.TYPE)));
//            items.get(i).setDate(cursor.getString(cursor.getColumnIndex(Constants.DATETIME)));
//            if ( !cursor.moveToNext() ) {
//                break;
//            }
//        }
//        cursor.close();
//        return items;
//    }
//
//
//
//    /**
//     * 선택한 위치 로그 리스트를 가져옵니다.
//     *
//     * @return
//     */
//    public List<LocationItemData> getSaveLocationLogList(int parentNo) {
//        List<LocationItemData> items = new ArrayList<LocationItemData>();
//        Cursor cursor = connection.query(Constants.SAVE_LIST_LOG_TABLE, SAVE_LIST_LOG_TABLE_COLUMNS, Constants.PARENTNO+"=? ", new String[]{String.valueOf(parentNo)}, null, null, null, null);
//        if ( cursor.getCount() == 0 ) {
//            return items;
//        }
//        cursor.moveToFirst();
//        int length = cursor.getCount();
//        for (int i=0; i<length; i++) {
//            items.add(new LocationItemData(
//                    cursor.getString(cursor.getColumnIndex(Constants.LAT)),
//                    cursor.getString(cursor.getColumnIndex(Constants.LANG))
//            ));
//            items.get(i).setNo(cursor.getInt(cursor.getColumnIndex(Constants.NO)));
//            items.get(i).setParentNo(cursor.getString(cursor.getColumnIndex(Constants.PARENTNO)));
//            items.get(i).setImg1(cursor.getString(cursor.getColumnIndex(Constants.IMG1)));
//            items.get(i).setImg1(cursor.getString(cursor.getColumnIndex(Constants.IMG2)));
//            items.get(i).setImg1(cursor.getString(cursor.getColumnIndex(Constants.IMG3)));
//            items.get(i).setImg1(cursor.getString(cursor.getColumnIndex(Constants.IMG4)));
//            items.get(i).setImg1(cursor.getString(cursor.getColumnIndex(Constants.IMG5)));
//            items.get(i).setDate(cursor.getString(cursor.getColumnIndex(Constants.DATETIME)));
//
//            if ( !cursor.moveToNext() ) {
//                break;
//            }
//        }
//        cursor.close();
//        return items;
//    }
//
//    public int getLastParentNo()
//    {
//        int lastNo = 0;
//        List<LocationItemData> items = new ArrayList<LocationItemData>();
//        Cursor cursor = connection.query(Constants.SAVE_LIST_TABLE, SAVE_LIST_TABLE_COLUMNS,null, null, null, null, "no desc", "1");
//        if ( cursor.getCount() == 0 ) {
//            return lastNo;
//        }
//        cursor.moveToFirst();
//        int length = cursor.getCount();
//        for (int i=0; i<length; i++) {
//            lastNo  = cursor.getInt(cursor.getColumnIndex(Constants.NO));
//            if ( !cursor.moveToNext() ) {
//                break;
//            }
//        }
//        return lastNo;
//    }
//
//    public void printAllSaveLocationList()
//    {
//        List<LocationItemData> items = new ArrayList<LocationItemData>();
//        items = getSaveLocationList();
//        int cnt = 0;
//        for(LocationItemData locationItemData : items)
//        {
//            KLog.i("location no("+cnt +" = " + locationItemData.getNo());
//            KLog.i("location title("+cnt +" = " + locationItemData.getTitle());
//            KLog.i("location desc("+cnt +" = " + locationItemData.getDesc());
//            KLog.i("location lat("+cnt +" = " + locationItemData.getLat());
//            KLog.i("location lang("+cnt +" = " + locationItemData.getLang());
//            KLog.i("location type("+cnt +" = " + locationItemData.getType());
//            KLog.i("location date("+cnt +" = " + locationItemData.getDate());
//            cnt ++;
//        }
//    }
//
//    public int getSaveLocationListType(int no)
//    {
//        Cursor cursor = connection.query(Constants.SAVE_LIST_TABLE, SAVE_LIST_TABLE_COLUMNS, Constants.NO+" =? ", new String[]{String.valueOf(no)}, null, null, null, null);
//        if ( cursor.getCount() == 0 ) {
//            return 0;
//        }
//        cursor.moveToFirst();
//        int type = cursor.getInt(cursor.getColumnIndex(Constants.TYPE));
//        KLog.i("getSaveLocationListType = " + type);
//        cursor.close();
//        return type;
//    }
//
//
//    public void printAllSaveLocationLogList()
//    {
//        //TODO 메소드를 하나 더 만들던지 해서 전부 프린트 하는거랑 선택적으로 하는거랑 나누어야 함
//        List<LocationItemData> items = new ArrayList<LocationItemData>();
//        items = getSaveLocationLogList(getLastParentNo());
//        int cnt = 0;
//        for(LocationItemData locationItemData : items)
//        {
//            KLog.i("location no ("+cnt +") = " + locationItemData.getNo());
//            KLog.i("location parent_no ("+cnt +") = " + locationItemData.getParentNo());
//            KLog.i("location lat ("+cnt +") = " + locationItemData.getLat());
//            KLog.i("location lang("+cnt +") = " + locationItemData.getLang());
//            KLog.i("location date("+cnt +") = " + locationItemData.getDate());
//            cnt ++;
//        }
//    }
//
//    /**
//     *
//     * @param locationitem
//     */
    public void saveDiary(DiaryData diaryData) {
        ContentValues values = new ContentValues();
            values.put(Constants.DB_Column.TITLE, diaryData.getTitle());
            values.put(Constants.DB_Column.CONTENT, diaryData.getContent());
            values.put(Constants.DB_Column.TYPE, diaryData.getType());
            values.put(Constants.DB_Column.DATETIME, diaryData.getDate());
        if(connection.isOpen()) {
            connection.insertOrThrow(Constants.SAVE_LIST_TABLE, null, values);
        } else {
            connect(getInstance().mContext);
            connection.insertOrThrow(Constants.SAVE_LIST_TABLE, null, values);
        }
    }
//
    public void updateDiary(DiaryData diaryData)
    {
        ContentValues values = new ContentValues();
        // UPDATE
        values.put(Constants.DB_Column.TITLE, diaryData.getTitle());
        values.put(Constants.DB_Column.CONTENT, diaryData.getContent());
        values.put(Constants.DB_Column.TYPE, diaryData.getType());
        values.put(Constants.DB_Column.DATETIME, diaryData.getDate());
        String whereClause = Constants.DB_Column.NO +"=?";
        String whereArgs[] = new String[] { String.valueOf(diaryData.getNo()) };
        if(connection.isOpen()) {
            connection.update(Constants.SAVE_LIST_TABLE, values, whereClause, whereArgs);
        } else {
            connect(getInstance().mContext);
            connection.update(Constants.SAVE_LIST_TABLE, values, whereClause, whereArgs);
        }

    }

    public boolean deleteDiaryItem(int no) {
        if(connection.isOpen()) {
            return connection.delete(Constants.SAVE_LIST_TABLE, Constants.DB_Column.NO + "=" + no, null) > 0;
        } else {
            connect(getInstance().mContext);
            return connection.delete(Constants.SAVE_LIST_TABLE, Constants.DB_Column.NO + "=" + no, null) > 0;
        }

    }

//    public void deleteDiaryItem(int no) {
//        String whereClause = Constants.DB_Column.NO + "=?";
//        String whereArgs[] = new String[]{String.valueOf(no)};
//        connection.delete(Constants.SAVE_LIST_TABLE, whereClause, whereArgs);
//    }

//
//    /**
//     * 로그 저장
//     * @param parent_no 부모 번호
//     * @param locationItemData 저장할 로그 정보
//     */
//    public void saveLocationLog(int parent_no , LocationItemData locationItemData)
//    {
//        ContentValues values = new ContentValues();
//        // INSERT
//        values.put(Constants.PARENTNO, String.valueOf(parent_no));
//        values.put(Constants.LAT, locationItemData.getLat());
//        values.put(Constants.LANG, locationItemData.getLang());
//        connection.insertOrThrow(Constants.SAVE_LIST_LOG_TABLE, null, values);
//    }

}
