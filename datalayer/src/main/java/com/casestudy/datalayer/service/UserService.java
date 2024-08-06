package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.entity.Portfolio;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.entity.Watchlist;
import com.casestudy.datalayer.repositary.PortfolioRepo;
import com.casestudy.datalayer.repositary.UserRepo;
import com.casestudy.datalayer.repositary.WatchListRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.casestudy.datalayer.dto.UserDTO;

import java.util.*;


import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private Validator validator;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PortfolioRepo portfolioRepo;
    @Autowired
    WatchListRepo watchListRepo;
    @Autowired
    MapperUtil mapperUtil;
    @Autowired
    JwtService jwtService;

    // Password encoder
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    //save user
    @Transactional
    public String createUser( UserDTO userDTO) {
        // Check if username or email already exist
        if (userRepo.existsByUsername(userDTO.getUsername())) {
            return "Username '" + userDTO.getUsername() + "' is already registered";
        }
        if (userRepo.existsByEmail(userDTO.getEmail())) {
            return "Email '" + userDTO.getEmail() + "' is already registered";
        }
        User user = mapperUtil.mapUserDtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Add a validation step
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            return  violations.iterator().next().getMessage();
        }
        try {
            // Saving the user first
            userRepo.save(user);

            // Creating and saving the portfolio
            Portfolio portfolio = new Portfolio();
            portfolio.setUser(user);
            portfolioRepo.save(portfolio);
            user.setPortfolio(portfolio);

            // Creating and saving the watchlist
            Watchlist watchlist = new Watchlist();
            watchlist.setUser(user);
            watchListRepo.save(watchlist);
            user.setWatchlist(watchlist);

            return "User created successfully";
        } catch (Exception e) {
            if (e.getMessage().contains("User")) {
                return "User creation failed: " + e.getMessage();
            } else if (e.getMessage().contains("Portfolio")) {
                return "Portfolio creation failed: " + e.getMessage();
            } else if (e.getMessage().contains("Watchlist")) {
                return "Watchlist creation failed: " + e.getMessage();
            } else {
                return "User creation failed: " + e.getMessage();
            }
        }
    }

    //login
    @Transactional
    public String login(UserDTO userDTO, HttpServletResponse response) {
        try {
            // Check if user exists
            User user = userRepo.findByUsername(userDTO.getUsername());
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "User not found";
            }

            // Check if password is correct
            if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                String token = jwtService.generateToken(user.getUsername());
                // Add the token to the response headers
                response.addHeader("Authorization", "Bearer " + token);
                response.addHeader("userId", user.getUserId().toString());
                response.addHeader("watchlistId", user.getWatchlist().getWatchlistId().toString());
                response.addHeader("portfolioId", user.getPortfolio().getPortfolioId().toString());
                response.setStatus(HttpServletResponse.SC_OK);
                return "Login Successful";
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return "Invalid Password";
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "An error occurred: " + e.getMessage();
        }
    }


    //get user by id
    @Transactional
    public UserDTO getUser( UUID id){
        try {
            User user = userRepo.findById(id).orElse(null);
            if(user == null){
                return null;
            }
            return mapperUtil.mapUserToUserDto(user);
        } catch (Exception e) {
            throw e;
        }
    }


    //get all users
    @Transactional
    public List<UserDTO> getAllUsers(){
        try {
            return mapperUtil.mapUserListToUserDTOList(userRepo.findAll());
        } catch (Exception e) {
            throw e;
        }
    }

    //delete user
    @Transactional
    public String deleteUser(UUID id){
        try {
            // Check if user exists
            User user = userRepo.findById(id).orElse(null);
            if(user == null){
                return "User not found";
            }

            // Deleting the user portfolio and watchlist
            Portfolio portfolio = user.getPortfolio();
            Watchlist watchlist = user.getWatchlist();

            portfolio.setDeleted(true);
            portfolioRepo.save(portfolio);

            watchlist.setDeleted(true);
            watchListRepo.save(watchlist);

            user.setDeleted(true);
            userRepo.save(user);

            return "User deleted successfully";
        } catch (Exception e) {
            if (e.getMessage().contains("User")) {
                return "User deletion failed: " + e.getMessage();
            } else if (e.getMessage().contains("Portfolio")) {
                return "Portfolio deletion failed: " + e.getMessage();
            } else if (e.getMessage().contains("Watchlist")) {
                return "Watchlist deletion failed: " + e.getMessage();
            } else {
                return "User deletion failed: " + e.getMessage();
            }
        }
    }

    //update user
    @Transactional
    public String updateUser(UUID id ,UserDTO userDTO){
        try {
            // Check if user exists
            User user = mapperUtil.mapUserDtoToUser(userDTO);
            User user1 = userRepo.findById(id).orElse(null);
            if(user1 == null){
                return "User not found";
            }

            // Check if username or email already exist
            if (userRepo.existsByUsername(user.getUsername()) && !user1.getUsername().equals(user.getUsername())) {
                return "Username '" + user.getUsername() + "' is already registered";
            }
            if (userRepo.existsByEmail(user.getEmail()) && !user1.getEmail().equals(user.getEmail())) {
                return "Email '" + user.getEmail() + "' is already registered";
            }

            // Updating the user
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
