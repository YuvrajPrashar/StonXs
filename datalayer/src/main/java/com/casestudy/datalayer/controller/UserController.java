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

    @PostMapping("/createUser")
    public String createUser(@RequestBody UserDTO userDTO){
        return UserService.createUser(userDTO);
    }
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody UUID id){
        return UserService.deleteUser(id);
    }
    @PatchMapping("/updateUser")
    public String updateUser(@RequestBody UserDTO userDTO){
        return UserService.updateUser(userDTO);
    }
    @GetMapping("/getUser{id}")
    public UserDTO getUser(@PathVariable("id") UUID id){
        return UserService.getUser(id);
    }
    @GetMapping("/getAllUsers")
    public List<UserDTO> getAllUsers(){
        return UserService.getAllUsers();
    }
}
