package com.strongbulb.kickdiary.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.databinding.ActivityDiarylistBinding;
import com.strongbulb.kickdiary.model.DiaryData;
import com.strongbulb.kickdiary.util.KLog;
import com.strongbulb.kickdiary.util.RealmUtil;
import com.strongbulb.kickdiary.view.adapter.DiaryListAdapter;
import com.strongbulb.kickdiary.view.base.BaseActivity;
import com.strongbulb.kickdiary.viewmodel.DiaryListViewModel;

import java.util.ArrayList;

/**
 * Created by JeonGuKang on 2017-04-11.
 */

public class DiaryListActivity extends BaseActivity {

    private DiaryListViewModel diaryListViewModel;
    private ActivityDiarylistBinding diarylistBinding;
    private DiaryListAdapter mDiaryListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        diarylistBinding = DataBindingUtil.setContentView(this, R.layout.activity_diarylist);
        diaryListViewModel = new DiaryListViewModel(this);
        diarylistBinding.setDiaryListViewModel(diaryListViewModel);
        setCustomParentToolbar(R.color.color_peter_river, mOnClickListener);
        linearLayoutManager = new LinearLayoutManager(mContext);
        initView();
    }

    public void initView() {
        mDiaryListAdapter = new DiaryListAdapter(mContext, onItemClickListener);
        diarylistBinding.rvList.setAdapter(mDiaryListAdapter);
        diarylistBinding.rvList.setHasFixedSize(true);
        diarylistBinding.rvList.setLayoutManager(linearLayoutManager);

        ArrayList<DiaryData> diaryDataList = new ArrayList(RealmUtil.getDiaryDataListSortDate());
        mDiaryListAdapter.add(diaryDataList);
        mDiaryListAdapter.notifyDataSetChanged();

    }

    private DiaryListAdapter.OnItemClickListener onItemClickListener = new DiaryListAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, DiaryData historyItem) {

        }

        @Override
        public void onDeleteClick(int position, DiaryData historyItem) {

        }

        @Override
        public void onItemImageClick(View view, int position, DiaryData historyItem) {

        }

        @Override
        public void onItemLongClick(View view, int position, DiaryData data) {

        }
    };


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
                    KLog.i("click");

                    break;
                case R.id.rlo_title:

                    break;
            }
        }
    };


    public ActivityDiarylistBinding getBinding() {return diarylistBinding;}

    public DiaryListViewModel getDiaryListViewModel() {return diaryListViewModel;}

    public DiaryListAdapter getmDiaryListAdapter() {return mDiaryListAdapter;}
    @Override
    protected void onDestroy() {
        diaryListViewModel.destroy();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.ResultActivityCode.EDITDIARYACTIVITY) {
            if(resultCode == RESULT_OK) {
                diaryListViewModel.listItemUpdate(data.getIntExtra(Constants.Extras.NO, 0));
            }
        }
    }
}

