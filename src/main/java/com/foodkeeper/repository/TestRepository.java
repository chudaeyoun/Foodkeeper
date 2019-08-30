package com.foodkeeper.repository;

import com.foodkeeper.domain.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Long> {
    List<TestEntity> findAll();
}
