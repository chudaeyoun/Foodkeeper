package com.foodkeeper.service;

import com.foodkeeper.domain.Order;
import com.foodkeeper.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderBizImpl implements OrderBiz {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrderListByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}