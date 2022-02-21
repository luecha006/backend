package com.project10.projectc10_backend.api;

import com.project10.projectc10_backend.businass.MaskPatternBusinass;
import com.project10.projectc10_backend.exception.BaseException;
import com.project10.projectc10_backend.model.MFetchAllPatternResponse;
import com.project10.projectc10_backend.model.MMaskPatternRequest;
import com.project10.projectc10_backend.model.MMaskPatternResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facemask")
public class MaskPatternAPI {

    private MaskPatternBusinass businass;

    public MaskPatternAPI(MaskPatternBusinass businass) {
        this.businass = businass;
    }

    @PostMapping
    @RequestMapping("/writemaskpattern")//ผ่าน
    public ResponseEntity<MMaskPatternResponse> WriteMaskPattern(@RequestBody MMaskPatternRequest request) throws BaseException {

        MMaskPatternResponse response = businass.WriteMaskPattern(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @RequestMapping("/fetchAllMaskPattern")//ผ่าน
    public ResponseEntity<List<MFetchAllPatternResponse>> fetchAllMaskPattern() throws BaseException{

       return ResponseEntity.ok(businass.fetchAllMaskPattern());
    }
}
