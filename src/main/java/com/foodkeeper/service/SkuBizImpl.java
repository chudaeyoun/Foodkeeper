package com.foodkeeper.service;

import com.foodkeeper.domain.Sku;
import com.foodkeeper.domain.SkuDto;
import com.foodkeeper.repository.SkuRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SkuBizImpl implements SkuBiz {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public List<SkuDto> getAllSkuList() {
        List<Sku> skuList = Lists.newArrayList(skuRepository.findAll());
        List<SkuDto> skuDtoList = Lists.newArrayList();
        for (Sku sku : skuList) {
            skuDtoList.add(convertToSkuDto(sku));
        }
        return skuDtoList;
    }

    @Override
    public SkuDto getSkuByBarcode(String barcode) {
        Sku sku = skuRepository.findByBarcode(barcode);
        return convertToSkuDto(sku);
    }

    private SkuDto convertToSkuDto(Sku sku) {
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        SkuDto skuDto = SkuDto.builder()
                .name(sku.getName())
                .barcode(sku.getBarcode())
                .price(sku.getPrice())
                .expiredAt(df.format(sku.getExpiredAt()))
                .imageUrl(sku.getImageUrl())
                .build();
        return skuDto;
    }
}

