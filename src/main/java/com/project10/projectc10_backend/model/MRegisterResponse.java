package com.project10.projectc10_backend.model;

import lombok.Data;

@Data
public class MRegisterResponse {
    //คลาสนี้จะ Mapping กับ admin ว่าต้องการที่จะส่งอะไรกลับไปให้ผู้ใช้งานบ้างต่ม data ของคลาสนี้

    private String username;

    private String type_admin;
}