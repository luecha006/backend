package com.project10.projectc10_backend.repository;

import com.project10.projectc10_backend.entity.Admin;
import com.project10.projectc10_backend.entity.HistoryScanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    //CrudRepository<User, String> --> สร้างข้อมูลใน database จาก class User และ id เป็น String (type ของ id ใน class นั้นๆ)

    Optional<Admin> findByUsername(String s);

    //เช็คว่า username ซ้ำมั้ย
    boolean existsByUsername(String username);

    Optional<Admin> deleteByUsername(String username);

    @Query(value = "select COUNT(id) from admin_table ", nativeQuery = true)
    int examineAdmin ();
}
