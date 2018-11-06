package com.hlogg.hloggbe;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DateResource {

    @CrossOrigin
    @RequestMapping(value = "/dates", method = RequestMethod.GET)
    public List<DateThing> getDates(){

        LocalDate startDate = DateHelper.getOldestDate();
        LocalDate endDate = LocalDate.now();

        List<LocalDate> datesBetween = DateHelper.getDatesBetweenUsingJava8(startDate, endDate);

        DateThing today = new DateThing(LocalDate.now());

        List<DateThing> dates = DateHelper
                .localDateToDateThing(datesBetween);

        dates.add(today);

        return dates;
    }

}
