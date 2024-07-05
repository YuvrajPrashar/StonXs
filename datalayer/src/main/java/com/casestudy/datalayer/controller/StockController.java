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

    @PostMapping("/stock/{id}")
    public String createStock(StockDTO stockDto){
        return stockService.createStock(stockDto);
    }

    @DeleteMapping("/stock/{id}")
    public String deleteStock(UUID id){
        return stockService.deleteStock(id);
    }
    @PatchMapping("/stock/{id}")
    public String updateStock(StockDTO stockDto){
        return stockService.updateStock(stockDto);
    }
    @GetMapping("/stock/{id}")
    public StockDTO getStock(UUID id){
        return stockService.getStock(id);
    }
    @GetMapping("/stocks")
    public List<StockDTO> getAllStocks(){
        return stockService.getAllStocks();
    }

}
