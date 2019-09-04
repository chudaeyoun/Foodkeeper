package com.foodkeeper.domain;

import lombok.Data;

import java.util.Map;

@Data
public class OrderDto {
    private String userId;
    private Map<Long, Integer> orderMap;
}