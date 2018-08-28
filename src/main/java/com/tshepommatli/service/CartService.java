/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.service;

import com.tshepommatli.model.Cart;
import com.tshepommatli.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tshepo Mmatli
 */
@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    //Add to Cart Method
    public void SaveCart(Cart cart) {
       cartRepository.save(cart);
    }
    
    //gets Cart using user id 	
    public List<Cart> getCart(int userId)
    {
        return cartRepository.viewByUserId(userId);
    }
    
    public void deleteCart(int userId)
    {
        cartRepository.delete(userId);
    }
    
    //Delete Cart using cart id 
    public void DeleteCart(int cartID) {
        cartRepository.delete(cartID);

    }
}
