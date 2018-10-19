package com.strongbulb.kickdiary.viewmodel;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.model.DiaryData;
import com.strongbulb.kickdiary.preference.SharedPreferenceManager;
import com.strongbulb.kickdiary.util.RealmUtil;
import com.strongbulb.kickdiary.util.Utils;
import com.strongbulb.kickdiary.view.activity.EditDiaryActivity;
import com.strongbulb.kickdiary.view.fragment.SublimePickerFragment;
import com.strongbulb.kickdiary.viewmodel.base.BaseViewModel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import io.realm.Realm;

import static io.realm.Realm.getDefaultInstance;

/**
 * Created by JeonGuKang on 2017-03-29.
 */

public class EditDiaryViewModel extends BaseViewModel {

    private EditDiaryActivity mEditDiaryActivity;

    private String allTime;

    public EditDiaryViewModel(Context context) {
        super(context);
        mEditDiaryActivity = (EditDiaryActivity)context;
    }

    @Override
    public void oncreate() {
        super.oncreate();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public String getCurrentDate() {
        java.util.Date currentDate = new java.util.Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm");
        allTime = format.format(currentDate);
        return Utils.getYearAndMonthAndDayOfDate(currentDate);
    }

//    public String getDate(Date inputdate) {
//        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
//        return format.format(inputdate);
//    }

    public void testDate() {
        try {
            String textDate = "2007-7-22";
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            date = format.parse(textDate);
            java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("yyyy년MM월dd일 HH시mm분ss초");
            String dateString = format1.format(date);
            System.out.println(dateString);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void startDatePickerDialog() {

        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mFragmentCallback);
        int displayOptions = 0;
        displayOptions |= SublimeOptions.ACTIVATE_DATE_PICKER;
        SublimeOptions options = new SublimeOptions();
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(SublimeOptions.Picker.DATE_PICKER);
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS",options);
        pickerFrag.setArguments(bundle);
        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(mEditDiaryActivity.getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
            //rlDateTimeRecurrenceInfo.setVisibility(View.GONE);
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate,
                                            int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                            String recurrenceRule) {
            if(mEditDiaryActivity.isModify()) {
                allTime = Utils.getDate(selectedDate.getFirstDate().getTime()).substring(0,10)+allTime.substring(10, allTime.length());
            } else {
                allTime = Utils.getDate(selectedDate.getFirstDate().getTime());
            }

            mEditDiaryActivity.setTitleUseUiThread( Utils.getYearAndMonthAndDayOfDate(selectedDate.getFirstDate().getTime()));
        }
    };

    public void updateDiary(final int no,final String title,final String content) {

        getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DiaryData diaryData = null;
                diaryData = realm.where(DiaryData.class).equalTo("no", no).findFirst();
                diaryData.setTitle(title);
                diaryData.setContent(content);
                diaryData.setDate(allTime);

            }
        });
    }

    public void saveDiary(final String title,final String content) {

        getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DiaryData diaryData = null;
                if(realm.where(DiaryData.class).findFirst() != null) {
                    diaryData = realm.createObject(DiaryData.class, RealmUtil.getNextKey(realm));
                } else {
                    diaryData = realm.createObject(DiaryData.class, 0);
                }

                diaryData.setTitle(title);
                diaryData.setContent(content);
                diaryData.setDate(allTime);
                diaryData.setType(Constants.DiaryType.DEFULT);
            }
        });

    }

    public void tempSaveDiary(String title, String content, String date) {
        SharedPreferenceManager.getInstance().tempSaveDiary(title, content, date);
    }

    public void setAllTime(String allTime) {
        this.allTime = allTime;
    }

}
