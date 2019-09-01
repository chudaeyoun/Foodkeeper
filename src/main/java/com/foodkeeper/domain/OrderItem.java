package com.foodkeeper.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@Builder
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "sku_id")
    private Sku sku;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "noti_yn", nullable = false)
    private boolean noti_yn;
}
