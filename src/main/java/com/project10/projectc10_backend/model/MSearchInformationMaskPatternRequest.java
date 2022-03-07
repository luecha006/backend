package com.project10.projectc10_backend.model;

import lombok.Data;

@Data
public class MSearchInformationMaskPatternRequest {
    private String pattern;
    private String s_time;
    private String e_time;
    private String s_date;
    private String e_date;
    private float s_temperature;
    private float e_temperature;
}
