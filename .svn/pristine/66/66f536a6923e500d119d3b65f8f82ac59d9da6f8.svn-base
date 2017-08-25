package com.strongbulb.kickdiary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.strongbulb.kickdiary.R;

/**
 * Created by Kangjeongu on 2017. 4. 16..
 */

public class CommonAlertDialog extends Dialog implements View.OnClickListener {

    private CommonDialogInterface.OnClickListener mListener = null;

    private long mLastClickTime = 0;

    public CommonAlertDialog(Context context, CommonDialogInterface.OnClickListener listener) {

        super(context, R.style.Theme_CustomDialog);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.common_alert_dialog_layout);

        this.mListener = listener;

        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);

    }

    public void setTitleView(boolean isVisible) {
        findViewById(R.id.title_layout).setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    public void setTitle(String strTitle) {

        ((TextView)findViewById(R.id.title)).setText(strTitle);
    }


    public void setMessage(String strMessage) {

        ((TextView)findViewById(R.id.txtContent)).setText(strMessage);
    }

    public void setMessageFont(float fFontSize, float fLineSpace) {

        ((TextView)findViewById(R.id.txtContent)).setTextSize(TypedValue.COMPLEX_UNIT_SP, fFontSize);
        if(fLineSpace != 0.0f) {
            ((TextView)findViewById(R.id.txtContent)).setLineSpacing(fLineSpace, 1.0f);
        }

    }

    public void setRigthButtonTitle(String strTitle) {

        ((Button)findViewById(R.id.btn_ok)).setText(strTitle);
    }
    public void setLeftButtonTitle(String strTitle) {

        ((Button)findViewById(R.id.btn_cancel)).setText(strTitle);
    }

    public void setLeftButtonTitleColor(int color) {

        ((Button)findViewById(R.id.btn_cancel)).setTextColor(color);
    }

    public void setRightButtonTitleColor(int color) {

        ((Button)findViewById(R.id.btn_ok)).setTextColor(color);
    }

    public void setViewLefeButton(Boolean bVisible) {

        if(bVisible == false) {
            findViewById(R.id.llo_btn_cancel).setVisibility(View.GONE);
            findViewById(R.id.btn_cancel).setVisibility(View.GONE);
            findViewById(R.id.common_alert_view).setVisibility(View.GONE);

        }
    }



    @Override
    public void onClick(View view) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();


        switch (view.getId()) {
            case R.id.btn_cancel:
                dismiss();
                if(this.mListener != null)
                    this.mListener.onClick(view.getId());
                break;
            case R.id.btn_ok: {
                dismiss();
                if(this.mListener != null)
                    this.mListener.onClick(view.getId());
            }
            break;
        }
    }



}

