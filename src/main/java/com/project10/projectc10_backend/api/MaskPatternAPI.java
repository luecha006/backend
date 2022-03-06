package com.project10.projectc10_backend.api;

import com.project10.projectc10_backend.businass.MaskPatternBusinass;
import com.project10.projectc10_backend.entity.HistoryScanner;
import com.project10.projectc10_backend.exception.BaseException;
import com.project10.projectc10_backend.exception.MaskPatternException;
import com.project10.projectc10_backend.model.MHomePageSelectMaskPatternRequest;
import com.project10.projectc10_backend.model.MWriteMaskPatternRequest;
import com.project10.projectc10_backend.model.MMaskPatternResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facemask")
public class MaskPatternAPI {

    private MaskPatternBusinass businass;

    public MaskPatternAPI(MaskPatternBusinass businass) {
        this.businass = businass;
    }

    @PostMapping
    @RequestMapping("/writemaskpattern")//บันทึกข้อมูบการสแกนลง database --> ผ่าน
    public ResponseEntity<MMaskPatternResponse> WriteMaskPattern(@RequestBody MWriteMaskPatternRequest request) throws BaseException {

        MMaskPatternResponse response = businass.WriteMaskPattern(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequestMapping("/fetchAllMaskPattern")// เรียกดูข้อมูลการสแกนทั้งหมด --> ผ่าน
    public ResponseEntity<Iterable<HistoryScanner>> fetchAllMaskPattern() throws BaseException {
        return ResponseEntity.ok(businass.fetchAllMaskPattern());
    }

    @PostMapping
    @RequestMapping("/selectWithMaskPattern") //เลือกข้อมูลจากหน้า homepage --> ผ่าน
    public Iterable<HistoryScanner> selectWithMaskPattern(@RequestBody MHomePageSelectMaskPatternRequest request) throws MaskPatternException {
        return businass.selectWithMaskPattern(request);
    }

    @GetMapping
    @RequestMapping("/selectHomePageDateCurrent")
    public Iterable<HistoryScanner> selectHomePageDateCurrent(){
        return businass.selectHomePageDateCurrent();
    }

    @PostMapping
    @RequestMapping("/selectHomePageMonthly")
    public void selectSearchInformationPage(){

    }
}
