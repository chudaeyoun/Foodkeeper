package com.foodkeeper.service;

import com.foodkeeper.domain.Order;

import java.util.List;

public interface OrderBiz {
    List<Order> getOrderListByUserId(Long userId);
}
