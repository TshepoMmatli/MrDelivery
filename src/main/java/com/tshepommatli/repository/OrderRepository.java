/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.repository;
import com.tshepommatli.model.Orders;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tshepo
 */
@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT l FROM Orders l WHERE l.userId = :userId")
    public ArrayList<Orders> viewByUserId(@Param("userId") int userId);
    public Orders findByOrderId(@Param("orderId") int orderId);
    public ArrayList<Orders> findByOrderStatusAndUserId(@Param("status") String status, @Param("userId") int userId);
}
