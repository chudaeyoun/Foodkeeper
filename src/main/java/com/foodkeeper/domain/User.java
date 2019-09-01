package com.foodkeeper.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Column(name = "user_id", length = 10)
    private String userId;
}
