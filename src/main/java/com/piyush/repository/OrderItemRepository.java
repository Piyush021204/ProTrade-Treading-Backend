package com.piyush.repository;

import com.piyush.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository  extends JpaRepository<OrderItem , Long> {
}
