package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.WatchlistDTO;
import com.casestudy.datalayer.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class WatchlistController {
    @Autowired
    private WatchlistService watchlistService;

    //add stock to watchlist
    @PatchMapping("auth/api-v1/stock/{stockId}/watchlist/{watchlisId}")
    public ResponseEntity<String> addStockToWatchlist(@PathVariable("stockId") UUID stockId, @PathVariable("watchlisId") UUID watchlisId) {
        try {
            String res = watchlistService.addStockToWatchlist(stockId, watchlisId);
            if (res == null) {
                return ResponseEntity.notFound().build();
            }
            if (res.contains("not found") || res.equals("Stock already exists")) {
                return ResponseEntity.badRequest().body(res);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong internally");
        }
    }

    //remove stock from watchlist
    @PatchMapping("auth/api-v1/watchlist/{watchlistid}/stock/{stockId}")
    public ResponseEntity<String> removeStockFromWatchlist(@PathVariable("watchlistid") UUID id, @PathVariable("stockId") UUID stockId) {
        try {
            String res = watchlistService.deleteStockFromWatchlist(id, stockId);
            if (res == null) {
                return ResponseEntity.notFound().build();
            }  if (res.contains("not found") || res.equals("does not exists")) {
                return ResponseEntity.badRequest().body(res);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong internally");
        }
    }
    //get watchlist by user id
    @GetMapping("auth/api-v1/user/{id}/watchlist")
    public ResponseEntity<?> getWatchlistByUserId(@PathVariable("id") UUID id) {
        try {
            WatchlistDTO res= watchlistService.getWatchlistByUserId(id);
            if (res == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Watchlist not found");
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong internally");
        }
    }
    //get all watchlists
    @GetMapping("auth/api-v1/watchlist")
    public ResponseEntity<List<WatchlistDTO>> getAllWatchlists() {
        try {
            List<WatchlistDTO> res = watchlistService.getAllWatchlists();
            if (res.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //get watchlist by watchlist id
    @GetMapping("auth/api-v1/watchlist/{watchlistId}")
    public ResponseEntity<WatchlistDTO> getWatchlist(@PathVariable("watchlistId") UUID id) {
        try {
            WatchlistDTO res = watchlistService.getWatchlist(id);
            if (res == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
