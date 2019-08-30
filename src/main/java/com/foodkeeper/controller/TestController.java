package com.foodkeeper.controller;

import com.foodkeeper.domain.TestEntity;
import com.foodkeeper.service.TestBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestBiz testBiz;

    @GetMapping("/lists")
    public ResponseEntity<List<TestEntity>> getAllTestEntityList() {
        try {
            List<TestEntity> testList = testBiz.getAllTestEntityList();
            if (testList.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(testList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
