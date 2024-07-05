package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("/stock")
    public String createStock(@RequestBody StockDTO stockDto){
        try {
            return stockService.createStock(stockDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/stock/{id}")
    public StockDTO getStock(@PathVariable("id") UUID id){
        try {
            return stockService.getStock(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/stocks")
    public List<StockDTO> getAllStocks(){
        try {
            return stockService.getAllStocks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("/stock/{id}")
    public String updateStock(@PathVariable("id") UUID id, @RequestBody StockDTO stockDto){
        try {
            return stockService.updateStock(id,stockDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/stock/{id}")
    public String deleteStock(@PathVariable("id") UUID id){
        try {
            return stockService.deleteStock(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
