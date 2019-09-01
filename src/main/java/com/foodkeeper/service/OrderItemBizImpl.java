package com.foodkeeper.service;

import com.foodkeeper.domain.OrderItem;
import com.foodkeeper.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemBizImpl implements OrderItemBiz {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getOrderItemListByUserId(Long userId) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        List<OrderItem> orderItemList = orderItemRepository.findByUserId(userId);
        // 현재 < 유통기한 < 현재 + 5일
        return orderItemList.stream().filter(i -> i.getSku().getExpiredAt().after(new Date()) && i.getSku().getExpiredAt().before((cal.getTime()))).collect(Collectors.toList());
    }
}