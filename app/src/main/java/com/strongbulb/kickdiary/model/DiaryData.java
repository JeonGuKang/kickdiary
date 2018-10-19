package com.strongbulb.kickdiary.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by JeonGuKang on 2017-04-11.
 */

public  class DiaryData extends RealmObject {

    @PrimaryKey
    int    no;
    String title;
    String content;
    String date;
    int    type;

    public DiaryData() {}

    public DiaryData(int no, String title, String content, String date , int type) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
