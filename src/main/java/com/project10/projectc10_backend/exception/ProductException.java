package com.project10.projectc10_backend.exception;

public class ProductException extends BaseException{
    //การ return error ในกรณี มีการส่ง value เป็น id มี
    public ProductException(String code){
        super("product."+code);
    }

    public static ProductException notFount(){
        return new ProductException("not found");
    }
}
