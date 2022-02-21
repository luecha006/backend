package com.project10.projectc10_backend.exception;

public class MaskPatternException extends BaseException {
    public MaskPatternException(String code) {
        super("maskpattern."+code);
    }

    //
    public static MaskPatternException MaskPatternNull(){
        return new MaskPatternException("write.maskpattern.null");
    }

    public static MaskPatternException TemperatureNull(){
        return new MaskPatternException("write.temperature.null");
    }
}
