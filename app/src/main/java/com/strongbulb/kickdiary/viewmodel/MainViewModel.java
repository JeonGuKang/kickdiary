package com.strongbulb.kickdiary.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.strongbulb.kickdiary.preference.SharedPreferenceManager;
import com.strongbulb.kickdiary.util.RealmUtil;
import com.strongbulb.kickdiary.view.activity.MainActivity;
import com.strongbulb.kickdiary.view.fragment.SublimePickerFragment;
import com.strongbulb.kickdiary.viewmodel.base.BaseViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by JeonGuKang on 2017-03-29.
 */

public class MainViewModel extends BaseViewModel{

    private Context mContext;
    private MainActivity mainActivity;
    private Timer mCurrentTimer;
    private Timer mGetOffWorkTimer;
    public MainViewModel(Context context) {
        super(context);
    }

    @Override
    public void oncreate() {
        super.oncreate();
        mainActivity =  (MainActivity) mBaseContext;
        //Log.i("mylog", ">>>>>   ApplicationClass.getDiaryDataList().size() :  " + RealmUtil.getDiaryDataList().size());
        RealmUtil.prinAllRealmData();
        //mainActivity.startActivity(new Intent(mainActivity, BackupActivity.class));
    }

    public void startCurrentTime() {
        mCurrentTimer = new Timer();
        CurrentimerTask timerTask = new CurrentimerTask();
        mCurrentTimer.schedule(timerTask, 500, 1000);
    }

    public void startAlarmTime() {
        SublimePickerFragment pickerFrag = new SublimePickerFragment();
        pickerFrag.setCallback(mAlarmCallback);
        int displayOptions = 0;
        displayOptions |= SublimeOptions.ACTIVATE_TIME_PICKER;
        SublimeOptions options = new SublimeOptions();
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(SublimeOptions.Picker.TIME_PICKER);
        Bundle bundle = new Bundle();
        bundle.putParcelable("SUBLIME_OPTIONS",options);
        pickerFrag.setArguments(bundle);
        pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        pickerFrag.show(mainActivity.getSupportFragmentManager(), "SUBLIME_PICKER");
    }

    public void setGetOffWorkTime(String time) {
        mainActivity.getBinding().tvAlarmTime.setText(time);
    }

    private Handler mCurrentUpdateTimeHandler = new Handler();

    private Runnable mCurrentUpdateTimeTask = new Runnable() {
        public void run() {
            Date rightNow = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                "HH : mm : ss", Locale.KOREA);
            String dateString = formatter.format(rightNow);
            mainActivity.getBinding().tvCurrentTime.setText(dateString);
        }
    };

    private class CurrentimerTask extends TimerTask {
        public void run() {
            mCurrentUpdateTimeHandler.post(mCurrentUpdateTimeTask);
        }
    }

    private Handler mGetOffWorkTimeHandler = new Handler();

    private Runnable mGetOffWorkTimeTask = new Runnable() {
        public void run() {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss",Locale.KOREA);
            String dateString = formatter.format(calendar.getTime());

            try {
                mainActivity.getBinding().tvCountdownTime.setText(getElapsedTime(dateString.trim(), SharedPreferenceManager.getInstance().getGetOffWorkTime().trim(),0));
                //KLog.i("SharedPreferenceManager.getInstance().getGetOffWorkTime().trim() = " + SharedPreferenceManager.getInstance().getGetOffWorkTime().trim());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    private class GetOffWorkTask extends TimerTask {
        public void run() {
            mGetOffWorkTimeHandler.post(mGetOffWorkTimeTask);
        }
    }

    public void startGetOffWorkTime() {
        mGetOffWorkTimer = new Timer();
        GetOffWorkTask timerTask = new GetOffWorkTask();
        mGetOffWorkTimer.schedule(timerTask, 500, 1000);
    }

    SublimePickerFragment.Callback mAlarmCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate,
                                            int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                            String recurrenceRule) {
            mainActivity.getBinding().tvAlarmTime.setText(hourOfDay + ":" + minute+":00");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 00);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss",Locale.KOREA);
            String dateString = formatter.format(calendar.getTime());
            SharedPreferenceManager.getInstance().setGetOffWorkTime(dateString);
            startGetOffWorkTime();
        }
    };

    private String getElapsedTime(final String begin_time, final String end_time, int compare) throws Exception {
        DateFormat stringFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            // String을 Date로 포맷
            Date beginDate = stringFormat.parse(begin_time);
            Date endDate = stringFormat.parse(end_time);

            long gap = ((endDate.getTime()) - (beginDate.getTime())) /1000 ; // 초 단위
            if(beginDate.getTime() > endDate.getTime()) {
                Calendar initCalendar = Calendar.getInstance();
                initCalendar.set(Calendar.HOUR_OF_DAY, 23 );
                initCalendar.set(Calendar.MINUTE, 59);
                initCalendar.set(Calendar.SECOND, 59);
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String dateString = formatter.format(initCalendar.getTime());
                Date initDate = stringFormat.parse(dateString);
                gap = (((initDate.getTime()+32400000) + endDate.getTime()) - beginDate.getTime())/ 1000;
            }

            long hourGap = gap / 60 / 60 ;
            long reminder = ((long)(gap / 60)) % 60 ;
            long minuteGap = reminder ;
            long secondGap = gap % 60 ;
            String returnTime = hourGap
                + ":" + minuteGap+ ":"+ secondGap;

            return returnTime;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new Exception(e) ;
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }



}

