package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.WatchlistDTO;
import com.casestudy.datalayer.entity.Stock;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.entity.Watchlist;
import com.casestudy.datalayer.repositary.StocksRepo;
import com.casestudy.datalayer.repositary.UserRepo;
import com.casestudy.datalayer.repositary.WatchListRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WatchlistService {
    @Autowired
    private StocksRepo stocksRepo;

    @Autowired
    private WatchListRepo watchListRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MapperUtil mapperUtil;

    //get watchlist by user id
    @Transactional
    public WatchlistDTO getWatchlistByUserId(UUID id) {
        try {
            // Fetching the user
            User user = userRepo.findById(id).orElse(null);
            if (user == null || user.getWatchlist() == null) {
                return null;
            }
            // Fetching the watchlist
            Watchlist watchlist = user.getWatchlist();
            return mapperUtil.mapWatchlistToWatchlistDTO(watchlist);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //get watchlist by watchlist id
    @Transactional
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

    //get all watchlists
    @Transactional
    public List<WatchlistDTO> getAllWatchlists() {
        try {
            return mapperUtil.mapWatchlistListToWatchlistDTOList(watchListRepo.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //add stock to watchlist
    @Transactional
    public String addStockToWatchlist(UUID stockId, UUID watchlisId) {
        try {
            // Fetching the stock and watchlist
            Stock stock = stocksRepo.findById(stockId).orElse(null);
            if (stock == null) {
                return "Stock not found";
            }
            // Fetching the watchlist
            Watchlist watchlist = watchListRepo.findById(watchlisId).orElse(null);
            if (watchlist == null) {
                return "watchlist not found";
            }
            // Adding the stock to the watchlist
            if (watchlist.getStock().contains(stock)) {
                return "Stock already exists in watchlist";
            }
            // Saving the watchlist
            watchlist.getStock().add(stock);
            watchListRepo.save(watchlist);
            return "Stock added to watchlist successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //remove stock from watchlist
    @Transactional
    public  String deleteStockFromWatchlist(UUID watchlistid , UUID stockId){
        try {
            // Fetching the watchlist and stock
            Watchlist watchlist = watchListRepo.findById(watchlistid).orElse(null);
            if (watchlist == null) {
                return "watchlist not found";
            }
            // Fetching the stock
            Stock stock = stocksRepo.findById(stockId).orElse(null);
            if (stock == null) {
                return "Stock not found";
            }
            // Removing the stock from the watchlist
            if (!watchlist.getStock().contains(stock)) {
                return "Stock does not exist in watchlist";
            }
            // Saving the watchlist
            watchlist.getStock().remove(stock);
            watchListRepo.save(watchlist);
            return "Stock removed from watchlist successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
