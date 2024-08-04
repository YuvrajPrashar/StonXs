package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.NewStockDTO;
import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StockController {
    @Autowired
    StockService stockService;

    //get all stocks
    @GetMapping("/api-v1/stocks")
    public ResponseEntity<Page<StockDTO>> getStocksByPage(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "15") int pageSize){
        try {
            Page<StockDTO> res = stockService.getStocksByPages(pageNo,pageSize);
            if (res.isEmpty()) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //get stocks by category
    @GetMapping("/api-v1/stocks/{category}")
    public ResponseEntity<Page<StockDTO>> getStocksByCategory(@PathVariable String category ,@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "15") int pageSize){
        try {
            Page<StockDTO> res = stockService.getStocksByCategory(category,pageNo,pageSize);
            if (res.isEmpty()) {
                return ResponseEntity.status(404).build();
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //get stocks by search
    @GetMapping("/api-v1/search")
    public ResponseEntity<List<StockDTO>> getStocksBySearching(@RequestParam(name = "search") String stockSymbl){
        try {
            List<StockDTO> res = stockService.getStocksBySearch(stockSymbl);
            if (res.isEmpty()) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //get stock by id
    @GetMapping("/api-v1/stock/{id}")
    public ResponseEntity<StockDTO> getStock(@PathVariable("id") UUID id){
        try {
            StockDTO res = stockService.getStock(id);
            if (res == null) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //get all stocks
    @GetMapping("/api-v1/all-stocks")
    public ResponseEntity<List<StockDTO>> getAllStocks(){
        try {
            List<StockDTO> res = stockService.getAllStocks();
            if (res.isEmpty()) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //update stock
    @PatchMapping("/auth/api-v1/stock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable("id") UUID id
            , @RequestBody StockDTO stockDto,@RequestParam(name="userid") UUID userid
    ){
        try {
            String res = stockService.updateStock(id,stockDto,userid);
            if (res.contains("exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else if (res.contains("Error")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            } else if (res.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
                return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //delete stock
    @DeleteMapping("/auth/api-v1/stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable("id") UUID id, @RequestParam(name="userid") UUID userid){
        try {
            String res = stockService.deleteStock(id,userid);

            if (res.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(res);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    //create stock
    @PostMapping("/auth/api-v1/stock")
    public ResponseEntity<String> createStock(@RequestBody NewStockDTO stockDto, @RequestParam(name="userid") UUID userid){
        try {
            String res = stockService.createStock(stockDto,userid);
            if (res.contains("exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else if (res.contains("Error")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
                return ResponseEntity.ok(res);
            }
        catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }

}
