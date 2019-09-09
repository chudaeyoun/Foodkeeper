package com.foodkeeper.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkuDto {
    private Long id;
    private String name;
    private int price;
    private String barcode;
    private String expiredAt;
    private String imageUrl;
}
