package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

    public String createStock(StockDTO stockDto){
        return stockService.createStock(stockDto);
    }
    public String deleteStock(Long id){
        return stockService.deleteStock(id);
    }
    public String updateStock(StockDTO stockDto){
        return stockService.updateStock(stockDto);
    }
    public StockDTO getStock(Long id){
        return stockService.getStock(id);
    }
    public List<StockDTO> getAllStocks(){
        return stockService.getAllStocks();
    }

}
