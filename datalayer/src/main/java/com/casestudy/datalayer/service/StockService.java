package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.entity.Stock;
import com.casestudy.datalayer.repositary.StocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StockService {
    @Autowired
    StocksRepo stocksRepo;
    @Autowired
    MapperUtil mapperUtil;

    public String createStock(StockDTO stock){
        stocksRepo.save(mapperUtil.mapStockDtoToStock(stock));
        return "Stock created successfully";
    }
    public StockDTO getStock(Long id){
        Stock stock = stocksRepo.findById(id).orElse(null);
        if(Objects.isNull(stock)){
            return null;
        }
        return mapperUtil.mapStockToStockDto(stock);
    }
    public String deleteStock(Long id){
        Stock stock = stocksRepo.findById(id).orElse(null);
        if(stock == null){
            return "Stock not found";
        }
        stock.setDeleted(true);
        return "Stock deleted successfully";
    }
    public String updateStock(StockDTO stockDto){
        Stock stock =mapperUtil.mapStockDtoToStock(stockDto);
        Stock stock1 = stocksRepo.findById(stock.getStockId()).orElse(null);
        if(stock1 == null){
            return "Stock not found";
        }
        stocksRepo.save(stock);
        return "Stock updated successfully";
    }
    public List<StockDTO> getAllStocks(){
        return mapperUtil.mapStockListToStockDTOList(stocksRepo.findAll());
    }


}
