package com.project10.projectc10_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    //primary key เป็น id ชนิด int และสร้าง auto
    @Id
    @JsonIgnore //ห้ามส่งกลับไป
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
