package com.strongbulb.kickdiary.viewmodel.base;

import android.content.Context;

import com.strongbulb.kickdiary.eventbus.holder.OttoBusHolder;
import com.strongbulb.kickdiary.view.adapter.DBAdapter;
import com.strongbulb.kickdiary.viewmodel.ViewModel;

/**
 * Created by JeonGuKang on 2017-03-29.
 */

public class BaseViewModel implements ViewModel {

    public Context mBaseContext;

    @Override
    public void destroy() {
        OttoBusHolder.get().unregister(mBaseContext);
        mBaseContext = null;
    }

    @Override
    public void oncreate() {
        OttoBusHolder.get().register(mBaseContext);
    }

    public BaseViewModel(Context context) {
        this.mBaseContext = context;
    }

}
