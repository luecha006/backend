package com.project10.projectc10_backend.controllers;

import com.project10.projectc10_backend.businass.TemperatureBusinass;
import com.project10.projectc10_backend.exception.TemperatureException;
import com.project10.projectc10_backend.model.MExtractTemperatureRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temperature")
public class TemperatureAPI {

    private final TemperatureBusinass businass;

    public TemperatureAPI(TemperatureBusinass businass) {
        this.businass = businass;
    }

    @PostMapping("/save")
    public ResponseEntity<Float> SaveTemperature(@RequestBody MExtractTemperatureRequest request) throws TemperatureException {
        float response = businass.SaveTemperature(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/extract")
    public ResponseEntity<Float> ExtractTemperature(){
        return ResponseEntity.ok(businass.ExtractTemperature());
    }
}
