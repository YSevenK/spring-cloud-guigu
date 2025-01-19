package com.ysevenk.order.bean;

import com.ysevenk.product.bean.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private String nickname;
    private List<Product> productList;
    private String address;
}
