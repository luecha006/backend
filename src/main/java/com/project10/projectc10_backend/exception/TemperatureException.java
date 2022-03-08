package com.project10.projectc10_backend.exception;

public class TemperatureException extends BaseException{
    public TemperatureException(String code) {
        super("temperature."+code);
    }

    public static TemperatureException TemperatureNull(){
        return new TemperatureException("save.temperature.null");
    }

    public static TemperatureException AdminNameNull(){
        return new TemperatureException("save.adminname.null");
    }
}
