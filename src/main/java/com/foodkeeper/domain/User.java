package com.foodkeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(name = "user_id", length = 10)
    private String userId;

    @Column(name = "cust_no", length = 11)
    private String custNo;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "fcm_token", length = 50)
    private String token;
}
