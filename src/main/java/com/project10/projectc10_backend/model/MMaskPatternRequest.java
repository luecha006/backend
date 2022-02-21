package com.project10.projectc10_backend.model;

import lombok.Data;

@Data
public class MMaskPatternRequest {
    private String date;
    private String time;
    private String maskpattern;
    private float temperature;
}
