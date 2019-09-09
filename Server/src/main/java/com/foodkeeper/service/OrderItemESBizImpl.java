package com.foodkeeper.service;

import com.foodkeeper.domain.OrderItemESDto;
import com.foodkeeper.repository.OrderItemESRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderItemESBizImpl implements OrderItemESBiz {

    @Autowired
    private OrderItemESRepository orderItemESRepository;

    @Override
    public List<OrderItemESDto> getOrderItemListByUserId(Long userId) {
        return orderItemESRepository.findByUserIdAndNoti(userId, true);
    }

    @Override
    public void save(OrderItemESDto orderItemESDto) {
        orderItemESRepository.save(orderItemESDto);
    }

    @Override
    public List<OrderItemESDto> findAll() {
        return Lists.newArrayList(orderItemESRepository.findAll());
    }
}
