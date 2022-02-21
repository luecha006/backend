package com.project10.projectc10_backend.ConvertDateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConvertStringToLocalDateTime {
    public LocalDate ConvertStringToLocalDate(String date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatters);
    }

    public LocalTime ConvertStringToLocalTime(String time) {
        return LocalTime.parse(time);
    }

//    public String ConvertLocalDateToString(LocalDate date){
//        DateTimeFormatter dFormatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        return date.format(dFormatters);
//    }
}
