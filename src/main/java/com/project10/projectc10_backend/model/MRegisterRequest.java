package com.project10.projectc10_backend.model;

import lombok.Data;

@Data
public class MRegisterRequest {
    // ถ้าลงทะเบียน เพิ่ม สมาชิก จะส่ง data มาดังนี้

    private String username;
    private String password;
    private String type_admin;

}