/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.service;

import com.tshepommatli.model.Orders;
import com.tshepommatli.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tshepo
 */
@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;

    public Orders SaveOrder(Orders order) {
        return orderRepository.save(order);
    }
    
    public List<Orders> getOrder(int userID) {
        return orderRepository.viewByUserId(userID);
    }
    
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Orders FindByOrderId(int orderId)
    {
        return orderRepository.findByOrderId(orderId);
    }
    
    public void deleteOrder(int orderId)
    {
        orderRepository.delete(orderId);
    }
    
    public List<Orders> getPlacedOrders(String status, int userId)
    {
        return orderRepository.findByOrderStatusAndUserId(status, userId);
    }
}
