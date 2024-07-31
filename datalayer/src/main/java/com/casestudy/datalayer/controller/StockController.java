package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("/api-v1/stocks")
    public Page<StockDTO> getStocksByPage(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "15") int pageSize){
        try {
            return stockService.getStocksByPages(pageNo, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/api-v1/stocks/{category}")
    public Page<StockDTO> getStocksByCategory(@PathVariable String category ,@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "15") int pageSize){
        try {
            return stockService.getStocksByCategory(category,pageNo,pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/auth/api-v1")
    public List<StockDTO> getStocksBySearching(@RequestParam(name = "search") String stockSymbl){
        try {
            return stockService.getStocksBySearch(stockSymbl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/api-v1/stock/{id}")
    public StockDTO getStock(@PathVariable("id") UUID id){
        try {
            return stockService.getStock(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @PatchMapping("/auth/api-v1/stock/{id}")
    public String updateStock(@PathVariable("id") UUID id, @RequestBody StockDTO stockDto){
        try {
            return stockService.updateStock(id,stockDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/auth/api-v1/stock/{id}")
    public String deleteStock(@PathVariable("id") UUID id){
        try {
            return stockService.deleteStock(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/auth/api-v1/stock")
    public String createStock(@RequestBody StockDTO stockDto){
        try {
            return stockService.createStock(stockDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/api-v1/all-stocks")
    public List<StockDTO> getAllStocks(){
        try {
            return stockService.getAllStocks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
