package com.foodkeeper.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@Builder
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;
}
