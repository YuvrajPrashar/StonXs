package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.NewStockDTO;
import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.entity.Stock;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.repositary.StocksRepo;
import com.casestudy.datalayer.repositary.UserRepo;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private UserRepo userRepo;

    //get stocks by category
    @Transactional
    public Page<StockDTO> getStocksByCategory(String category , int pageNo, int pageSize, String sector) {
        try {
            // Sorting the stocks by stock name
            System.out.println(category);
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
            // Fetching the stocks

            if (sector==null){
                Page<StockDTO> stockPage = stocksRepo.findAllByCategoryAndIsDeletedFalse(category, pageRequest).map(stock -> mapperUtil.mapStockToStockDto(stock));
                return stockPage;
            }
            Page<StockDTO> stockPage = stocksRepo.findAllBySectorAndCategoryAndIsDeletedFalse(sector, category, pageRequest).map(stock -> mapperUtil.mapStockToStockDto(stock));

            return stockPage;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get stocks by pages
    @Transactional
    public Page<StockDTO> getStocksByPages(int pageNo, int pageSize, String sector) {
        try {
            // Sorting the stocks by stock name
            PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("stockName").ascending());
            // Fetching the stocks
            if (sector==null){
                Page<StockDTO> stockPage = stocksRepo.findAllByIsDeletedFalse(pageRequest).map(stock -> mapperUtil.mapStockToStockDto(stock));
                return stockPage;
            }
            Page<StockDTO> stockPage = stocksRepo.findAllBySectorAndIsDeletedFalse(sector, pageRequest).map(stock -> mapperUtil.mapStockToStockDto(stock));
            return stockPage;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get stocks by search
    @Transactional
    public List<StockDTO> getStocksBySearch(String search) {
        try {
            // Fetching the stocks by search
            return  mapperUtil.mapStockListToStockDTOList(stocksRepo.findBySearch(search));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //create stock
    @Transactional
    public String createStock(NewStockDTO stock, UUID userid){
        try {
            if (!userRepo.existsById(userid)) {
                return "User not found";
            }
            User user = userRepo.findById(userid).orElse(null);
            if (!user.isAdmin()){
                return "User is not an admin";
            }
            // Saving the stock
            //Checking if stock already exists or not by stock symbol and stock name
            if(stocksRepo.existsByStockSymbol(stock.getStockSymbol()) || stocksRepo.existsByStockName(stock.getStockName())){
                return "Stock already exists";
            }
            stocksRepo.save(mapperUtil.mapNewStockDtoToStock(stock));
            return "Stock created successfully";
        } catch (Exception e) {
                return "Error creating stock";
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

    //delete stock
    @Transactional
    public String deleteStock(UUID id,UUID userid){
        try {

            if (!userRepo.existsById(userid)) {
                return "User not found";
            }
            User user = userRepo.findById(userid).orElse(null);
            if (!user.isAdmin()){
                return "User is not an admin";
            }

            // Fetching the stock
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

    //update stock
    @Transactional
    public String updateStock(UUID id,StockDTO stockDto,UUID userid){
        try {

            if (!userRepo.existsById(userid)) {
                return "User not found";
            }
            User user = userRepo.findById(userid).orElse(null);
            if (!user.isAdmin()){
                return "User is not an admin";
            }
            Stock stock = stocksRepo.findById(id).orElse(null);
            if(stock == null){
                return "Stock not found";
            }
            System.out.println(stock.getStockName());
            //Checking if stock already exists or not by stock symbol and stock name
            if(stocksRepo.existsByStockSymbol(stockDto.getStockSymbol()) || stocksRepo.existsByStockName(stockDto.getStockName())){
                return "Stock already exists";
            }
            if (stockDto.getStockSymbol() != null){
            stock.setStockSymbol(stockDto.getStockSymbol());}
            if (stockDto.getStockName() != null){
            stock.setStockName(stockDto.getStockName());}
            if (stockDto.getSector() != null){
            stock.setSector(stockDto.getSector());}
            stocksRepo.save(stock);
            return "Stock updated successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //get all stocks
    @Transactional
    public List<StockDTO> getAllStocks(){
        try {
            return mapperUtil.mapStockListToStockDTOList(stocksRepo.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
