package com.foodkeeper.service;

import com.foodkeeper.domain.Sku;

import java.util.List;

public interface SkuBiz {
    List<Sku> getAllSkuList();
    Sku getSkuByBarcode(String barcode);
}