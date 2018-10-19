package com.strongbulb.kickdiary.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strongbulb.kickdiary.R;
import com.strongbulb.kickdiary.model.DiaryData;
import com.strongbulb.kickdiary.util.Utils;
import com.strongbulb.kickdiary.view.viewholder.DiaryListViewHolder;

import java.util.ArrayList;

/**
 * Created by JeonGuKang on 2017-04-11.
 */

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListViewHolder> {

    private final static String TAG = DiaryListAdapter.class.getSimpleName();
    private ArrayList<DiaryData> mDiaryDataList;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener;

    public DiaryListAdapter(Context context, OnItemClickListener onitemClickListener) {
        this.mContext = context;
        this.mOnItemClickListener = onitemClickListener;
        this.mDiaryDataList = new ArrayList<>();
    }

    @Override
    public DiaryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_diary, null);
        DiaryListViewHolder rcv = new DiaryListViewHolder(v, mContext);
        return rcv;
    }

    @Override
    public void onBindViewHolder(DiaryListViewHolder holder, int _position) {
        final int position = _position;
        final DiaryData item = mDiaryDataList.get(position);
        holder.tvMonth.setText(Utils.monthOfDate(mDiaryDataList.get(position).getDate()));
        holder.tvDay.setText(Utils.dayOfDate(mDiaryDataList.get(position).getDate()));
        holder.tvTitle.setText(mDiaryDataList.get(position).getTitle());
        holder.tvContent.setText(mDiaryDataList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mDiaryDataList.size();
    }

    public DiaryData getItem(int position) {
        return mDiaryDataList.get(position);
    }

    public ArrayList<DiaryData> getDiaryDataList() {
        return mDiaryDataList;
    }

    public void add(DiaryData data) {
        mDiaryDataList.add(data);
    }

    public void add(ArrayList<DiaryData> diaryDataArrayList) {
        mDiaryDataList = diaryDataArrayList;
    }

    public void replaceItem(int position, DiaryData diaryData) {
        mDiaryDataList.set(position,diaryData );
    }

    public void clear() {
        this.mDiaryDataList.clear();
    }

    public void removeItem(int position) {
        this.mDiaryDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.mDiaryDataList.size());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, final int position, final DiaryData historyItem);

        void onDeleteClick(final int position, final DiaryData historyItem);

        void onItemImageClick(View view, final int position, final DiaryData historyItem);

        void onItemLongClick(View view, final int position, final DiaryData data);
    }

}

