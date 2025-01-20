package com.ysevenk.order.feign.fallback;

import com.ysevenk.order.feign.ProductFeignClient;
import com.ysevenk.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("兜底回调...");
        Product product = new Product();
        product.setId(id);
        product.setPrice(new BigDecimal("0"));
        product.setProductName("未知");
        product.setNum(0);
        return product;
    }
}
