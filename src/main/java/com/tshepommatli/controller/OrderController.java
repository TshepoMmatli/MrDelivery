/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.controller;

import com.tshepommatli.model.Orders;
import com.tshepommatli.model.User;
import com.tshepommatli.service.OrderService;
import com.tshepommatli.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Tshepo
 */

@RestController
public class OrderController {
    
    @Autowired
    private OrderService orderService ;
    @Autowired
    private UserService userService ;
    
    //Modal and view functions
    @RequestMapping(value="/createOrder", method = RequestMethod.GET)
    public ModelAndView createOrder(){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("createOrder");
            return modelAndView;
    }

    @RequestMapping(value="/placeOrder", method = RequestMethod.GET)
    public ModelAndView placeOrder(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Hi, " + user.getName() + " " + user.getLastName());
            modelAndView.addObject("userID", user.getId());
            modelAndView.setViewName("placeOrder");
            return modelAndView;
    }
    
    @RequestMapping(value="/checkout", method = RequestMethod.GET)
    public ModelAndView checkout(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Hi, " + user.getName() + " " + user.getLastName());
            modelAndView.addObject("userID", user.getId());
            modelAndView.setViewName("checkout");
            return modelAndView;
    }
            
    @RequestMapping(value="/orderConfirmation", method = RequestMethod.GET)
    public ModelAndView orderConfirmation(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Hi, " + user.getName() + " " + user.getLastName());
            modelAndView.addObject("userID", user.getId());
            modelAndView.setViewName("orderConfirmation");
            return modelAndView;
    }
            
    @RequestMapping(value="/orders", method = RequestMethod.GET)
    public ModelAndView orders(){
            ModelAndView modelAndView = new ModelAndView();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            modelAndView.addObject("userName", "Hi, " + user.getName() + " " + user.getLastName());
            modelAndView.addObject("userID", user.getId());
            modelAndView.setViewName("orders");
            return modelAndView;
    }
    
    @RequestMapping(value="/saveOrder", method = RequestMethod.POST)
    public void saveOrder(@RequestBody Orders order){
        orderService.SaveOrder(order);
    }
    
    @RequestMapping(value = "/viewOrder/{userID}", method = RequestMethod.GET)
    public List<Orders> getAllOrder(@PathVariable(value = "userID") int userID)
    {
        return orderService.getOrder(userID);
    }
    
    @RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
    public List<Orders> getAllOrders()
    {
        return orderService.getAllOrders();
    }
    
    @RequestMapping(value = "/getPlacedOrders/{userId}" , method = RequestMethod.GET)
    public List<Orders> getPlacedOrders(@PathVariable(value = "userId") int userId)
    {
        //Return orders of "Order Placed" status and the user ID passed
        return orderService.getPlacedOrders("Order Placed", userId);
    }
    
    @PutMapping("/changeStatus/{orderId}")
    public void updateOrder(@PathVariable(value = "orderId") int orderId) {
        Orders order = orderService.FindByOrderId(orderId);
        
        if(order != null) {
            order.setStatus("Delivered");
        }

        orderService.SaveOrder(order);
      
    }
            
                //Delete a Restaurant
    @DeleteMapping("/deleteOrder/{orderId}")
    public void deleteOrder(@PathVariable(value = "orderId") int orderId) {
        Orders order = orderService.FindByOrderId(orderId);
        if(order != null) {
           
            orderService.deleteOrder(orderId);
        }
    }
}
