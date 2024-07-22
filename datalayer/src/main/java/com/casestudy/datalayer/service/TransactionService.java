package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.TransactionDTO;
import com.casestudy.datalayer.entity.Portfolio;
import com.casestudy.datalayer.entity.Stock;
import com.casestudy.datalayer.entity.Transactions;
import com.casestudy.datalayer.entity.User;
import com.casestudy.datalayer.repositary.PortfolioRepo;
import com.casestudy.datalayer.repositary.StocksRepo;
import com.casestudy.datalayer.repositary.TransactionRepo;
import com.casestudy.datalayer.repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private StocksRepo stockRepo;

    @Autowired
    private MapperUtil mapperUtil;

    public String getTransactionByUserIdAndStockId(UUID userid, UUID stockid, TransactionDTO transactionDTO) {

        try {
            User user = userRepo.findById(userid).orElse(null);
            Stock stock = stockRepo.findById(stockid).orElse(null);

            if (user == null || stock == null) {
                return null;
            }

            Portfolio portfolio = user.getPortfolio();

            long quantity = transactionDTO.getQuantity();
            String transactiontype = transactionDTO.getTransactionType();
            float price = transactionDTO.getPrice();

            Transactions transaction = new Transactions();
            transaction.setPortfolio(portfolio);
            transaction.setStock(stock);
            transaction.setQuantity(quantity);
            transaction.setPrice(price);
            transaction.setTransactionType(transactiontype);
            transaction.setStatus("Completed");

            if (transactiontype.equals("buy")) {
                if (portfolio.getBalance() < quantity * price) {
                    transaction.setStatus("Cancelled");
                    return "Insufficient balance";
                }
                portfolio.getStock().add(stock);
                portfolio.setBalance((int) (portfolio.getBalance() - quantity * price));
                portfolio.setTotalvalue(portfolio.getTotalvalue() + (int) (quantity * price));
                portfolioRepo.save(portfolio);
                transactionRepo.save(transaction);
                stock.setMarketCap(stock.getMarketCap().subtract(new BigInteger(String.valueOf(quantity))));
                return "Transaction successful";
            } else if (transactiontype.equals("sell")) {
                portfolio.getStock().remove(stock);
                portfolio.setBalance((int) (portfolio.getBalance() + quantity * price));
                portfolio.setTotalvalue(portfolio.getTotalvalue() - (int) (quantity * price));
                portfolioRepo.save(portfolio);
                transactionRepo.save(transaction);
                stock.setMarketCap(stock.getMarketCap().add(new BigInteger(String.valueOf(quantity))));
                return "Transaction successful";
            }

            else {
                return "Invalid transaction type";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String deleteTransaction(UUID id) {
        try {
            Transactions transaction = transactionRepo.findById(id).orElse(null);
            if (transaction == null) {
                return "Transaction not found";
            }
            transaction.setDeleted(true);
            transactionRepo.save(transaction);
            return "Transaction deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionDTO getTransactionByUserId(UUID userid) {
        try {
            User user = userRepo.findById(userid).orElse(null);
            if (user == null) {
                return null;
            }
            Portfolio portfolio = user.getPortfolio();
            return mapperUtil.mapTransactionToTransactionDTO(portfolio.getTransactions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
