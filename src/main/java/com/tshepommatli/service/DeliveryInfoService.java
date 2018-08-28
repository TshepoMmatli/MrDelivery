/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.service;
import com.tshepommatli.model.DeliveryInfo;
import com.tshepommatli.repository.DeliveryInfoRepository;
import com.tshepommatli.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tshepo Mmatli
 */

@Service
public class DeliveryInfoService {
    @Autowired
    DeliveryInfoRepository deliveryInfoRepository;
    @Autowired
    private UserRepository userRepository;

    //Save DeliveryInfo Method
    public void SaveDeliveryInfo(DeliveryInfo deliveryInfo) {
       deliveryInfoRepository.save(deliveryInfo);
    }
    
    //gets deliveryInfo using user id 	
    public List<DeliveryInfo> getDeliveryInfo(int userId)
    {
        return deliveryInfoRepository.viewByUserId(userId);
    }

    public boolean userInfoExists(int userId)
    {
        boolean flag = false;
        List<DeliveryInfo> userInfo = deliveryInfoRepository.viewByUserId(userId);
        
        if(userInfo != null);
        {
            flag = true;
        }
        
        return flag;
    }
}
