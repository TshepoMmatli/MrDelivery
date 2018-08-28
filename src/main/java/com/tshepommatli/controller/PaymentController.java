/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.controller;

import com.tshepommatli.model.Payment;
import com.tshepommatli.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tshepo Mmatli
 */
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService ;
    
    @RequestMapping(value="/SavePayment", method = RequestMethod.POST)
    public void addRestaurant(@RequestBody Payment payment){
            paymentService.addPayment(payment);
    }
    
}
