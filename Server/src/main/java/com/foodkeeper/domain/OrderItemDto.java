package com.foodkeeper.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    // 주문상품 ID
    private Long orderItemId;
    // 상품명
    private String skuName;
    // 바코드
    private String barcode;
    // 상품 이미지
    private String skuImage;
    // 구매날짜
    private String orderedAt;
    // 유통기한
    private String expiredAt;
    // d-day
    private String d_day;
}
