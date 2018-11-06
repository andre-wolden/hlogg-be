package com.hlogg.hloggbe;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateHelper {

    public static List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }

    public static List<DateThing> localDateToDateThing(List<LocalDate> listOfLocalDates){
        List<DateThing> list = new ArrayList<>();
        for (LocalDate localDate : listOfLocalDates) {
            list.add(new DateThing(localDate));
        }
        return list;
    }
}
