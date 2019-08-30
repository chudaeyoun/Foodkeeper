package com.foodkeeper.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TEST")
@Data
public class TestEntity extends BaseEntity {
    @Column(name = "NAME", length = 10)
    private String name;
}
