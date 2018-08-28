package com.tshepommatli.repository;

import org.springframework.stereotype.Repository;
import com.tshepommatli.model.User;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository("userRepository")
public interface UserRepository extends CrudRepository <User, Integer> {
	 User findByEmail(String email);
         @Query("SELECT u FROM User u WHERE u.email = :email")
         public ArrayList<User> viewByUserId(@Param("email") String email);
}
