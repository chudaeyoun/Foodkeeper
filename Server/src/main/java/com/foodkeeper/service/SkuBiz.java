package com.foodkeeper.service;

import com.foodkeeper.domain.SkuDto;

import java.util.List;

public interface SkuBiz {
    List<SkuDto> getAllSkuList();
    SkuDto getSkuByBarcode(String barcode);
}