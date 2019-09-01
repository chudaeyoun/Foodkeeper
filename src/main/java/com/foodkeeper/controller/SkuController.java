package com.foodkeeper.controller;

import com.foodkeeper.domain.Sku;
import com.foodkeeper.service.SkuBiz;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sku")
public class SkuController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SkuBiz skuBiz;

    @GetMapping("/lists")
    public ResponseEntity<List<Sku>> getAllSkuList() {
        try {
            List<Sku> skuList = skuBiz.getAllSkuList();
            if (skuList.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(skuList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{barcode}")
    public ResponseEntity getSku(@PathVariable("barcode") String barcode) {
        logger.info("param {barcode} =>" + barcode);

        if (Strings.isNullOrEmpty(barcode)) {
            String message = "파라미터 확인을 해주세요. param {barcode} => " + barcode;
            logger.error(message);
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }

        try {
            Sku sku = skuBiz.getSkuByBarcode(barcode);
            if (sku == null) {
                String message = "해당 barcode와 일치하는 상품이 없습니다";
                logger.error(message);
                return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(sku, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
