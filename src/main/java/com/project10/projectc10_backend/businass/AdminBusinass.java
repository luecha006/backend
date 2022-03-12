package com.project10.projectc10_backend.businass;

import com.project10.projectc10_backend.entity.Admin;
import com.project10.projectc10_backend.exception.AdminException;
import com.project10.projectc10_backend.mapper.AdminMapper;
import com.project10.projectc10_backend.model.*;
import com.project10.projectc10_backend.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminBusinass {

    private final AdminService adminService;
    private final AdminMapper adminMapper;

    public AdminBusinass(AdminService adminService, AdminMapper adminMapper) {
        this.adminService = adminService;
        this.adminMapper = adminMapper;
    }

    public boolean chasngePassword(MAdminChangePasswordRequest request) throws AdminException {
        boolean token = adminService.updatePassword(request.getUsername(), request.getPassword());

        return token;
    }

    public boolean delete(MAdminDeleteRequest request) throws AdminException { //delect of username --> ผ่าน
        Optional<Admin> opt = adminService.findByUsername(request.getUsername());
        if (opt.isEmpty()) {
            throw AdminException.notFound();
        }

        Admin admin = opt.get();
        boolean token = adminService.deleteById(admin.getId()); //ส่ง id ไปให้ Function deleteById
        return token; // ถ้าผ่านส่ง true กลับไป
    }

    public MAdminLoginResponse login(MAdminLoginRequest request) throws AdminException {

        //verifi database ตรวจสอบใน database
        Optional<Admin> opt = adminService.findByUsername(request.getUsername()); //ส่ง username ไปให้ function findByUsername

        if (opt.isEmpty()) { //ถ้าไม่เจอ
            //throw login fail username not fount
            throw AdminException.loginFailUsernameNotFound();
        }

        // ถ้าเจอ น่าจะใช่ else
        Admin admin = opt.get();
        if (!adminService.matchPassword(request.getPassword(), admin.getPassword())) { //password ตรง = true --> !true
            // request.getPassword() = password ที่ส่งมา, admin.getPassword() = password ที่อยู่ใน database

            //throw login fail password not incorrect
            throw AdminException.loginFailPasswordIncorrect();
        }
        // password ตรงกันส่งค่า true กลับไป
        return adminMapper.toMLoginResponse(admin);

    }

    public MAdminRegisterResponse register(MAdminRegisterRequest request) throws AdminException {

        Admin admin = adminService.create(request.getDate(), request.getTime(), request.getUsername(), request.getPassword(), request.getType_admin());

        return adminMapper.toMRegisterResponse(admin);
    }

    public List<MFetchAllResponse> fetchAll() throws AdminException {
        List<MFetchAllResponse> allAdmin = new ArrayList<>();
        Iterable<Admin> admins = adminService.fetchAll();

        for (Admin admin : admins) { //ทำ mapping จาก Admin -> MFetchAllResponse
            allAdmin.add((MFetchAllResponse) adminMapper.toMFetchAllResponse(admin));
        }

        if (allAdmin.isEmpty()) {
            return null;
        } else {
            return allAdmin;    //ส่งรายการไปให้ api
        }
    }


    public int examineAdmin(){
        return adminService.examineAdmin();
    }
}
