/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.service;

import com.tshepommatli.model.Menu;
import com.tshepommatli.repository.MenuRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/**
 *
 * @author Tshepo Mmatli
 */
@Service
public class MenuService {
    
    @Autowired
    public MenuRepository menuRepository;
    
    public List<Menu> getMenus(Long restaurantId)
    {
        List<Menu> menus =  menuRepository.findByRestaurantId(restaurantId);
        return menus;
    }
    
    public List<Menu> getMenus(Long restaurantId, String sectionName)
    {
        List<Menu> menus =  menuRepository.findBySectionNameAndId(restaurantId, sectionName);
        return menus;
    }
    
    
    public void addMenuItem(Menu menu)
    {
        menuRepository.save(menu);
    }
    
    
 
}
