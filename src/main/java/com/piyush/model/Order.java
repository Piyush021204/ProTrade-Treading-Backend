package com.piyush.model;

import com.piyush.domain.OrderStatus;
import com.piyush.domain.OrderType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private OrderType orderType;

    @Column(nullable = false)
    private BigDecimal price;


    private LocalDateTime timestamp=LocalDateTime.now();

    @Column(nullable = false)
    private OrderStatus status;


    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private OrderItem orderItem;


}
