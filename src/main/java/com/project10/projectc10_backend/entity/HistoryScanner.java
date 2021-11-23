package com.project10.projectc10_backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "history_scanner")
public class HistoryScanner extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private Integer mask_pattern;

    @Column(nullable = false)
    private Float temperature;


}
