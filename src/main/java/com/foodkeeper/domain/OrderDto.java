package com.foodkeeper.domain;

import lombok.Data;

import java.util.Map;

@Data
public class OrderDto {
    String userId;
    Map<Long, Integer> orderMap;
}