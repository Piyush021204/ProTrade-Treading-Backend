package com.piyush.repository;

import com.piyush.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.lang.model.element.PackageElement;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
}
