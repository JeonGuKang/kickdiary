package com.strongbulb.kickdiary.viewmodel;

import android.content.Context;

import com.strongbulb.kickdiary.viewmodel.base.BaseViewModel;

/**
 * Created by JeonGuKang on 2017. 9. 1..
 */

public class ListFileViewModel extends BaseViewModel {

  private Context mContext;


  public ListFileViewModel(Context context) {
    super(context);
    mContext = context;

  }

  @Override
  public void oncreate() {
    super.oncreate();
  }

  @Override
  public void destroy() {
    super.destroy();
  }

}

