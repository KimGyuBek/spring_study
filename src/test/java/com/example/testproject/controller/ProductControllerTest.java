package com.example.testproject.controller;


import com.example.testproject.data.dto.ProductDto;
import com.example.testproject.service.impl.ProductServiceImpl;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
//@AutoConfigureWebMvc //이 어노테이션을 통해 MockMvc를 Builder 없이 주입받을 수 있음
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean //ProductController에서 잡고있는 Bean객체에 대해 Mock 형태의 객체를 생성해줌
    ProductServiceImpl productService;

//    @BeforeAll
//    static void beforeAll() {
//        given(productService.getProduct("12345")).willReturn(
//                new ProductDto("12334", "pen", 5000, 200));
//
//    }

//    http://localhost:8181/api/v1/product-api/product/{productId}
    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
//    @Disabled
    void getProductTest() throws Exception {

//        given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메서드
        given(productService.getProduct("12345")).willReturn(
                new ProductDto("12334", "pen", 5000, 200));

        String productId = "12345";

//        andExpect : 기대하는 값이 나왔는지 체크해볼 수 있는 메서드
        mockMvc.perform(
                get("/api/v1/product-api/product/" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

//        verify : 해당 객체의 메서드가 실행되었는지 체크해줌
        verify(productService).getProduct("12345");
    }


//    http://localhost:8181/api/v1/product-api/product
    @Test
    @DisplayName("Product 데이터 생성 테스트")
//    @Disabled
    void createProductTest() throws Exception {

//        Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 떄문에 아래와 같이 가정 사항을 만들어줌
        given(productService.saveProduct("12334", "pen", 5000, 200))
                .willReturn(
                        new ProductDto("12334", "pen", 5000, 200));


        ProductDto productDto = ProductDto.builder().productId("12334")
                .productName("pen")
                .productPrice(5000)
                .productStock(200)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

//        아래로 대체 가능
//        String json = new ObjectMapper().writeValueAsString(productDto);

        mockMvc.perform(
                post("/api/v1/product-api/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

        verify(productService).saveProduct("12334", "pen", 5000, 200);
    }


}
