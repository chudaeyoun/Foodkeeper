package com.foodkeeper.repository;

import com.foodkeeper.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> findByUserIdAndNoti(Long userId, Boolean noti);
}
