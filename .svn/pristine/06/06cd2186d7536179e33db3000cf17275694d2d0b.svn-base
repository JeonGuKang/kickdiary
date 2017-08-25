package com.strongbulb.kickdiary.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by JeonGuKang on 2017-04-11.
 */

public class DiaryData implements Serializable {


    @SerializedName("title")                      String title;
    @SerializedName("content")                    String content;
    @SerializedName("date")                       String date;
    @SerializedName("type")                       int    type;
    @SerializedName("no")                         int    no;

    public DiaryData(int no, String title, String content, String date , int type) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public DiaryData(String title, String content, String date , int type) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
