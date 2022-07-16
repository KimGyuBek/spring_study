package com.example.testproject.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "product") //Entity를 기반으로 디비에 테이블을 자동으로 생성할건데 이 테이블의 이름을 어떻게 할 건지
public class ProductEntity extends BaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String productId;
    String productName;
    Integer productPrice;
    Integer productStock;


}
