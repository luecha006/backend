package com.project10.projectc10_backend.repository;

import com.project10.projectc10_backend.entity.Temperature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface TemperatureRepository extends CrudRepository<Temperature, Integer> {

    @Query(value = "select temperature from temperature order by id desc limit 1 ", nativeQuery = true)
    float ExtractTemperature();
}
