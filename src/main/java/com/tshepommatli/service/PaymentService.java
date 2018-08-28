/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.service;

import com.tshepommatli.model.Payment;
import com.tshepommatli.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tshepo Mmatli
 */
@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public void addPayment(Payment payment) {
       paymentRepository.save(payment);
    }
    
}
