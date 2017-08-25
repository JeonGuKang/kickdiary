package com.strongbulb.kickdiary.dialog;

/**
 * Created by fogman on 2014. 1. 13..
 */
public interface CommonDialogInterface {

    interface OnClickListener {
        void onClick(int i);
    }

    interface OnItemClickListener {
        void onItemClick(int i);
    }

    interface OnBackPressedListener {
        void onBackPressed();
    }

    interface SendValueOnClickListener<T> {
        void onClick(int i, T t);
    }

}
