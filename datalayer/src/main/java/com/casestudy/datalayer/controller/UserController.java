package com.casestudy.datalayer.controller;
import com.casestudy.datalayer.dto.UserDTO;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService UserService;

    @PostMapping("/user")
    public String createUser(@RequestBody UserDTO userDTO){
        try {
            return UserService.createUser(userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable("id") UUID id){
        try {
            return UserService.getUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        try {
            return UserService.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/user/{id}")
    public String updateUser(@PathVariable ("id") UUID id, @RequestBody UserDTO userDTO){
        try {
            return UserService.updateUser(id,userDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable ("id") UUID id){
        try {
            return UserService.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
