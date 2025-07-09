package com.piyush.service;

import com.piyush.domain.OrderType;
import com.piyush.model.Coin;
import com.piyush.model.Order;
import com.piyush.model.OrderItem;
import com.piyush.model.User;

import java.util.List;

public interface OrderService {


    Order createOrder(User user, OrderItem orderItem , OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;

    List<Order> getAllOrderOfUser(Long  userId, OrderType orderType,String assetSymbol);

    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;




}
