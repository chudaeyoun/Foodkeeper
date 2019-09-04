package com.foodkeeper.repository;

import com.foodkeeper.domain.OrderItemESDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemESRepository extends ElasticsearchRepository<OrderItemESDto, Long> {
    List<OrderItemESDto> findByUserIdAndNoti(Long userId, Boolean noti);
}