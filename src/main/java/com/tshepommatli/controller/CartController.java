package com.tshepommatli.controller;

import com.tshepommatli.model.Cart;
import com.tshepommatli.service.CartService;
import com.tshepommatli.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* @author Tshepo Mmatli
**/

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;
    
    @PostMapping(value = "/saveCart")
    public void SaveCart(@RequestBody Cart cart)
    {
        cartService.SaveCart(cart);		
    }
    
    @RequestMapping(value = "/viewCart/{userID}", method = RequestMethod.GET)
    public List<Cart> getAllCart(@PathVariable(value = "userID") int userID)
    {
        return cartService.getCart(userID);
    }
    
    @RequestMapping(value = "/emptyCart/{userID}", method = RequestMethod.DELETE)
    public void emptyCart(@PathVariable(value = "userID") int userID){
        List<Cart> cart =  cartService.getCart(userID);
        for(int item = 0; item < cart.size(); item++)
        {
             if(cart.get(item).getUserId() == userID)
                cartService.deleteCart(cart.get(item).getCartId());     
        }
    }
    
    @RequestMapping(value = "/deleteCart/{cartID}", method = RequestMethod.DELETE)
    public void DeleteCart(@PathVariable(value = "cartID") int cartID)
    {
        cartService.DeleteCart(cartID);
    }
}
