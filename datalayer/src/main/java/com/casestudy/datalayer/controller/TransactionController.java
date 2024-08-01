package com.casestudy.datalayer.controller;

import com.casestudy.datalayer.dto.TransactionDTO;
import com.casestudy.datalayer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    //create new transaction
    @PostMapping("auth/api-v1/transaction/user/{userid}/stock/{stockid}")
    public ResponseEntity<String> createTransactionByUserIdAndStockId(@PathVariable("userid") UUID userid, @PathVariable("stockid") UUID stockid, @RequestBody TransactionDTO transactionDTO) {
        try {
            String res = transactionService.getTransactionByUserIdAndStockId(userid, stockid, transactionDTO);
            if (res == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }if (res.equals("Insufficient balance") || res.equals("Insufficient quantity") || res.equals("Transaction failed") || res.equals("Invalid transaction type") || res.equals("Invalid transaction type")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong internally");
        }
    }

    //get user transactions
    @GetMapping("auth/api-v1/transactions/{userid}")
    public ResponseEntity<List<TransactionDTO>> getTransactionByUserId(@PathVariable("userid") UUID userid) {
        try {
            List<TransactionDTO> res = transactionService.getTransactionByUserId(userid);
            if (res.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
