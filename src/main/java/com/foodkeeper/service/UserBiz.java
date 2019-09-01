package com.foodkeeper.service;

import com.foodkeeper.domain.User;

public interface UserBiz {
    User getUserByUserId(String userId);
}
