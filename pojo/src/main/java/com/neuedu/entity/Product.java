package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @program: parentshop
 * @description: 商品实体类
 * @author: Linluo
 * @create: 2019-10-08 18:17
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private Integer id;
    private String productName;
    private String productDescription;
    private BigDecimal normalPrice;
    private BigDecimal memberPrice;
    private Timestamp productDate;
    private Category category;
    private String productImgPath;

    public Product(String productName, String productDescription, BigDecimal normalPrice, Category category, String productImgPath) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.normalPrice = normalPrice;
        this.memberPrice = normalPrice.multiply(BigDecimal.valueOf(0.8));
        this.category = category;
        this.productImgPath = productImgPath;
    }
}
