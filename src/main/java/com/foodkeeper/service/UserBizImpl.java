package com.foodkeeper.service;

import com.foodkeeper.domain.User;
import com.foodkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBizImpl implements UserBiz {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
