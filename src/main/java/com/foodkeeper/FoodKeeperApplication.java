package com.foodkeeper;

import com.foodkeeper.domain.TestEntity;
import com.foodkeeper.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodKeeperApplication implements CommandLineRunner {

    @Autowired
    private TestRepository testRepository;

    public static void main(String[] args) {
        SpringApplication.run(FoodKeeperApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testRepository.save(getTestEntity());
    }

    private TestEntity getTestEntity(){
        TestEntity testEntity = new TestEntity();
        testEntity.setName("대윤");
        return testEntity;
    }
}
