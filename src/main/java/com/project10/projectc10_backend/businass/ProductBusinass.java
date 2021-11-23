package com.project10.projectc10_backend.businass;

import com.project10.projectc10_backend.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusinass {

    public String getProductByID(String id) throws ProductException {
        //TODO : get data from database

        if(Objects.equals("1234", id)){
            //การตรวจสอบข้อมูลที่ได้รับมาจาก http -> id ซึ่งจริงต้องเอาไปเช็คกับทาง database
            throw ProductException.notFount();
        }
        return id;
    }
}
