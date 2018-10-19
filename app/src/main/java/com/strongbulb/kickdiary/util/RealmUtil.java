package com.strongbulb.kickdiary.util;

import com.strongbulb.kickdiary.model.DiaryData;

import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

import static io.realm.Realm.getDefaultInstance;

/**
 * Created by JeonGuKang on 2017. 8. 31..
 */

public class RealmUtil {

  public static DiaryData getDiaryDataItem(int no) {
    return getDefaultInstance().where(DiaryData.class).equalTo("no", no).findFirst();
  }

  public static RealmResults<DiaryData> getDiaryDataListSortDate(){
    return getDefaultInstance().where(DiaryData.class).findAll();
  }

  public static void prinAllRealmData() {
    RealmResults<DiaryData> realmResults = Realm.getDefaultInstance().where(DiaryData.class).findAll();
    for(DiaryData item :realmResults) {
      Log.i("mylog",  item.toString());
    }
  }

  public static int getNextKey(Realm realm) {
    try {
      Number number = realm.where(DiaryData.class).max("no");
      if (number != null) {
        return number.intValue() + 1;
      } else {
        return 0;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  }
  static boolean result = false;
  public static boolean deleteRealmData(final int no) {

    Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
      @Override
      public void execute(Realm realm) {
        result = false;
        result = realm.where(DiaryData.class).equalTo("no", no).findAll().deleteAllFromRealm();
      }
    });
    return result;
  }
}
