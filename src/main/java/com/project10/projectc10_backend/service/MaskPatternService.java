package com.project10.projectc10_backend.service;

import com.project10.projectc10_backend.ConvertDateTime.ConvertStringToLocalDateTime;
import com.project10.projectc10_backend.entity.HistoryScanner;
import com.project10.projectc10_backend.exception.BaseException;
import com.project10.projectc10_backend.exception.MaskPatternException;
import com.project10.projectc10_backend.repository.MaskPatternRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class MaskPatternService {

    private final ConvertStringToLocalDateTime convertDateTime = new ConvertStringToLocalDateTime();
    private final MaskPatternRepository repository;

    public MaskPatternService(MaskPatternRepository repository) {
        this.repository = repository;
    }

    public HistoryScanner create(String date, String time, String maskPattern, float temperature) throws BaseException {

        if (Objects.isNull(maskPattern)) {
            throw MaskPatternException.MaskPatternNull();
        }

        if (Objects.isNull(temperature)) {
            throw MaskPatternException.TemperatureNull();
        }

        HistoryScanner entity = new HistoryScanner();

        entity.setDate(convertDateTime.ConvertStringToLocalDate(date));
        entity.setTime(convertDateTime.ConvertStringToLocalTime(time));
        entity.setMaskpattern(maskPattern);
        entity.setTemperature(temperature);

        return repository.save(entity);
    }


    public List<HistoryScanner> fetchAllMaskPattern() {
        List<HistoryScanner> allPattern = new ArrayList<>();
        Iterable<HistoryScanner> maskPatterns = repository.DisplayTablePageAngSearchInformationPageAllNull();

        for (HistoryScanner historyScanner : maskPatterns) {
            allPattern.add(historyScanner);
        }
        return allPattern;
    }

    public Iterable<HistoryScanner> selectWithMaskPattern(String pattern, String s_value, String e_value) throws MaskPatternException {
        Iterable<HistoryScanner> maskPatterns = new HashSet<>();

        if (Objects.isNull(pattern)) {
            throw MaskPatternException.PatternNull();
        }

        if (Objects.isNull(s_value)) {
            throw MaskPatternException.StartTimeNull();
        }

        if (Objects.isNull(e_value)) {
            throw MaskPatternException.EndTimeNull();
        }

        // รูปแบบที่1 (0) จะส่งวันที่ปัจจุบันมาด้วยเพื่อใช้ Query
        if (pattern.equals("0")) {  //ผ่าน

            // สร้างวันที่ปัจจุบันให้ไป query
            LocalDate date = LocalDate.now();
            LocalTime s_time = convertDateTime.ConvertStringToLocalTime(s_value);
            LocalTime e_time = convertDateTime.ConvertStringToLocalTime(e_value);

            maskPatterns = repository.selectHomepageCurrentDay(s_time, e_time, date);

        } else if (pattern.equals("1") || pattern.equals("2")) {
            LocalDate s_date = convertDateTime.ConvertStringToLocalDate(s_value);
            LocalDate e_date = convertDateTime.ConvertStringToLocalDate(e_value);

            maskPatterns = repository.findByHomepageWeeklyANDMonthly(s_date, e_date);

        }

        return maskPatterns;
    }

    public Iterable<HistoryScanner> selectHomePageDateCurrent() {
        LocalDate currentDay = LocalDate.now();
        Iterable<HistoryScanner> maskPatterns = repository.selectHomePage(currentDay);
        return maskPatterns;
    }

    public Iterable<HistoryScanner> selectSearchInformationPage(
            String pattern,
            LocalTime s_time,
            LocalTime e_time,
            LocalDate s_date,
            LocalDate e_date,
            float s_temperature,
            float e_temperature) {

        Iterable<HistoryScanner> maskPatterns = new HashSet<>();

        if (pattern.equals("0")) {
            if ((s_time == null && e_time == null) && (s_date == null && e_date == null) && (s_temperature == 0.0 && e_temperature == 0.0)) {
                maskPatterns = repository.DisplayTablePageAngSearchInformationPageAllNull();
            } else if ((s_time != null && e_time != null) && (s_date != null && e_date != null) && (s_temperature != 0.0 && e_temperature != 0.0)) {
                maskPatterns = repository.SearchInformationPageSelectAllAndPatternNull(
                        s_time,
                        e_time,
                        s_date,
                        e_date,
                        s_temperature,
                        e_temperature);
            } else if ((s_time == null && e_time == null) && (s_date == null && e_date == null)) {
                maskPatterns = repository.SearchInformationPageCaseNotPatternAndTemperatureOnly(s_temperature, e_temperature);
            } else if ((s_time == null && e_time == null) && (s_temperature == 0.0 && e_temperature == 0.0)) {
                maskPatterns = repository.SearchInformationPageCaseNotPatternAndDateOnly(s_date, e_date);
            } else if ((s_date == null && e_date == null) && (s_temperature == 0.0 && e_temperature == 0.0)) {
                maskPatterns = repository.SearchInformationPageCaseNotPatternAndTimeOnly(s_time, e_time);
            } else if (s_time == null && e_time == null) {
                maskPatterns = repository.SearchInformationPageCaseNotPatternAndTimeNull(s_date, e_date, s_temperature, e_temperature);
            } else if (s_date == null && e_date == null) {
                maskPatterns = repository.SearchInformationPageCaseNotPatternAndDateNull(s_time, e_time, s_temperature, e_temperature);
            } else if (s_temperature == 0.0 && e_temperature == 0.0) {
                maskPatterns = repository.SearchInformationPageCaseNotPatternAndTemperatureNull(s_time, e_time, s_date, e_date);
            }

        } else {
            if ((s_time != null && e_time != null) && (s_date != null && e_date != null) && (s_temperature != 0.0 && e_temperature != 0.0)) {
                maskPatterns = repository.SearchInformationPagePatternAllValue(
                        pattern,
                        s_time,
                        e_time,
                        s_date,
                        e_date,
                        s_temperature,
                        e_temperature);
            } else if ((s_time == null && e_time == null) && (s_date == null && e_date == null) && (s_temperature == 0.0 && e_temperature == 0.0)) {
                maskPatterns = repository.SearchInformationPageCasePatternAllNull(pattern);
            } else if ((s_date == null && e_date == null) && (s_temperature == 0.0 && e_temperature == 0.0)) {
                maskPatterns = repository.SearchInformationPageCasePatternTimeOnly(pattern, s_time, e_time);
            } else if ((s_time == null && e_time == null) && (s_temperature == 0.0 && e_temperature == 0.0)) {
                maskPatterns = repository.SearchInformationPageCasePatternDateOnly(pattern, s_date, e_date);
            } else if ((s_time == null && e_time == null) && (s_date == null && e_date == null)) {
                maskPatterns = repository.SearchInformationPageCasePatternTemperatureOnly(pattern, s_temperature, e_temperature);
            } else if (s_time == null && e_time == null) {
                maskPatterns = repository.SearchInformationPageCasePatternTimeNull(
                        pattern,
                        s_date,
                        e_date,
                        s_temperature,
                        e_temperature);
            } else if (s_date == null && e_date == null) {
                maskPatterns = repository.SearchInformationPageCasePatternDateNull(
                        pattern,
                        s_time,
                        e_time,
                        s_temperature,
                        e_temperature);
            } else if (s_temperature == 0.0 && e_temperature == 0.0) {
                maskPatterns = repository.SearchInformationPageCasePatternTemperatureNull(
                        pattern,
                        s_time,
                        e_time,
                        s_date,
                        e_date);
            }
        }

        return maskPatterns;
    }

    public Iterable<HistoryScanner> ExportPage(String s_value, String e_value){
        LocalDate s_date = convertDateTime.ConvertStringToLocalDate(s_value);
        LocalDate e_date = convertDateTime.ConvertStringToLocalDate(e_value);

        Iterable<HistoryScanner> maskPatterns = repository.ExportPage(s_date, e_date);

        return maskPatterns;
    }
}
