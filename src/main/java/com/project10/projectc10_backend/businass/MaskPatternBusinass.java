package com.project10.projectc10_backend.businass;

import com.project10.projectc10_backend.entity.HistoryScanner;
import com.project10.projectc10_backend.exception.BaseException;
import com.project10.projectc10_backend.mapper.MaskPatternMapper;
import com.project10.projectc10_backend.model.MFetchAllPatternResponse;
import com.project10.projectc10_backend.model.MMaskPatternRequest;
import com.project10.projectc10_backend.model.MMaskPatternResponse;
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

        HistoryScanner historyScanner = maskPatternService.create(request.getMaskpattern(), request.getTemperature());

        return maskMapper.toMMaskPatternResponse(historyScanner);
    }

    public List<MFetchAllPatternResponse> fetchAllMaskPattern() {

        List<MFetchAllPatternResponse> allPattern = new ArrayList<>();

        Iterable<HistoryScanner> historyScanners = maskPatternService.fetchAllMaskPattern();


        for (HistoryScanner h : historyScanners) { //ทำ mapping จาก Admin -> MFetchAllResponse
            allPattern.add((MFetchAllPatternResponse) maskMapper.toMFetchAllPatternResponse(h));
        }

        if (allPattern.isEmpty()) {
            return null;
//            throw AdminException.fetchAllAdmin();
        } else {
            return allPattern;    //ส่งรายการไปให้ api
        }
    }
}
