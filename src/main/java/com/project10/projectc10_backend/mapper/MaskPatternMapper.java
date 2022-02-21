package com.project10.projectc10_backend.mapper;

import com.project10.projectc10_backend.entity.HistoryScanner;
import com.project10.projectc10_backend.model.MFetchAllPatternResponse;
import com.project10.projectc10_backend.model.MMaskPatternResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaskPatternMapper {

    MMaskPatternResponse toMMaskPatternResponse(HistoryScanner historyScanner);

    MFetchAllPatternResponse toMFetchAllPatternResponse(HistoryScanner historyScanner);


}
