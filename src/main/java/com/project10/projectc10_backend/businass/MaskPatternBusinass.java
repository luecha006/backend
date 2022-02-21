package com.project10.projectc10_backend.businass;

import com.project10.projectc10_backend.entity.HistoryScanner;
import com.project10.projectc10_backend.exception.BaseException;
import com.project10.projectc10_backend.exception.MaskPatternException;
import com.project10.projectc10_backend.mapper.MaskPatternMapper;
import com.project10.projectc10_backend.model.MFetchAllPatternResponse;
import com.project10.projectc10_backend.model.MMaskPatternRequest;
import com.project10.projectc10_backend.model.MMaskPatternResponse;
import com.project10.projectc10_backend.model.MTestMaskPatternRequest;
import com.project10.projectc10_backend.service.MaskPatternService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaskPatternBusinass {

    private final MaskPatternService maskPatternService;
    private final MaskPatternMapper maskMapper;

    public MaskPatternBusinass(MaskPatternService maskPatternService, MaskPatternMapper maskMapper) {
        this.maskPatternService = maskPatternService;
        this.maskMapper = maskMapper;
    }

    public MMaskPatternResponse WriteMaskPattern(MMaskPatternRequest request) throws BaseException {

        HistoryScanner historyScanner = maskPatternService.create(request.getDate(), request.getTime(), request.getMaskpattern(), request.getTemperature());

        return maskMapper.toMMaskPatternResponse(historyScanner);
    }

    public Iterable<HistoryScanner> fetchAllMaskPattern() {
        return maskPatternService.fetchAllMaskPattern();
    }
//--------------------------------
//    public List<MFetchAllPatternResponse> selectWithMaskPattern(MTestMaskPatternRequest pattern) {
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
    public Iterable<HistoryScanner> selectWithMaskPattern(MTestMaskPatternRequest pattern) throws MaskPatternException {
        List<MFetchAllPatternResponse> allPattern = new ArrayList<>();

        Iterable<HistoryScanner> historyScanners = maskPatternService.selectWithMaskPattern(
                pattern.getPattern(),
                pattern.getS_value(),
                pattern.getE_value());

        return historyScanners;

    }
}
