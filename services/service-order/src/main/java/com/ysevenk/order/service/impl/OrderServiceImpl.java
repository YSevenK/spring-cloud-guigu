package com.ysevenk.order.service.impl;

import com.ysevenk.order.bead.Order;
import com.ysevenk.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        order.setId(1L);
        // TODO 总金额
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(userId);
        order.setNickname("zsan");
        order.setAddress("成都");
        // TODO 远程调用 查询商品列表
        order.setProductList(null);
        return order;
    }
}
