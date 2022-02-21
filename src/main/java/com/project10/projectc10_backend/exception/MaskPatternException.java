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

    public static MaskPatternException PatternNull(){
        return new MaskPatternException("pattern.null");
    }

    public static MaskPatternException DateNull(){
        return new MaskPatternException("date.null");
    }

    public static MaskPatternException StartTimeNull(){
        return new MaskPatternException("s_time.null");
    }

    public static MaskPatternException EndTimeNull(){
        return new MaskPatternException("e_time.null");
    }

    public static MaskPatternException selectWithMaskPatternNull(){
        return new MaskPatternException("selectWithMaskPattern.historyScanners.null");
    }

}
