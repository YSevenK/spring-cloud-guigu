package com.ysevenk.order.service;


import com.ysevenk.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
