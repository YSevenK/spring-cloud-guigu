package com.ysevenk.order.feign;

import com.ysevenk.order.feign.fallback.ProductFeignClientFallback;
import com.ysevenk.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {

    // 标注在controller层是接收请求
    // 标注在server层是发送请求
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
