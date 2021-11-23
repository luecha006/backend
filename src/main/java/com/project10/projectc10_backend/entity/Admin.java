package com.project10.projectc10_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity(name = "admin_table")
public class Admin extends BaseEntity {

//    @JsonIgnore //ห้ามส่งกลับไป
    @Column(nullable = false)
    private LocalDate date;

//    @JsonIgnore //ห้ามส่งกลับไป
    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false, unique = true) //unique = true => ห้ามซ้ำกัน
    private String username;

//    @JsonIgnore //ห้ามส่งกลับไป
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String type_admin;

}
