package com.project10.projectc10_backend.repository;

import com.project10.projectc10_backend.entity.HistoryScanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface MaskPatternRepository extends CrudRepository<HistoryScanner, Integer> {

    @Query(value = "select * from history_scanner " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> DisplayTablePageAngSearchInformationPageAllNull ();

    @Query(value = "select * from history_scanner " +
            "where (time between ?1 AND ?2) AND " +
            "(date=?3) order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> selectHomepageCurrentDay(LocalTime s_value, LocalTime e_value, LocalDate date);

    @Query(value = "select * from history_scanner where date between ?1 AND ?2 order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> findByHomepageWeeklyANDMonthly(LocalDate s_date, LocalDate e_date);

    @Query(value = "select * from history_scanner where date=?1", nativeQuery = true)
    Iterable<HistoryScanner> selectHomePage(LocalDate currentDay);

    //    ส่งมาครบทุกตัวและมีรูปแบบ
    @Query(value = "select * from history_scanner " +
            "where (maskpattern=?1) and " +
            "(time between ?2 and ?3) and " +
            "(date between ?4 and ?5) and " +
            "(temperature between ?6 and ?7) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPagePatternAllValue(
            String pattern,
            LocalTime s_time,
            LocalTime e_time,
            LocalDate s_date,
            LocalDate e_date,
            float s_temperature,
            float e_temperature);

    //    เป็น null หมดยกเว้นรูปแบบ
    @Query(value = "select * from history_scanner " +
            "where maskpattern=?1 " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCasePatternAllNull(String pattern);

    //    เวลาเป็น null
    @Query(value = "select * from history_scanner " +
            "where (maskpattern=?1) and " +
            "(date between ?2 and ?3) and " +
            "(temperature between ?4 and ?5) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCasePatternTimeNull(
            String pattern,
            LocalDate s_date,
            LocalDate e_date,
            float s_temperature,
            float e_temperature);

    //    วันที่เป็น null
    @Query(value = "select * from history_scanner " +
            "where (maskpattern=?1) and " +
            "(time between ?2 and ?3) and " +
            "(temperature between ?4 and ?5) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCasePatternDateNull(
            String pattern,
            LocalTime s_time,
            LocalTime e_time,
            float s_temperature,
            float e_temperature);

    //    อุณหภูมิเป็น null
    @Query(value = "select * from history_scanner " +
            "where (maskpattern=?1) and " +
            "(time between ?2 and ?3) and " +
            "(date between ?4 and ?5) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCasePatternTemperatureNull(
            String pattern,
            LocalTime s_time,
            LocalTime e_time,
            LocalDate s_date,
            LocalDate e_date);

    //    มีแค่เวลา
    @Query(value = "select * from history_scanner " +
            "where (maskpattern = ?1) and " +
            "(time between ?2 and ?3) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCasePatternTimeOnly(String pattern, LocalTime s_time, LocalTime e_time);

    //    มีแค่วันที่
    @Query(value = "select * from history_scanner " +
            "where (maskpattern = ?1) and " +
            "(date between ?2 and ?3) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCasePatternDateOnly(String pattern, LocalDate s_date, LocalDate e_date);

    //    มีแค่อุณหภูมิ
    @Query(value = "select * from history_scanner " +
            "where (maskpattern = ?1) and " +
            "(temperature between ?2 and ?3) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCasePatternTemperatureOnly(String pattern, float s_temperature, float e_temperature);

    //    รูปแบบและเวลาเป็น null
    @Query(value = "select * from history_scanner " +
            "where (date between ?1 and ?2) and " +
            "(temperature between ?3 and ?4) " +
            "order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCaseNotPatternAndTimeNull(
            LocalDate s_date,
            LocalDate e_date,
            float s_temperature,
            float e_temperature);

    //    รูปแบบและวันที่เป็น null
    @Query(value = "select * from history_scanner " +
            "where (time between ?1 and ?2) and " +
            "(temperature between ?3 and ?4) order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCaseNotPatternAndDateNull(
            LocalTime s_time,
            LocalTime e_time,
            float s_temperature,
            float e_temperature);

    //    รูปแบบและอุณภูมิเป็น null
    @Query(value = "select * from history_scanner " +
            "where (time between ?1 and ?2) and " +
            "(date between ?3 and ?4) order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCaseNotPatternAndTemperatureNull(
            LocalTime s_time,
            LocalTime e_time,
            LocalDate s_date,
            LocalDate e_date);

    //    มีแค่เวลา
    @Query(value = "select * from history_scanner " +
            "where time between ?1 and ?2 order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCaseNotPatternAndTimeOnly(LocalTime s_time, LocalTime e_time);

    //    มีแค่วันที่
    @Query(value = "select * from history_scanner " +
            "where date between ?1 and ?2 order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCaseNotPatternAndDateOnly(LocalDate s_date, LocalDate e_date);

    //    มีแค่อุณหภูมิ
    @Query(value = "select * from history_scanner " +
            "where temperature between ?1 and ?2 order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageCaseNotPatternAndTemperatureOnly(float s_temperature, float e_temperature);

    //    ส่งมาครบทุกตัวและเลือกทุกรูปแบบ
    @Query(value = "select * from history_scanner " +
            "where (time between ?1 and ?2) and " +
            "(date between ?3 and ?4) and " +
            "(temperature between ?5 and ?6) order by id desc", nativeQuery = true)
    Iterable<HistoryScanner> SearchInformationPageSelectAllAndPatternNull(
            LocalTime s_time,
            LocalTime e_time,
            LocalDate s_date,
            LocalDate e_date,
            float s_temperature,
            float e_temperature);

    @Query(value = "select * from history_scanner where date between ?1 and ?2", nativeQuery = true)
    Iterable<HistoryScanner> ExportPage(LocalDate s_date, LocalDate e_date);
}
