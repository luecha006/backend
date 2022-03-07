package com.project10.projectc10_backend.model;

import lombok.Data;

@Data
public class MAdminRegisterResponse {
    //คลาสนี้จะ Mapping กับ admin ว่าต้องการที่จะส่งอะไรกลับไปให้ผู้ใช้งานบ้างตาม data ของคลาสนี้

    private String username;
    private String type_admin;
}
