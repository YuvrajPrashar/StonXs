package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.WatchlistDTO;
import com.casestudy.datalayer.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    @GetMapping("/watchlist/{id}")
    public WatchlistDTO getWatchlist(@PathVariable("id") UUID id) {
        return watchlistService.getWatchlist(id);
    }
    @GetMapping("/user/{id}/watchlist")
    public WatchlistDTO getWatchlistByUserId(@PathVariable("id") UUID id) {
        return watchlistService.getWatchlistByUserId(id);
    }
    @GetMapping("/watchlist")
    public List<WatchlistDTO> getAllWatchlists() {
        return watchlistService.getAllWatchlists();
    }
}
