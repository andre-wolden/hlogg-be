package com.hlogg.hloggbe;

import java.time.LocalDate;

public class ActivityRecord {

    public int Id;
    public Activity activity;
    public LocalDate date;

    public ActivityRecord(Activity activity) {
        this.activity = activity;
        this.date = LocalDate.now();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
