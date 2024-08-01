package com.casestudy.datalayer.controller;
import com.casestudy.datalayer.dto.UserDTO;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService UserService;

    //create new user
    @PostMapping("/api-v1/signup")
    public ResponseEntity<String> addingNewUser(@RequestBody UserDTO userDTO){
        try {
            String res= UserService.createUser(userDTO);
            if(res.contains("is already registered")){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
            } else if (res.contains("failed")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            return ResponseEntity.ok(res);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User creation failed: " + e.getMessage());
        }
    }

    //login
    @PostMapping("/api-v1/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        try {
            String result = UserService.login(userDTO, response);
            if (result.equals("User not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            } else if (result.equals("Invalid Password")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }

    //get user by id
    @GetMapping("/api-v1/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") UUID id){
        try {
            UserDTO res = UserService.getUser(id);
            if (res == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get all users
    @GetMapping("/auth/api-v1/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        try {
            List<UserDTO> res = UserService.getAllUsers();
            if (res == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update user
    @PatchMapping("/auth/api-v1/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable ("id") UUID id, @RequestBody UserDTO userDTO){
        try {
            String res = UserService.updateUser(id,userDTO);
            if (res.contains("failed")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            } else if (res.contains("already registered")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
            } else if ( res.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User update failed: " + e.getMessage());
        }
    }

    //delete user
    @DeleteMapping("/auth/api-v1/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable ("id") UUID id){
        try {
            String res = UserService.deleteUser(id);
            if (res.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }else if (res.contains("failed")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User deletion failed: " + e.getMessage());
        }
    }
}
