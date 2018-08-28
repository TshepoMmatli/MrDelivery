package com.tshepommatli.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tshepommatli.model.Role;
import com.tshepommatli.model.User;
import com.tshepommatli.repository.RoleRepository;
import com.tshepommatli.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Override
    public User findUserByEmail(String email) {
            return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(2);
    Role userRole = roleRepository.findByRole("customer");
    user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            userRepository.save(user);
    }
    
    //Additional Functions
    //User Cart
    @Override
    public ArrayList<User> getUser(String email)
    {
            return userRepository.viewByUserId(email);
    }
    
    //gets All Users 	
    public List<User> getAllUsers()
    {
        List<User> Users = new ArrayList<>();
        userRepository.findAll()
        .forEach(Users::add);
        return Users;	
    }
	
    //Add user 
    public void AddUser(User Users) {
        userRepository.save(Users);
    }
	
    //Update user using user id 
    public void updateUser(int id, User Users) 
    {
        userRepository.save(Users);
    }

    //Delete user using user id 
    public void DeleteUser(int userID) {	
    	userRepository.delete(userID);	
    }
}
