package com.foodkeeper.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationItemDto {
    private String userId;
    private String token;
    private String skuName;
    private String orderedAt;
    private String expiredAt;
}
