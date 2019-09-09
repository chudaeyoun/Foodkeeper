package com.foodkeeper.service;

import com.foodkeeper.domain.NotificationItemDto;
import com.foodkeeper.domain.OrderItemDto;

import java.util.HashMap;
import java.util.List;

public interface OrderItemBiz {
    HashMap<String, List<OrderItemDto>> getOrderItemMapByUserId(Long userId, boolean sort);
    void deleteOrderItemById(Long orderItemId);
    void disuseNotificationById(Long orderItemId);
    List<NotificationItemDto> getNotificationItemList();
    void enableNotification();
}
