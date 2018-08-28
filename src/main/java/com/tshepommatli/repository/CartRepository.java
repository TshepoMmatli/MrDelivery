/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.repository;

import com.tshepommatli.model.Cart;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tshepo Mmatli
 */
@Repository
public interface CartRepository extends CrudRepository <Cart, Integer>{
    
    @Query("SELECT l FROM Cart l WHERE l.userId = :userId")
    public ArrayList<Cart> viewByUserId(@Param("userId") int userId);
    
}
