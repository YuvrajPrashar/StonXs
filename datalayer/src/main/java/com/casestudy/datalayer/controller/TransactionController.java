package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.TransactionDTO;
import com.casestudy.datalayer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/user/{userid}/stock/{stockid}")
    public String getTransactionByUserIdAndStockId(@PathVariable("userid") UUID userid, @PathVariable("stockid") UUID stockid, @RequestBody TransactionDTO transactionDTO) {
        try {
            return transactionService.getTransactionByUserIdAndStockId(userid, stockid, transactionDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/transaction/{id}")
    public String deleteTransaction(@PathVariable("id") UUID id) {
        try {
            return transactionService.deleteTransaction(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/transactions/{userid}")
    public TransactionDTO getTransactionByUserId(@PathVariable("userid") UUID userid) {
        try {
            return transactionService.getTransactionByUserId(userid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
