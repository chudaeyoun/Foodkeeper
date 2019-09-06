package com.foodkeeper.service;

import com.foodkeeper.domain.NotificationItemDto;
import com.foodkeeper.domain.OrderItem;
import com.foodkeeper.domain.OrderItemDto;
import com.foodkeeper.repository.OrderItemRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderItemBizImpl implements OrderItemBiz {

    @Autowired
    private OrderItemRepository orderItemRepository;

    private DateFormat df = new SimpleDateFormat("yyyy.MM.dd");

    @Override
    public HashMap<String, List<OrderItemDto>> getOrderItemMapByUserId(Long userId) {
        List<OrderItem> orderItemList = orderItemRepository.findByUserIdAndNoti(userId, true);

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

        HashMap<String, List<OrderItemDto>> orderItemMap = Maps.newHashMap();
        orderItemMap.put("items", orderItemDtoList);
        return orderItemMap;
    }

    @Override
    public void changeNotificationById(Long orderItemId) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        if (orderItem.isPresent()) {
            orderItem.get().setNoti(false);
            orderItemRepository.save(orderItem.get());
        }
    }

    @Override
    public List<NotificationItemDto> getNotificationItemList() {
        List<OrderItem> orderItemList = orderItemRepository.findByNoti(true);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 5);
        // 현재 < 유통기한 < 현재 + 5일
        orderItemList.stream()
                .filter(i -> i.getSku().getExpiredAt().after(new Date())
                        && i.getSku().getExpiredAt().before((cal.getTime())))
                .collect(Collectors.toList());

        return convertToNotificationItemDtoList(orderItemList);
    }

    private List<NotificationItemDto> convertToNotificationItemDtoList(List<OrderItem> orderItemList) {
        List<NotificationItemDto> notificationItemDtoList = Lists.newArrayList();
        for (OrderItem orderItem : orderItemList){
            notificationItemDtoList.add(NotificationItemDto.builder()
                    .userId(orderItem.getUser().getUserId())
                    .token(orderItem.getUser().getToken())
                    .skuName(orderItem.getSku().getName())
                    .orderedAt(df.format(orderItem.getOrder().getCreatedAt()))
                    .expiredAt(df.format(orderItem.getSku().getExpiredAt()))
                    .build());
        }
        return notificationItemDtoList;
    }
}