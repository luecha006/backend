package com.project10.projectc10_backend.service;

import com.project10.projectc10_backend.entity.Temperature;
import com.project10.projectc10_backend.exception.TemperatureException;
import com.project10.projectc10_backend.repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TemperatureService {

    private final TemperatureRepository repository;

    public TemperatureService(TemperatureRepository repository) {
        this.repository = repository;
    }

    public float SaveTemperature(String admin_name, float temperature) throws TemperatureException {
        if (Objects.isNull(admin_name)) {
            throw TemperatureException.TemperatureNull();
        }

        if (Objects.isNull(admin_name)) {
            throw TemperatureException.AdminNameNull();
        }

        Temperature entity = new Temperature();
        entity.setAdmin_name(admin_name);
        entity.setTemperature(temperature);
        repository.save(entity);

        return entity.getTemperature();
    }

    public float ExtractTemperature(){
        float temperatures = repository.ExtractTemperature();
        return temperatures;
    }
}
