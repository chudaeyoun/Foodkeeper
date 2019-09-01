package com.foodkeeper;

import com.foodkeeper.domain.TestEntity;
import com.foodkeeper.domain.User;
import com.foodkeeper.repository.TestRepository;
import com.foodkeeper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodKeeperApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodKeeperApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userRepository.save(getUser());
    }

    private User getUser(){
        User user = new User();
        user.setUserId("admin");
        return user;
    }
}
