package com.strongbulb.kickdiary.view.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.util.KLog;
import com.strongbulb.kickdiary.view.activity.DiaryListActivity;

/**
 * Created by JeonGuKang on 2017-04-12.
 */

public class DiaryListViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public LinearLayout lloParent;
    public TextView tvMonth;
    public TextView tvDay;
    public TextView tvTitle;
    public TextView tvContent;
    public Button   btnDelete;
    public Context  mContext;


    public DiaryListViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        tvMonth     = (TextView) itemView.findViewById(R.id.month);
        tvDay       = (TextView) itemView.findViewById(R.id.day);
        tvTitle     = (TextView) itemView.findViewById(R.id.title);
        tvContent   = (TextView) itemView.findViewById(R.id.content);
        lloParent   = (LinearLayout) itemView.findViewById(R.id.llo_parent);
        btnDelete   = (Button)   itemView.findViewById(R.id.delete);
        lloParent.setOnClickListener(this);
        lloParent.setOnLongClickListener(this);
        btnDelete.setOnClickListener(this);

    }

    @Override
    public boolean onLongClick(View view) {
        ((DiaryListActivity)mContext).getDiaryListViewModel().deleteDiaryData(((DiaryListActivity)mContext).getmDiaryListAdapter().getItem(getAdapterPosition()).getNo(), getAdapterPosition());
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.delete) {
            KLog.i("Click Delete");
            ((DiaryListActivity)mContext).getDiaryListViewModel().deleteDiaryData(((DiaryListActivity)mContext).getmDiaryListAdapter().getItem(getAdapterPosition()).getNo(), getAdapterPosition());
            return ;
        }
        ((DiaryListActivity)mContext).getDiaryListViewModel().startDiaryEditActivity( getAdapterPosition());
    }

}

