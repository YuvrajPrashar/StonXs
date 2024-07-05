package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.WatchlistDTO;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.entity.Watchlist;
import com.casestudy.datalayer.repositary.UserRepo;
import com.casestudy.datalayer.repositary.WatchListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WatchlistService {
    @Autowired
    private WatchListRepo watchListRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MapperUtil mapperUtil;

    public WatchlistDTO getWatchlistByUserId(UUID id) {
        try {
            User user = userRepo.findById(id).orElse(null);
            if (user == null) {
                return null;
            }
            Watchlist watchlist = user.getWatchlist();
            return mapperUtil.mapWatchlistToWatchlistDTO(watchlist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public WatchlistDTO getWatchlist(UUID id) {
        try {
            Watchlist watchlist = watchListRepo.findById(id).orElse(null);
            if (watchlist == null) {
                return null;
            }
            return mapperUtil.mapWatchlistToWatchlistDTO(watchlist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<WatchlistDTO> getAllWatchlists() {
        try {
            return mapperUtil.mapWatchlistListToWatchlistDTOList(watchListRepo.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
