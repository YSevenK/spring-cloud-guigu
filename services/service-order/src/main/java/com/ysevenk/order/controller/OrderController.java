package com.ysevenk.order.controller;

import com.ysevenk.order.bean.Order;
import com.ysevenk.order.properties.OrderProperties;
import com.ysevenk.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope   //自动刷新nacos配置
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String config() {
        return "order.timeout= " + orderProperties.getTimeout()
                + "; order-confirm= " + orderProperties.getAutoConfirm()
                + "order.db_url= " + orderProperties.getDbUrl();
    }

    // 创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        return order;
    }
}
