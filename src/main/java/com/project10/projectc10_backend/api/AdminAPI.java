package com.project10.projectc10_backend.api;

import com.project10.projectc10_backend.businass.AdminBusinass;
import com.project10.projectc10_backend.entity.Admin;
import com.project10.projectc10_backend.exception.AdminException;
import com.project10.projectc10_backend.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin") //ถ้าอยู่บนหัว class แสดงว่าทุกๆ api จะต้องขึ้นต้นด้วย path นี้
public class AdminAPI {

    private final AdminBusinass businass; //สร้าง object ของ class TastBusinass

    public AdminAPI(AdminBusinass businass) {
        this.businass = businass;
    }

    @PostMapping("/register") //function ลงทะเบียน ,add Admin --> ผ่าน
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws AdminException {
        //ResponseEntity เป็นการห่อ object โดยถ้าการประมวลผลสำเร็จมันจะ return http กลับเป็น 200
        //ลงทะเบียนโดยมีข้อมูล โครงสร้างตาม Class MRegisterRequest

        MRegisterResponse response = businass.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login") //--> ผ่าน
    public ResponseEntity<Boolean> login(@RequestBody MLoginRequest request) throws AdminException { //function login
        boolean response = businass.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/changepassword") //--> ผ่าน
    public ResponseEntity<Boolean> changepassword(@RequestBody MChangePassword request) throws AdminException {
        boolean response = businass.chasngePassword(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete") //--> ผ่าน
    public ResponseEntity<Boolean> deleteAdmin(@RequestBody MDeleteRequest request) throws AdminException {
        boolean respons = businass.delete(request);

        return ResponseEntity.ok(respons);
    }

    // ดึงข้อมูล
    @GetMapping("/fetchAllAdmin") //--> ผ่าน
    public List<MFetchAllResponse> fetchAllAdmin() throws AdminException {

        return businass.fetchAll();
    }
}
