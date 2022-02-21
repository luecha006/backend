package com.project10.projectc10_backend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "history_scanner")
@Data
public class HistoryScanner extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private String maskpattern;

    @Column(nullable = false)
    private float temperature;

}
