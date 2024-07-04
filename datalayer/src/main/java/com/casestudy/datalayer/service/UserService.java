package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casestudy.datalayer.dto.UserDTO;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    MapperUtil mapperUtil;

    public String createUser(UserDTO userDTO){
        User user = mapperUtil.mapUserDtoToUser(userDTO);
        try {
            userRepo.save(user);
            return "User created successfully";
        } catch (Exception e) {
            return "User creation failed " + e.getMessage();
        }
    }

    public User getUser(Long id){
        try {
            return userRepo.findById(id).orElse(null);
        } catch (Exception e) {
            throw e;
        }
    }
    public List<User> getAllUsers(){
        try {
            return userRepo.findAll();
        } catch (Exception e) {
            throw e;
        }
    }
    public String deleteUser(Long id){
        try {
            User user = userRepo.findById(id).orElse(null);
            if(user == null){
                return "User not found";
            }
            user.setDeleted(true);
            return "User deleted successfully";
        } catch (Exception e) {
            return "User deletion failed " + e.getMessage();
        }
    }
    public String updateUser(UserDTO userDTO){
        try {
            User user = mapperUtil.mapUserDtoToUser(userDTO);
            User user1 = userRepo.findById(user.getUserId()).orElse(null);
            if(user1 == null){
                return "User not found";
            }
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setUsername(user.getUsername());
            userRepo.save(user1);
            return "User updated successfully";
        } catch (Exception e) {
            return "User updation failed " + e.getMessage();
        }
    }
}
