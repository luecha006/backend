package com.project10.projectc10_backend.repository;

import com.project10.projectc10_backend.entity.HistoryScanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface MaskPatternRepository extends CrudRepository<HistoryScanner, Integer> {

    @Query(value = "select * from history_scanner where (time between ?1 AND ?2) AND (date = ?3)", nativeQuery = true)
    Iterable<HistoryScanner> findByHomepageCurrentDay(LocalTime s_value, LocalTime e_value, LocalDate date);

    @Query(value = "select * from history_scanner where date between ?1 AND ?2", nativeQuery = true)
    Iterable<HistoryScanner> findByHomepageWeeklyANDMonthly(LocalDate s_date, LocalDate e_date);


    Optional<HistoryScanner> findByTemperature(float f);
}
