package com.piyush.repository;

import com.piyush.model.Order;
import com.piyush.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order , Long> {

   List<Order>  findByUserId(Long userId);

}
