package com.piyush.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piyush.service.CoinService;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double quantity;

    @ManyToOne
    private Coin coin;

    private double buyPrice;

    private double sellPrice;

    @JsonIgnore
    @OneToOne
    private Order order;


}
