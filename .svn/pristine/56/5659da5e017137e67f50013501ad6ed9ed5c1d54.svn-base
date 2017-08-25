package com.strongbulb.kickdiary.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.dialog.CommonAlertDialog;
import com.strongbulb.kickdiary.dialog.CommonDialogInterface;
import com.strongbulb.kickdiary.model.DiaryData;
import com.strongbulb.kickdiary.util.KLog;
import com.strongbulb.kickdiary.util.Utils;
import com.strongbulb.kickdiary.view.activity.DiaryListActivity;
import com.strongbulb.kickdiary.view.activity.EditDiaryActivity;
import com.strongbulb.kickdiary.view.adapter.DiaryListAdapter;
import com.strongbulb.kickdiary.view.fragment.SublimePickerFragment;
import com.strongbulb.kickdiary.viewmodel.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JeonGuKang on 2017-04-11.
 */

public class DiaryListViewModel extends BaseViewModel {

    private Context mContext;
    private DiaryListActivity mDiaryListActivity;

    public DiaryListViewModel(Context context) {
        super(context);
        mContext = context;
        mDiaryListActivity = (DiaryListActivity)context;
    }

    @Override
    public void oncreate() {
        super.oncreate();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    public ArrayList<DiaryData> getDiaryData() {
        return mDiaryListActivity.getmDB().getInstance().getDiaryList();
    }

    public void deleteDiaryData(final int diaryNo , final int listPosition) {
        CommonAlertDialog commonAlertDialog = new CommonAlertDialog(mContext, new CommonDialogInterface.OnClickListener() {
            @Override
            public void onClick(int i) {
                switch (i) {
                    case R.id.btn_cancel:
                        break;

                    case R.id.btn_ok:
                        if(mDiaryListActivity.getmDB().getInstance().deleteDiaryItem(diaryNo)) {
                            mDiaryListActivity.getmDiaryListAdapter().removeItem(listPosition);
                        }
                        break;
                }
            }
        });
        commonAlertDialog.setTitleView(false);
        commonAlertDialog.setMessage(mContext.getResources().getString(R.string.str_warning_delete));
        commonAlertDialog.setLeftButtonTitle(mContext.getResources().getString(R.string.cancel));
        commonAlertDialog.setRigthButtonTitle(mContext.getResources().getString(R.string.str_ok));
        commonAlertDialog.setRightButtonTitleColor(mContext.getResources().getColor(R.color.color_carrot));
        commonAlertDialog.show();

    }

    public void startDiaryEditActivity(int position) {
        KLog.i("position = " + position );
      mDiaryListActivity.startActivityForResult(new Intent(mContext, EditDiaryActivity.class).putExtra(Constants.Extras.DIARYDATA, mDiaryListActivity.getmDiaryListAdapter().getItem(position)), Constants.ResultActivityCode.EDITDIARYACTIVITY);
    }

    public void listItemUpdate(int position) {
        for(int i = 0; i < mDiaryListActivity.getmDiaryListAdapter().getItemCount(); i++) {
            if(mDiaryListActivity.getmDiaryListAdapter().getItem(i).getNo() == position) {
                mDiaryListActivity.getmDiaryListAdapter().replaceItem(i,mDiaryListActivity.getmDB().getInstance().getDiaryList().get(i) );
                mDiaryListActivity.getmDiaryListAdapter().notifyItemChanged(i);
            }
        }
    }

}
