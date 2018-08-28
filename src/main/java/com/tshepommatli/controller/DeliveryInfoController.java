/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.controller;

import com.tshepommatli.model.DeliveryInfo;
import com.tshepommatli.service.DeliveryInfoService;
import com.tshepommatli.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Tshepo Mmatli
 */

@RestController
public class DeliveryInfoController {
    @Autowired
    DeliveryInfoService deliveryInfoService;
    
    @Autowired
    UserService userService;
    
    //Save function
    @RequestMapping(value="/saveDeliveryInfo", method = RequestMethod.POST)
    public void saveDeliveryInfo(@RequestBody DeliveryInfo deliveryInfo){
        deliveryInfoService.SaveDeliveryInfo(deliveryInfo);
    }

    //Get Function
    @RequestMapping(value = "/viewDeliveryInfo/{userID}", method = RequestMethod.GET)
    public List<DeliveryInfo> getDeliveryInfo(@PathVariable(value = "userID") int userID)
    {
        return deliveryInfoService.getDeliveryInfo(userID);
    } 
}
