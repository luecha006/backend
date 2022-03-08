package com.project10.projectc10_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;

@Entity(name = "temperature")
@Data
public class Temperature extends BaseEntity {
    @JsonIgnore
    private String admin_name;
    private float temperature;
}
