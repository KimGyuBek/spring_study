package com.example.testproject.data.repository;


import com.example.testproject.data.entity.ProductEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void generateData() {
        int count = 1;

        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 3000, 9000));
        productRepository.save(getProduct(Integer.toString(--count), count = count + 2, 1500, 200));
        productRepository.save(getProduct(Integer.toString(count), count++, 4000, 5000));
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1500));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 1000));
        productRepository.save(getProduct(Integer.toString(count), count++, 500, 10000));
        productRepository.save(getProduct(Integer.toString(count), count++, 8500, 3500));
        productRepository.save(getProduct(Integer.toString(count), count++, 7200, 2000));
        productRepository.save(getProduct(Integer.toString(count), count++, 5100, 1700));

    }

    private ProductEntity getProduct(String id, int nameNumber, int price, int stock) {
        System.out.println("generate!");
        return new ProductEntity(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest() {
        List<ProductEntity> foudAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");

        for (ProductEntity productEntity : foudAll) {
            System.out.println(productEntity.toString());
        }

        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foudEntities = productRepository.findByProductName("상품4");

        for (ProductEntity productEntity : foudEntities) {
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> queryEntities = productRepository.queryByProductName("상품4");

        for (ProductEntity productEntity : queryEntities) {
            System.out.println(productEntity.toString());
        }
    }

    @Test
    void existTest() {
        List<ProductEntity> foundAll = productRepository.findAll();

        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.existsByProductName("상품4"));
    }

    @Test
    void countTest() {
        List<ProductEntity> foundAll = productRepository.findAll();

        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.countByProductName("상품4"));
    }

    @Test
    @Transactional
    void deleteTest() {
        System.out.println("before : " + productRepository.count());

        productRepository.deleteByProductId("1");
        productRepository.removeByProductId("9");

        System.out.println("after : " + productRepository.count());

    }

    @Test
    void topTest() {
        productRepository.save(getProduct("109", 123, 1500, 5000));
        productRepository.save(getProduct("101", 123, 2500, 5000));
        productRepository.save(getProduct("102", 123, 3500, 5000));
        productRepository.save(getProduct("103", 123, 4500, 5000));
        productRepository.save(getProduct("104", 123, 1000, 5000));
        productRepository.save(getProduct("105", 123, 2000, 5000));
        productRepository.save(getProduct("106", 123, 3000, 5000));
        productRepository.save(getProduct("107", 123, 4000, 5000));

        List<ProductEntity> foundentities = productRepository.findFirst5ByProductName("상품123");

        for (ProductEntity productEntity : foundentities) {
            System.out.println(productEntity.toString());
        }

        List<ProductEntity> foundEntities2 = productRepository.findTop3ByProductName("상품123");

        for (ProductEntity productEntity : foundEntities2) {
            System.out.println(productEntity.toString());
        }
    }

//    조건자 키워드 테스트

    @Test
    void isEqualTest() {
        List<ProductEntity> foundAll = productRepository.findAll();

        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByProductIdEquals("1"));

        System.out.println(productRepository.findByProductIdIs("1"));
    }

//    정렬과 페이징

    @Test
    void orderByTest() {

        List<ProductEntity> foundAll = productRepository.findAll();

        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductStockAsc(
            "상품");
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductNameContainingOrderByProductStockDesc("상품");
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

        foundProducts = productRepository.findByProductNameContainingOrderByProductPriceAscProductIdDesc(
            "상품");
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);
        }

    }

    @Test
    void orderByWithParameterTest() {

        List<ProductEntity> foundAll = productRepository.findAll();

        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContaining("상품",
            Sort.by(
                Order.asc("productPrice")));
        for (ProductEntity productEntity : foundProducts) {
            System.out.println(productEntity);

        }
    }


//    @Query 사용하기

    @Test
    void queryTest() {
        List<ProductEntity> foundAll = productRepository.findAll();

        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> fondProduct = productRepository.findByProductBasis();
        for(ProductEntity productEntity : fondProduct) {
            System.out.println(productEntity.toString());
        }

    }


    @Test
    void queryTests2() {
        List<ProductEntity> foundAll = productRepository.findAll();

        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity productEntity : foundAll) {
            System.out.println(productEntity.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProduct  = productRepository.findTest(1, 1);
        for (ProductEntity productEntity : foundProduct) {
            System.out.println(productEntity.toString());
        }

    }

}
