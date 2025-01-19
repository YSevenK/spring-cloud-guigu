package com.ysevenk.order.service;

import com.ysevenk.order.bead.Order;

public interface OrderService {
    Order createOrder(Long productId,Long userId);
}
