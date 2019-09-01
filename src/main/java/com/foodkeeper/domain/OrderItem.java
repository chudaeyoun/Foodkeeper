package com.foodkeeper.domain;

import com.foodkeeper.domain.Enum.OrderItemStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem extends BaseEntity {

    @Column(name = "status", nullable = false)
    private OrderItemStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sku_id")
    private Sku sku;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "noti_yn", nullable = false)
    private boolean noti_yn;
}
