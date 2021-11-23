package com.project10.projectc10_backend.mapper;

import com.project10.projectc10_backend.entity.Admin;
import com.project10.projectc10_backend.model.MFetchAllResponse;
import com.project10.projectc10_backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    //สร้างคลาสที่ mapper จาก admin เป็น MRegisterResponse
    //โดยที่เจาเอา file ที่คลายกันไป map ให้
    MRegisterResponse toMRegisterResponse(Admin admin);

    MFetchAllResponse toMFetchAllResponse (Admin admin);
}
