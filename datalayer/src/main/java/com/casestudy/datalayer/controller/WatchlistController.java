package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.WatchlistDTO;
import com.casestudy.datalayer.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @PatchMapping("/stock/{stockId}/watchlist/{userId}")
    public String addStockToWatchlist(@PathVariable("stockId") UUID stockId, @PathVariable("userId") UUID userId) {
        try {
            return watchlistService.addStockToWatchlist(stockId, userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/watchlist/{watchlistid}/stock/{stockId}")
    public String removeStockFromWatchlist(@PathVariable("watchlistid") UUID id, @PathVariable("stockId") UUID stockId) {
        try {
            return watchlistService.deleteStockFromWatchlist(id, stockId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/{id}/watchlist")
    public WatchlistDTO getWatchlistByUserId(@PathVariable("id") UUID id) {
        try {
            return watchlistService.getWatchlistByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/watchlist")
    public List<WatchlistDTO> getAllWatchlists() {
        try {
            return watchlistService.getAllWatchlists();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/watchlist/{id}")
    public WatchlistDTO getWatchlist(@PathVariable("id") UUID id) {
        try {
            return watchlistService.getWatchlist(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
