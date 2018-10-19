package com.strongbulb.kickdiary.viewmodel;

import android.content.Context;
import android.content.Intent;

import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.dialog.CommonAlertDialog;
import com.strongbulb.kickdiary.dialog.CommonDialogInterface;
import com.strongbulb.kickdiary.model.DiaryData;
import com.strongbulb.kickdiary.util.KLog;
import com.strongbulb.kickdiary.util.RealmUtil;
import com.strongbulb.kickdiary.view.activity.DiaryListActivity;
import com.strongbulb.kickdiary.view.activity.EditDiaryActivity;
import com.strongbulb.kickdiary.viewmodel.base.BaseViewModel;

import io.realm.RealmResults;

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

    public RealmResults<DiaryData> getDiaryData() {
        return RealmUtil.getDiaryDataListSortDate();
    }

    public void deleteDiaryData(final int diaryNo , final int listPosition) {
        CommonAlertDialog commonAlertDialog = new CommonAlertDialog(mContext, new CommonDialogInterface.OnClickListener() {
            @Override
            public void onClick(int i) {
                switch (i) {
                    case R.id.btn_cancel:
                        break;

                    case R.id.btn_ok:
                        if(RealmUtil.deleteRealmData(diaryNo)) {
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
      mDiaryListActivity.startActivityForResult(new Intent(mContext, EditDiaryActivity.class).putExtra(Constants.Extras.NO, mDiaryListActivity.getmDiaryListAdapter().getItem(position).getNo()), Constants.ResultActivityCode.EDITDIARYACTIVITY);
    }

    public void listItemUpdate(int position) {
        int index = 0;
        for(DiaryData data : mDiaryListActivity.getmDiaryListAdapter().getDiaryDataList()) {
            if(data.getNo() == position) {
                mDiaryListActivity.getmDiaryListAdapter().notifyItemChanged(index);
            }
            index ++;
        }
    }

}
