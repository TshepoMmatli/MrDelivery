/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.service;

import com.tshepommatli.model.Restaurant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tshepommatli.repository.RestaurantRepository;


/**
 *
 * @author Tshepo Mmatli
 */
@Service
public class RestaurantService {
    
    @Autowired
    public RestaurantRepository restaurantRepository;
    
    public void addRestaurant(Restaurant restaurant)
    {
        restaurantRepository.save(restaurant);
    }
    
    public List<Restaurant> getRestaurants()
    {
        Boolean requestStatus = true;
        List<Restaurant> restaurants =  restaurantRepository.findByRequestStatus(requestStatus);
        return restaurants;
    }

    
    public List<Restaurant> getPendingRestaurants()
    {
        Boolean requestStatus = false;
        List<Restaurant> restaurants =  restaurantRepository.findByRequestStatus(requestStatus);
        return restaurants;
    }
    
    public Restaurant findByRestaurantId(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        return restaurant;
    }
    
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
    
    public void updateRestaurant(Long restaurantId){
        
        Restaurant restaurant = findByRestaurantId(restaurantId);
        restaurant.setRequestStatus(true);
        saveRestaurant(restaurant);
        System.out.println("Update OK 201");
        
    }
    
    public void deleteRestaurant(Long restaurantId)
    {
        restaurantRepository.delete(restaurantId);
    }
}
