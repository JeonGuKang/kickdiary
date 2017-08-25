package com.strongbulb.kickdiary.view.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.strongbulb.kickdiary.Constants;
import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.util.Utils;
import com.strongbulb.kickdiary.view.adapter.DBAdapter;

/**
 * Created by JeonGuKang on 2017-01-02.
 */

public class BaseActivity extends AppCompatActivity {

    private Context         mBaseContext;
    private View            mCustomBar;
    private LinearLayout    llTitleLeft;
    private LinearLayout    llTitleRight;
    private ImageView       mBtnTitleLeft;
    private RelativeLayout  mRloTitle;
    private TextView        mTitle;
    private ImageView       mBtnTitleRight;
    private DBAdapter       mDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseContext = this;
        mCustomBar = LayoutInflater.from(mBaseContext).inflate(R.layout.layout_editdiary_header, null);
        connectDataBase();
    }

    @Override
    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }

    public void connectDataBase() {
        mDB.getInstance().connect(mBaseContext);
    }

    public void closeDB() {
        mDB.getInstance().close();
    }

    public DBAdapter getmDB() {
        return mDB;
    }

    public void setCustomParentToolbar(final int BackGroundColor,final View.OnClickListener listener) {

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                        getSupportActionBar().setCustomView(mCustomBar);
                        getSupportActionBar().setDisplayShowCustomEnabled(true);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        Toolbar parent = (Toolbar) getSupportActionBar().getCustomView().getParent(); // first get parent toolbar of current action bar
                        parent.setContentInsetsAbsolute(0, 0);              // set padding programmatically to 0dp
                        getSupportActionBar().setCustomView(mCustomBar,params);
                        Utils.setStatusBarColor(BaseActivity.this, BackGroundColor);
                        llTitleLeft     = (LinearLayout)getSupportActionBar().getCustomView().findViewById(R.id.llTitleLeft);
                        mRloTitle       =  (RelativeLayout) getSupportActionBar().getCustomView().findViewById(R.id.rlo_title);

                        mTitle  = (TextView) mCustomBar.findViewById(R.id.titleString);
                        llTitleLeft.setOnClickListener(listener);
                        mTitle.setText("");
                        mRloTitle.setOnClickListener(listener);
                    }
                });
            }
        }).start();

    }

    public void setTitle(String str) {
        ((TextView)mCustomBar.findViewById(R.id.titleString)).setText(str );
    }

    public void setTitle(int txtRes) {
        ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.titleString)).setText(this.getResources().getString(txtRes));
    }

    public String getStrTitle() {
        return  ((TextView)mCustomBar.findViewById(R.id.titleString)).getText().toString();
    }

    public void setTitleLeftButton(int imageRes, View.OnClickListener listener) {
        mBtnTitleLeft.setBackgroundResource(imageRes);
        mBtnTitleLeft.setOnClickListener(listener);
        llTitleLeft.setOnClickListener(listener);
    }

    public void setTitleLeftButton(View.OnClickListener listener) {
        mBtnTitleLeft.setOnClickListener(listener);
        llTitleLeft.setOnClickListener(listener);
    }

    public void setTitleRightButton(int imageRes, View.OnClickListener listener) {

        ((ImageView)mCustomBar.findViewById(R.id.btnTitleRight)).setBackgroundResource(imageRes);
        ((ImageView)mCustomBar.findViewById(R.id.btnTitleRight)).setOnClickListener(listener);
        ((LinearLayout)mCustomBar.findViewById(R.id.llTitleRight)).setOnClickListener(listener);
    }

    public void setTitleRightButton(View.OnClickListener listener) {
        mBtnTitleRight.setOnClickListener(listener);
        llTitleRight.setOnClickListener(listener);
    }

    public void setTitleLeftButtonVisible(boolean bShow) {

        if (bShow)
            mBtnTitleLeft.setVisibility(View.VISIBLE);
        else
            mBtnTitleLeft.setVisibility(View.GONE);
    }

    public void setTitleRightButtonVisible(boolean bShow) {

        if (bShow)
            mBtnTitleRight.setVisibility(View.VISIBLE);
        else
            mBtnTitleRight.setVisibility(View.GONE);
    }

}
