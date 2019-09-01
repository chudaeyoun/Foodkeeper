package com.foodkeeper.service;

import com.foodkeeper.domain.Sku;
import com.foodkeeper.domain.User;

import java.util.List;

public interface SkuBiz {
    List<Sku> getAllSkuList();
    Sku getSkuByBarcode(String barcode);
}