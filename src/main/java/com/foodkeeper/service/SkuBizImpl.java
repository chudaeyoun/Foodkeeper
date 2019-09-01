package com.foodkeeper.service;

import com.foodkeeper.domain.Sku;
import com.foodkeeper.repository.SkuRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuBizImpl implements SkuBiz {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public List<Sku> getAllSkuList() {
        return Lists.newArrayList(skuRepository.findAll());
    }

    @Override
    public Sku getSkuByBarcode(String barcode) {
        return skuRepository.findByBarcode(barcode);
    }
}

