package com.example.testproject.controller;

import com.example.testproject.common.Constant;
import com.example.testproject.common.exception.TestException;
import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1/product-api")
public class ProductController {
    private final static Logger LOGGER = Logger.getGlobal();

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        ProductDto productDto = productService.getProduct(productId);

        return productDto;
    }



    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

        //Validation check
        //가독성 떨어지고 문제 발생 가능성. 가능한 어노테이션 사용 권장
        if(productDto.getProductId().equals("") || productDto.getProductId().isEmpty()) {

            LOGGER.severe("product is Empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productDto);
        }



        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        ProductDto response = productService
                .saveProduct(productId, productName, productPrice, productStock);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest()  throws TestException {
//        throw new TestException(Constant.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "의도한 에러가 발생했습니다.");
        throw new TestException(Constant.ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN, "접근이 금지되었습니다.");
    }
}
