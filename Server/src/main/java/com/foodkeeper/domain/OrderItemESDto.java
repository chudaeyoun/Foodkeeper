package com.foodkeeper.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Data
@Builder
@Document(indexName = "foodkeeper", type = "order_item")
public class OrderItemESDto {
    @Id
    private Long id;
    // User 테이블 ID
    private Long userId;
    // 주문상품 ID
    private Long orderItemId;
    // 상품명
    private String skuName;
    // 상품 이미지
    private String skuImage;
    // 구매날짜
    private String orderedAt;
    // 유통기한
    private String expiredAt;
    // 푸시 알림
    private boolean noti;
}
