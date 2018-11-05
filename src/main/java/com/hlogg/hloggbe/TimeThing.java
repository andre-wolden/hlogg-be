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

    private int Year;
    private String Month;
    private int Week;
    private String Day;

    public TimeThing() {
        this.Year = LocalDate.now().getYear();
        this.Month = LocalDate.now().getMonth().toString();
        this.Week = LocalDate.now().get(WeekFields.ISO.weekOfYear());
        this.Day = LocalDate.now().getDayOfWeek().toString();
    }
}
