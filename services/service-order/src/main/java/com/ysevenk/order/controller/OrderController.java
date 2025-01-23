package com.ysevenk.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ysevenk.order.bean.Order;
import com.ysevenk.order.properties.OrderProperties;
import com.ysevenk.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope   //自动刷新nacos配置
@RequestMapping("/api/order")
@Slf4j
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

    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Order seckill(@RequestParam("userId") Long userId,
                         @RequestParam("productId") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public Order seckillFallback(Long userId, Long productId, BlockException exception) {
        System.out.println("seckillFallback...");
        Order order = new Order();
        order.setId(productId);
        order.setUserId(userId);
        order.setAddress("异常现象: " + exception.getClass());
        return order;
    }

    @GetMapping("/writeDb")
    public String writeDb() {
        return "write DB success!";
    }

    @GetMapping("/readDb")
    public String readDb() {
        log.info("readDb...");
        return "read DB success!";
    }
}
