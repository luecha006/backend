package com.project10.projectc10_backend.service;

import com.project10.projectc10_backend.entity.Admin;
import com.project10.projectc10_backend.exception.AdminException;
import com.project10.projectc10_backend.model.MFetchAllResponse;
import com.project10.projectc10_backend.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository repository; //เรียกใช้ class UserRepository (สร้าง object)
    private final PasswordEncoder passwordEncoder;

    // สร้าง เวลาและวันที่ ที่ทำการ save
    private LocalDate date = LocalDate.now();
    private LocalTime time = LocalTime.now();

    public AdminService(AdminRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean deleteById(Integer id) { //ลบผู้ใช้ โดยรหัสผ่าน
        repository.deleteById(id);
        return true;
    }

    public boolean updatePassword(String username, String password) throws AdminException { //เปลี่ยนรหัยผ่านใน database
        Optional<Admin> opt = repository.findByUsername(username);
        if (opt.isEmpty()) {
            throw AdminException.notFound();
        }

        Admin admin = opt.get();
        admin.setPassword(passwordEncoder.encode(password));
        repository.save(admin);
        return true;
    }

    public Optional<Admin> findByUsername(String username) { // functin ตรวจสอบ username --> login adminAPI
        return repository.findByUsername(username);
    }

    public boolean matchPassword(String rawPassword, String enCdedPassword) {
        //function ตรวจเช็คว่า password ตรงกันมั้ย

        return passwordEncoder.matches(rawPassword, enCdedPassword); // matches จะตรวจเช็คแบบถอดรหัส

    }

    public Admin create(String username, String password, String type_admin) throws AdminException {
        // class สำหรับ set ข้อมูลที่จะสร้างลง database --> register adminAPI

        //validate ตรวจเช็คก่อนว่าค่าที่ส่งมาตรงตามที่กำหนดมั้ยเพื่อตรวจจับ
        if (Objects.isNull(username)) {
            throw AdminException.createUsernameNull();
        }

        if (Objects.isNull(password)) {
            throw AdminException.createPasswordNull();
        }


        //verify เช็คว่ามีการซ้ำมั้ย
        if (repository.existsByUsername(username)) {
            throw AdminException.createUsernameDuplicated();
        }


        //save บันทึกค่k
        Admin entity = new Admin(); //สร้าง object จากคลาส User
        entity.setDate(this.date);
        entity.setTime(this.time);
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setType_admin(type_admin);

        return repository.save(entity);
    }

    public List<Admin> fetchAll() {  //ดึงข้อมูลจาก database
        List<Admin> allAdmin = new ArrayList<>();
        Iterable<Admin> admins = repository.findAll();

        for (Admin admin: admins){
            allAdmin.add(admin);
        }
        return allAdmin;
    }
}
