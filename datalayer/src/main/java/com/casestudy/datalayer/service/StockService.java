package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.entity.Stock;
import com.casestudy.datalayer.repositary.StocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class StockService {
    @Autowired
    StocksRepo stocksRepo;
    @Autowired
    MapperUtil mapperUtil;

    public String createStock(StockDTO stock){
        try {
            stocksRepo.save(mapperUtil.mapStockDtoToStock(stock));
            return "Stock created successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public StockDTO getStock(UUID id){
        try {
            Stock stock = stocksRepo.findById(id).orElse(null);
            if(Objects.isNull(stock)){
                return null;
            }
            return mapperUtil.mapStockToStockDto(stock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String deleteStock(UUID id){
        try {
            Stock stock = stocksRepo.findById(id).orElse(null);
            if(stock == null){
                return "Stock not found";
            }
            stock.setDeleted(true);
            return "Stock deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String updateStock(StockDTO stockDto){
        try {
            Stock stock =mapperUtil.mapStockDtoToStock(stockDto);
            Stock stock1 = stocksRepo.findById(stock.getStockId()).orElse(null);
            if(stock1 == null){
                return "Stock not found";
            }
            stocksRepo.save(stock);
            return "Stock updated successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<StockDTO> getAllStocks(){
        try {
            return mapperUtil.mapStockListToStockDTOList(stocksRepo.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
