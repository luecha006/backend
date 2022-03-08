package com.project10.projectc10_backend.businass;

import com.project10.projectc10_backend.exception.TemperatureException;
import com.project10.projectc10_backend.model.MExtractTemperatureRequest;
import com.project10.projectc10_backend.service.TemperatureService;
import org.springframework.stereotype.Service;

@Service
public class TemperatureBusinass {
    private final TemperatureService temperatureService;

    public TemperatureBusinass(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    public float SaveTemperature(MExtractTemperatureRequest request) throws TemperatureException {
        return temperatureService.SaveTemperature(request.getAdmin_name(), request.getTemperature());
    }

    public float ExtractTemperature(){
        return temperatureService.ExtractTemperature();
    }
}
