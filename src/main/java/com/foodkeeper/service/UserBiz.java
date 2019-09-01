package com.foodkeeper.service;

import com.foodkeeper.domain.TestEntity;
import com.foodkeeper.domain.User;

import java.util.List;

public interface UserBiz {
    User getUserByUserId(String userId);
}
