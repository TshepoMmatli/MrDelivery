package com.tshepommatli.service;

import com.tshepommatli.model.User;
import java.util.ArrayList;
import java.util.List;

public interface UserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
    public ArrayList<User> getUser(String email);
    public List<User> getAllUsers();
    public void AddUser(User Users);
    public void updateUser(int id, User Users);
    public void DeleteUser(int userID);
}
