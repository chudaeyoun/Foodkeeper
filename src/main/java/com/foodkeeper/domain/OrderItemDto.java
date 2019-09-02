package com.foodkeeper.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    // 주문상품 ID
    Long orderItemId;
    // 상품명
    String skuName;
    // 상품 이미지
    String skuImage;
    // 구매날짜
    String orderedAt;
    // 유통기한
    String expiredAt;
}
