package com.foodkeeper.repository;

import com.foodkeeper.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> findByUserId(Long userId);
    List<OrderItem> findByUseAndNoti(Boolean use, Boolean noti);
    List<OrderItem> findByUserIdAndUse(Long userId, Boolean use);
}
