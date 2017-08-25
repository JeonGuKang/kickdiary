package com.strongbulb.kickdiary.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.skyfishjy.library.RippleBackground;
import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.databinding.ActivityMainBinding;
import com.strongbulb.kickdiary.preference.SharedPreferenceManager;
import com.strongbulb.kickdiary.view.base.BaseActivity;
import com.strongbulb.kickdiary.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {

    private MainViewModel mainViewModel;
    private ActivityMainBinding mainBinding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainBinding.setViewModel(mainViewModel);
        mainViewModel.oncreate();
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        initBottomButton();
        mainViewModel.startCurrentTime();
        mainBinding.lloAlramTime.setOnClickListener(mOnClickListener);
        if(!SharedPreferenceManager.getInstance().getGetOffWorkTime().equals("")) {
            mainViewModel.setGetOffWorkTime(SharedPreferenceManager.getInstance().getGetOffWorkTime());
            mainViewModel.startGetOffWorkTime();
        }
    }

    public void initBottomButton() {

        ImageView iv_edit = new ImageView(mContext); iv_edit.setBackgroundResource(R.mipmap.ic_edit);
        ImageView iv_list = new ImageView(mContext); iv_list.setBackgroundResource(R.mipmap.ic_list);
        iv_edit.setId(R.id.iv_edit);
        iv_list.setId(R.id.iv_list);
        iv_edit.setOnClickListener(mOnClickListener);
        iv_list.setOnClickListener(mOnClickListener);
        FrameLayout.LayoutParams tvParams = new FrameLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.dimen_icon_size), getResources().getDimensionPixelSize(R.dimen.dimen_icon_size));
        iv_edit.setLayoutParams(tvParams);
        iv_list.setLayoutParams(tvParams);
        SubActionButton.Builder subBuilder = new SubActionButton.Builder(MainActivity.this);
        FloatingActionMenu circleMenu = new FloatingActionMenu.Builder(MainActivity.this)
            .setStartAngle(270) // A whole circle!
            .setEndAngle(180)
            .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
            .addSubActionView(iv_edit)
            .addSubActionView(iv_list)
            .attachTo(getBinding().ivBottomButton)
            .build();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_edit:
                    startActivity(new Intent(mContext, EditDiaryActivity.class));
                    break;
                case R.id.iv_list:
                    startActivity(new Intent(mContext, DiaryListActivity.class));
                    break;
                case R.id.llo_alram_time:
                    mainViewModel.startAlarmTime();
                    break;
            }
        }
    };

    public ActivityMainBinding getBinding() {return mainBinding;}

    @Override
    protected void onDestroy() {
        mainViewModel.destroy();
        super.onDestroy();
    }

}
