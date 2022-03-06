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
        Iterable<HistoryScanner> maskPatterns = repository.findAll();

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

    public Iterable<HistoryScanner> selectHomePageDateCurrent(){
        LocalDate currentDay = LocalDate.now();
        Iterable<HistoryScanner> maskPatterns = repository.selectHomePage(currentDay);
        return maskPatterns;
    }
}
