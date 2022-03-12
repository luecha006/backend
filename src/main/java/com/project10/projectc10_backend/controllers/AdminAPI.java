package com.project10.projectc10_backend.controllers;

import com.project10.projectc10_backend.businass.AdminBusinass;
import com.project10.projectc10_backend.exception.AdminException;
import com.project10.projectc10_backend.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin") //ถ้าอยู่บนหัว class แสดงว่าทุกๆ api จะต้องขึ้นต้นด้วย path นี้
class AdminAPI {

    private final AdminBusinass businass; //สร้าง object ของ class TestBusinass

    public AdminAPI(AdminBusinass businass) {
        this.businass = businass;
    }

    @PostMapping("/register") //function ลงทะเบียน ,add Admin --> ผ่าน
    public ResponseEntity<MAdminRegisterResponse> register(@RequestBody MAdminRegisterRequest request) throws AdminException {
        //ResponseEntity เป็นการห่อ object โดยถ้าการประมวลผลสำเร็จมันจะ return http กลับเป็น 200
        //ลงทะเบียนโดยมีข้อมูล โครงสร้างตาม Class MAdminRegisterRequest

        MAdminRegisterResponse response = businass.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login") //--> ผ่าน
    public ResponseEntity<MAdminLoginResponse> login(@RequestBody MAdminLoginRequest request) throws AdminException { //function login
        MAdminLoginResponse response = businass.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/changepassword") //--> ผ่าน
    public ResponseEntity<Boolean> changepassword(@RequestBody MAdminChangePasswordRequest request) throws AdminException {
        boolean response = businass.chasngePassword(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete") //--> ผ่าน
    public ResponseEntity<Boolean> deleteAdmin(@RequestBody MAdminDeleteRequest request) throws AdminException {
        boolean response = businass.delete(request);

        return ResponseEntity.ok(response);
    }

    // ดึงข้อมูล
    @GetMapping("/fetchAllAdmin") //--> ผ่าน
    public List<MFetchAllResponse> fetchAllAdmin() throws AdminException {

        return businass.fetchAll();
    }

    @GetMapping("/examineadmin")
    public ResponseEntity<Integer> examineAdmin(){
        return ResponseEntity.ok(businass.examineAdmin());
    }
}
