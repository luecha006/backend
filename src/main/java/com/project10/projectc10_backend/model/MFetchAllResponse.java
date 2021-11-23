package com.project10.projectc10_backend.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MFetchAllResponse {

    private LocalDate date;

    private LocalTime time;

    private String username;

    private String type_admin;
}
