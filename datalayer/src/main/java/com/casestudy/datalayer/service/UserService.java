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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casestudy.datalayer.dto.UserDTO;
import java.util.List;
import java.util.UUID;


import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PortfolioRepo portfolioRepo;
    @Autowired
    WatchListRepo watchListRepo;
    @Autowired
    MapperUtil mapperUtil;

    public String createUser(UserDTO userDTO){
        User user = mapperUtil.mapUserDtoToUser(userDTO);
        try {
            Portfolio portfolio = new Portfolio();
            userRepo.save(user);
            portfolio.setUser(user);
            portfolioRepo.save(portfolio);
            user.setPortfolio(portfolio);
            Watchlist watchlist = new Watchlist();
            watchlist.setUser(user);
            watchListRepo.save(watchlist);
            user.setWatchlist(watchlist);

            return "User created successfully";
        } catch (Exception e) {
            return "User creation failed " + e.getMessage();
        }
    }

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
    public List<UserDTO> getAllUsers(){
        try {
            return mapperUtil.mapUserListToUserDTOList(userRepo.findAll());
        } catch (Exception e) {
            throw e;
        }
    }
    public String deleteUser(UUID id){
        try {
            User user = userRepo.findById(id).orElse(null);
            if(user == null){
                return "User not found";
            }
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
            return "User deletion failed " + e.getMessage();
        }
    }
    public String updateUser(UUID id ,UserDTO userDTO){
        try {
            User user = mapperUtil.mapUserDtoToUser(userDTO);
            User user1 = userRepo.findById(id).orElse(null);
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

    public String login(UserDTO userDTO , HttpServletResponse response) {
        try {
            User user = userRepo.findByUsername(userDTO.getUsername());
            if(user == null){
                return "User not found";
            }
            if(user.getPassword().equals(userDTO.getPassword())){
                Cookie userIdCookie = new Cookie("userId", user.getUserId().toString());
                userIdCookie.setMaxAge(60*60*24);
                userIdCookie.setPath("/");
                userIdCookie.setHttpOnly(true);

                Cookie watchlistIdCookie = new Cookie("watchlistId", user.getWatchlist().getWatchlistId().toString());
                watchlistIdCookie.setMaxAge(60*60*24);
                watchlistIdCookie.setHttpOnly(true);
                watchlistIdCookie.setPath("/");

                Cookie portfolioIdCookie = new Cookie("portfolioId", user.getPortfolio().getPortfolioId().toString());
                portfolioIdCookie.setMaxAge(60*60*24);
                portfolioIdCookie.setHttpOnly(true);
                portfolioIdCookie.setPath("/");

                response.addCookie(userIdCookie);
                response.addCookie(watchlistIdCookie);
                response.addCookie(portfolioIdCookie);
                return "Login successful";
            }
            return "Login failed here ";
        } catch (Exception e) {
            return "Login failed from exception" + e.getMessage();
        }
    }
}
