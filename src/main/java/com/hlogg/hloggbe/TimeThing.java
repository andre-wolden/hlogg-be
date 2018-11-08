package com.hlogg.hloggbe;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public class TimeThing {

    private int year;
    private String month;
    private int week;
    private String day;
    private int dayOfMonth;

    public TimeThing() {
        this.year = LocalDate.now().getYear();
        this.month = LocalDate.now().getMonth().toString();
        this.week = LocalDate.now().get(WeekFields.ISO.weekOfYear());
        this.day = LocalDate.now().getDayOfWeek().toString();
        this.dayOfMonth = LocalDate.now().getDayOfMonth();
    }
}
