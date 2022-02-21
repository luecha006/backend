package com.project10.projectc10_backend.model;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class MaskPatternResponse {
    private Date date;
    private Time time;
    private String mask_pattern;
    private float temperature;
}
