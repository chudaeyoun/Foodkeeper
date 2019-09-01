package com.foodkeeper.domain;

import com.foodkeeper.domain.Enum.PaymentStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Data
public class Payment extends BaseEntity {
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "price", nullable = false)
    private int price;
}
