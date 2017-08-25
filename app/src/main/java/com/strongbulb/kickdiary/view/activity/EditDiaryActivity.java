package com.strongbulb.kickdiary.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.databinding.ActivityEditDiaryBinding;
import com.strongbulb.kickdiary.model.DiaryData;
import com.strongbulb.kickdiary.preference.SharedPreferenceManager;
import com.strongbulb.kickdiary.view.base.BaseActivity;
import com.strongbulb.kickdiary.viewmodel.EditDiaryViewModel;


/**
 * Created by JeonGuKang on 2017-03-29.
 */

public class EditDiaryActivity extends BaseActivity {

    private EditDiaryViewModel          editDiaryViewModel;
    private ActivityEditDiaryBinding    editDiaryBinding;
    private Context                     mContext;
    private DiaryData                   mDiaryData;
    private boolean                     mIsModify               = false;
    private boolean                     mIsSaveClicked          = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        editDiaryBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_diary);
        editDiaryViewModel = new EditDiaryViewModel(this);
        editDiaryBinding.setEditDiaryViewModel(editDiaryViewModel);
        setCustomParentToolbar(R.color.color_peter_river, mOnClickListener);
        setTitleRightButton(R.mipmap.ic_save, mOnClickListener);
        getIntentData();

    }

    private void getIntentData() {
        if(getIntent() != null) {
            if(getIntent().getExtras() != null) {
                mIsModify = true;
                mDiaryData = (DiaryData)getIntent().getExtras().getSerializable(Constants.Extras.DIARYDATA);
                initView(mDiaryData);
            } else {
                initView();
            }
            setIntent(null);
        }
    }

    public void initView (DiaryData diaryData) {
        setTitleUseUiThread(diaryData.getDate());
        editDiaryBinding.etEditDiaryTitle.setText(diaryData.getTitle());
        editDiaryBinding.etEditDiaryContent.setText(diaryData.getContent());
    }

    public void initView () {
        setTitleUseUiThread(SharedPreferenceManager.getInstance().getDate().equals("") ? editDiaryViewModel.getCurrentDate(): SharedPreferenceManager.getInstance().getDate());
        editDiaryBinding.etEditDiaryTitle.setText(SharedPreferenceManager.getInstance().getTitle());
        editDiaryBinding.etEditDiaryContent.setText(SharedPreferenceManager.getInstance().getContent());
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_edit:
                    startActivity(new Intent(mContext, EditDiaryActivity.class));
                    break;
                case R.id.iv_list:
                    break;
                case R.id.llTitleLeft:
                    finish();
                    break;
                case R.id.llTitleRight:
                case R.id.btnTitleRight:
                    setTitleRightButton(R.mipmap.ic_save, null);
                    if(mIsModify) {
                        editDiaryViewModel.updateDiary(mDiaryData.getNo(),editDiaryBinding.etEditDiaryTitle.getText().toString(),
                                editDiaryBinding.etEditDiaryContent.getText().toString(),
                                getStrTitle());
                        setResult(RESULT_OK, new Intent().putExtra(Constants.Extras.NO, mDiaryData.getNo()));
                    } else {
                        editDiaryViewModel.saveDiary(editDiaryBinding.etEditDiaryTitle.getText().toString(),
                                editDiaryBinding.etEditDiaryContent.getText().toString(),
                                getStrTitle());
                        SharedPreferenceManager.getInstance().tempDeleteDiary();

                    }
                    mIsSaveClicked = true;
                    finish();
                    break;
                case R.id.rlo_title:
                    editDiaryViewModel.startDatePickerDialog();
                    break;
            }
        }
    };

    public void setTitleUseUiThread(final String date) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTitle(date);
                    }
                });
            }
        }).start();
    }

    public ActivityEditDiaryBinding getBinding() {return editDiaryBinding;}

    @Override
    protected void onPause() {
        super.onPause();
        if(!mIsModify && !mIsSaveClicked) {
                SharedPreferenceManager.getInstance().tempSaveDiary(editDiaryBinding.etEditDiaryTitle.getText().toString(),
                        editDiaryBinding.etEditDiaryContent.getText().toString(),
                        getStrTitle());
        };
    }

    @Override
    protected void onDestroy() {
        editDiaryViewModel.destroy();
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
            return super.onOptionsItemSelected(item);
        }
    }

}
