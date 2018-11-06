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
public class DateThing {

    private LocalDate localDate;
    private int year;
    private String month;
    private int week;
    private String dayOfWeek;

    public DateThing(LocalDate localDate) {
        this.localDate = localDate;
        this.year = this.localDate.getYear();
        this.month = this.localDate.getMonth().toString();
        this.week = this.localDate.get(WeekFields.ISO.weekOfYear());
        this.dayOfWeek = this.localDate.getDayOfWeek().toString();
    }
}
