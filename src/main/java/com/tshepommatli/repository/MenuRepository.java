/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.repository;

import com.tshepommatli.model.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Tshepo Mmatli
 */

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{
    public List<Menu> findByRestaurantId(@Param("restaurantId") Long restaurantId);
    
    //Used to get menu using section name and restaurant id attributes
    public List<Menu> findBySectionNameAndId(@Param("restaurantId") Long restaurantId,
            @Param("sectionName") String sectionName);
}

