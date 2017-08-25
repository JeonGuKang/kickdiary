package com.strongbulb.kickdiary.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.eventbus.holder.OttoBusHolder;
import com.strongbulb.kickdiary.model.DiaryData;
import com.strongbulb.kickdiary.preference.SharedPreferenceManager;
import com.strongbulb.kickdiary.util.KLog;
import com.strongbulb.kickdiary.util.Utils;
import com.strongbulb.kickdiary.view.activity.EditDiaryActivity;
import com.strongbulb.kickdiary.view.activity.MainActivity;
import com.strongbulb.kickdiary.view.adapter.DBAdapter;
import com.strongbulb.kickdiary.view.fragment.SublimePickerFragment;
import com.strongbulb.kickdiary.viewmodel.base.BaseViewModel;

import java.util.Date;

/**
 * Created by JeonGuKang on 2017-03-29.
 */

public class EditDiaryViewModel extends BaseViewModel {

    private Context mContext;
    private EditDiaryActivity mEditDiaryActivity;
    private SelectedDate mSelectedDate;

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
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return format.format(currentDate);
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
            mSelectedDate = selectedDate;
            mEditDiaryActivity.setTitleUseUiThread( Utils.getDate(selectedDate.getFirstDate().getTime()));
        }
    };

    public void updateDiary(int no, String title, String content, String date) {
        mEditDiaryActivity.getmDB().getInstance().updateDiary(new DiaryData(no, title, content, date, 1));
        for(int i = 0; i < mEditDiaryActivity.getmDB().getInstance().getDiaryList().size(); i++) {
            Utils.testClassToString(mEditDiaryActivity.getmDB().getInstance().getDiaryList().get(i));
        }
    }

    public void saveDiary(String title, String content, String date) {
        mEditDiaryActivity.getmDB().getInstance().saveDiary(new DiaryData(title, content, date, 1));
        for(int i = 0; i < mEditDiaryActivity.getmDB().getInstance().getDiaryList().size(); i++) {
            Utils.testClassToString(mEditDiaryActivity.getmDB().getInstance().getDiaryList().get(i));
        }
    }

    public void tempSaveDiary(String title, String content, String date) {
        SharedPreferenceManager.getInstance().tempSaveDiary(title, content, date);
    }

}
