package com.project10.projectc10_backend.api;

import com.project10.projectc10_backend.businass.ProductBusinass;
import com.project10.projectc10_backend.exception.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductAPI {

    private final ProductBusinass productBusinass;

    public ProductAPI(ProductBusinass productBusinass) {
        this.productBusinass = productBusinass;
    }

    @GetMapping("/{id}")
    // เหมาะที่จะใช้กับการ login
    public ResponseEntity<String> getProductByID(@PathVariable("id") String id) throws ProductException {
        String response  = productBusinass.getProductByID(id);
        return ResponseEntity.ok(response);
    }
}
