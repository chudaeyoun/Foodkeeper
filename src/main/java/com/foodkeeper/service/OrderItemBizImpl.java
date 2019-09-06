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
        orderItemList.sort(Comparator.comparing(o -> o.getSku().getExpiredAt()));

        List<OrderItemDto> orderItemDtoList = Lists.newArrayList();
        for (OrderItem item : orderItemList) {
            orderItemDtoList.add(OrderItemDto.builder()
                    .orderItemId(item.getId())
                    .skuName(item.getSku().getName())
                    .skuImage(item.getSku().getImageUrl())
                    .orderedAt(df.format(item.getCreatedAt()))
                    .expiredAt(df.format(item.getSku().getExpiredAt()))
                    .d_day(convertToDDay(item.getSku().getExpiredAt()))
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
        cal.add(Calendar.DATE, -1);

        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.DATE, 6);
        // 오늘 -1 < 유통기한 < 오늘 +6일 (오늘~오늘+5)
        orderItemList.stream()
                .filter(i -> i.getSku().getExpiredAt().after(cal.getTime())
                        && i.getSku().getExpiredAt().before(cal1.getTime()))
                .collect(Collectors.toList());

        orderItemList.sort(Comparator.comparing(o -> o.getSku().getExpiredAt()));
        return convertToNotificationItemDtoList(orderItemList);
    }

    private List<NotificationItemDto> convertToNotificationItemDtoList(List<OrderItem> orderItemList) {
        List<NotificationItemDto> notificationItemDtoList = Lists.newArrayList();
        for (OrderItem orderItem : orderItemList) {
            notificationItemDtoList.add(NotificationItemDto.builder()
                    .orderItemId(orderItem.getId())
                    .userId(orderItem.getUser().getUserId())
                    .token(orderItem.getUser().getToken())
                    .skuName(orderItem.getSku().getName())
                    .orderedAt(df.format(orderItem.getOrder().getCreatedAt()))
                    .expiredAt(df.format(orderItem.getSku().getExpiredAt()))
                    .d_day(convertToDDay(orderItem.getSku().getExpiredAt()))
                    .build());
        }
        return notificationItemDtoList;
    }

    private String convertToDDay(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar d_day = Calendar.getInstance();

        d_day.setTime(date);

        long l_dday = d_day.getTimeInMillis() / (24 * 60 * 60 * 1000);
        long l_today = today.getTimeInMillis() / (24 * 60 * 60 * 1000);

        long dday = l_today - l_dday;

        if (dday == 0) {
            return "D-DAY";
        } else if (dday > 0) {
            return "";
        } else {
            return "D" + dday;
        }
    }
}