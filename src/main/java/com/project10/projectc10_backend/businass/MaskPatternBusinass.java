package com.project10.projectc10_backend.businass;

import com.project10.projectc10_backend.ConvertDateTime.ConvertStringToLocalDateTime;
import com.project10.projectc10_backend.entity.HistoryScanner;
import com.project10.projectc10_backend.exception.BaseException;
import com.project10.projectc10_backend.exception.MaskPatternException;
import com.project10.projectc10_backend.mapper.MaskPatternMapper;
import com.project10.projectc10_backend.model.*;
import com.project10.projectc10_backend.service.MaskPatternService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MaskPatternBusinass {

    private final MaskPatternService maskPatternService;
    private final MaskPatternMapper maskMapper;

    private final ConvertStringToLocalDateTime convertDateTime = new ConvertStringToLocalDateTime();

    public MaskPatternBusinass(MaskPatternService maskPatternService, MaskPatternMapper maskMapper) {
        this.maskPatternService = maskPatternService;
        this.maskMapper = maskMapper;
    }

    public MMaskPatternResponse WriteMaskPattern(MWriteMaskPatternRequest request) throws BaseException {

        HistoryScanner historyScanner = maskPatternService.create(request.getDate(), request.getTime(), request.getMaskpattern(), request.getTemperature());

        return maskMapper.toMMaskPatternResponse(historyScanner);
    }

    public Iterable<HistoryScanner> fetchAllMaskPattern() {
        return maskPatternService.fetchAllMaskPattern();
    }
//--------------------------------
//    public List<MFetchAllPatternResponse> selectWithMaskPattern(MHomePageSelectMaskPatternRequest pattern) {
//        List<MFetchAllPatternResponse> allPattern = new ArrayList<>();
//
//        List<HistoryScanner> historyScanners = maskPatternService.selectWithMaskPattern(pattern.getE_time());
//
//        for (HistoryScanner h : historyScanners) { //ทำ mapping จาก Admin -> MFetchAllResponse
//            allPattern.add((MFetchAllPatternResponse) maskMapper.toMFetchAllPatternResponse(h));
//        }
//
////        System.out.println("allPattern businass "+allPattern);
//
//        if (historyScanners.isEmpty()) {
//            return null;
////            throw AdminException.fetchAllAdmin();
//        } else {
//            return allPattern;    //ส่งรายการไปให้ api
//        }
//    }


    // ไม่ทำการ mapper ส่งค่าเป็น historyscanner กลับเลย
    public Iterable<HistoryScanner> selectWithMaskPattern(MHomePageSelectMaskPatternRequest request) throws MaskPatternException {
        List<MFetchAllPatternResponse> allPattern = new ArrayList<>();

        Iterable<HistoryScanner> historyScanners = maskPatternService.selectWithMaskPattern(
                request.getPattern(),
                request.getS_value(),
                request.getE_value());

        return historyScanners;

    }

    public Iterable<HistoryScanner> selectHomePageDateCurrent() {
        Iterable<HistoryScanner> historyScanners = maskPatternService.selectHomePageDateCurrent();
        return historyScanners;
    }

    public Iterable<HistoryScanner> selectSearchInformationPage(MSearchInformationMaskPatternRequest request) {
        String pattern = request.getPattern();
        float s_temperature = request.getS_temperature();
        float e_temperature = request.getE_temperature();
        LocalTime s_time = null;
        LocalTime e_time = null;
        LocalDate s_date = null;
        LocalDate e_date = null;

        if (Objects.nonNull(request.getS_time())) {
            s_time = convertDateTime.ConvertStringToLocalTime(request.getS_time());
        }
        if (Objects.nonNull(request.getE_time())) {
            e_time = convertDateTime.ConvertStringToLocalTime(request.getE_time());
        }
        if (Objects.nonNull(request.getS_date())) {
            s_date = convertDateTime.ConvertStringToLocalDate(request.getS_date());
        }
        if (Objects.nonNull(request.getE_date())) {
            e_date = convertDateTime.ConvertStringToLocalDate(request.getE_date());
        }

        Iterable<HistoryScanner> historyScanners = maskPatternService.selectSearchInformationPage(
                pattern,
                s_time,
                e_time,
                s_date,
                e_date,
                s_temperature,
                e_temperature);
        return historyScanners;
    }

    public Iterable<HistoryScanner> ExportPage(MExportPatternRequest request) {
        return maskPatternService.ExportPage(request.getS_month(), request.getE_month());
    }
}
