package com.foodkeeper.service;

import com.foodkeeper.domain.OrderItem;
import com.foodkeeper.domain.OrderItemDto;
import com.foodkeeper.repository.OrderItemRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemBizImpl implements OrderItemBiz {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDto> getOrderItemListByUserId(Long userId) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        List<OrderItem> orderItemList = orderItemRepository.findByUserIdAndNoti(userId, true);
        // 현재 < 유통기한 < 현재 + 5일
        orderItemList.stream().filter(i -> i.getSku().getExpiredAt().after(new Date()) && i.getSku().getExpiredAt().before((cal.getTime()))).collect(Collectors.toList());

        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        List<OrderItemDto> orderItemDtoList = Lists.newArrayList();
        for (OrderItem item : orderItemList) {
            orderItemDtoList.add(OrderItemDto.builder()
                    .orderItemId(item.getId())
                    .skuName(item.getSku().getName())
                    .skuImage(item.getSku().getImageUrl())
                    .orderedAt(df.format(item.getCreatedAt()))
                    .expiredAt(df.format(item.getSku().getExpiredAt()))
                    .build());
        }

        return orderItemDtoList;
    }

    @Override
    public void changeNotificationById(Long orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem.isPresent()){
            orderItem.get().setNoti(false);
            orderItemRepository.save(orderItem.get());
        }
    }
}