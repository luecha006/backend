package com.project10.projectc10_backend.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MFetchAllPatternResponse {
    private LocalDate date;
    private LocalTime time;
    private String maskpattern;
    private float temperature;
}
