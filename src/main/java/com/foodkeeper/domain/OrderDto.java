package com.foodkeeper.domain;

import lombok.Data;

import java.util.Map;

@Data
public class OrderDto {
    private String custNo;
    private Map<Long, Integer> orderMap;
}