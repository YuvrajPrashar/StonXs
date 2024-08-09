package com.casestudy.datalayer.service;

import com.casestudy.datalayer.MapperUtil;
import com.casestudy.datalayer.dto.TransactionDTO;
import com.casestudy.datalayer.entity.*;
import com.casestudy.datalayer.repositary.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;
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

    @Autowired
    private HoldingsRepo holdingsRepo;

    //create new transaction
    @Transactional
    public String createTransactionByUserIdAndStockId(UUID userid, UUID stockid, TransactionDTO transactionDTO) {

        try {
            // Fetching the user and stock
            User user = userRepo.findById(userid).orElse(null);
            Stock stock = stockRepo.findById(stockid).orElse(null);


            if (user == null || stock == null) {
                return null;
            }

            // Fetching the portfolio and details
            Portfolio portfolio = user.getPortfolio();
            long quantity = transactionDTO.getQuantity();
            String transactionType = transactionDTO.getTransactionType();
            float price = transactionDTO.getPrice();

            // Creating a new transaction
            Transactions transaction = new Transactions();
            transaction.setUser(user);
            transaction.setPortfolio(portfolio);
            transaction.setStock(stock);
            transaction.setQuantity(quantity);
            transaction.setPrice(price);
            transaction.setTransactionType(transactionType);
            transaction.setStatus("In-Progress");

            // Executing the transaction
            if (transactionType.equals("buy")) {
                if (portfolio.getBalance() < quantity * price) {
                    transaction.setStatus("Cancelled");
                    transactionRepo.save(transaction);
                    return "Insufficient balance";
                } else {
                    transaction.setStatus("In-Progress");
                    transactionRepo.save(transaction);
                }
                return "Transaction in progress";
            } else if (transactionType.equals("sell")) {
                List<Holdings> holdings = holdingsRepo.findByPortfolio(portfolio);
                Holdings holdingToUpdate = null;

                // searching for the holdings
                if (holdings != null) {
                    for (Holdings holding : holdings) {
                        if (holding.getStocks().getStockId().equals(stock.getStockId())) {
                            holdingToUpdate = holding;
                            break;
                        }
                    }
                }

                // Checking if the user has enough holdings
                if (holdingToUpdate == null || holdingToUpdate.getQuantity() < quantity) {
                    transaction.setStatus("Cancelled");
                    transactionRepo.save(transaction);
                    return "Insufficient holdings";
                } else {
                    transaction.setStatus("In-Progress");
                    transactionRepo.save(transaction);
                }
                return "Transaction in progress";
            } else {
                return "Invalid transaction type";
            }
        } catch (Exception e) {
            return "Transaction failed";
        }
    }

    //get all transactions of a user
    @Transactional
    public List<TransactionDTO> getTransactionByUserId(UUID userid) {
        try {
            User user = userRepo.findById(userid).orElse(null);
            if (user == null) {
                return null;
            }

            return mapperUtil.mapTransactionsListToTransactionsDTOList(transactionRepo.findAllByUserOrderByCreatedOnDesc(user));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void TransactionManager() {
        try {
            List<Transactions> transactions = transactionRepo.findBystatus("In-Progress");
            for(Transactions transactions1 : transactions){

                // Fetching the user, stock and portfolio

                User user = transactions1.getUser();
                Stock stock = transactions1.getStock();
                Portfolio portfolio = user.getPortfolio();
                long quantity = transactions1.getQuantity();
                String transactionType = transactions1.getTransactionType();
                float price = transactions1.getPrice();
                Transactions transaction = transactions1;


                if (transactionType.equals("buy")) {
                    if (portfolio.getBalance() < quantity * price) {
                        transaction.setStatus("Cancelled");
                        transactionRepo.save(transaction);
                    }

                    // Updating the holdings
                    List<Holdings> holdings = holdingsRepo.findByPortfolio(portfolio);
                    Holdings holdingToUpdate = null;

                    if (holdings != null) {
                        for (Holdings holding : holdings) {
                            if (holding.getStocks().getStockId().equals(stock.getStockId())) {
                                holdingToUpdate = holding;
                                break;
                            }
                        }
                    }

                    if (holdingToUpdate == null) {
                        Holdings newHolding = new Holdings();
                        newHolding.setPortfolio(portfolio);
                        newHolding.setStocks(stock);
                        newHolding.setQuantity((int) quantity);
                        holdingsRepo.save(newHolding);
                    } else {
                        holdingToUpdate.setQuantity(holdingToUpdate.getQuantity() + (int) quantity);
                        holdingsRepo.save(holdingToUpdate);
                    }

                    portfolio.setBalance((int) (portfolio.getBalance() - quantity * price));
                    portfolio.setInvestedValue((int) (portfolio.getInvestedValue() + quantity * price));
                    portfolioRepo.save(portfolio);

                    transaction.setStatus("Completed");
                    transactionRepo.save(transaction);
                    stock.setMarketCap(stock.getMarketCap().subtract(new BigInteger(String.valueOf(quantity))));
                    stockRepo.save(stock);
                } else if (transactionType.equals("sell")) {
                    List<Holdings> holdings = holdingsRepo.findByPortfolio(portfolio);
                    Holdings holdingToUpdate = null;
                    // Searching in  the holdings
                    if (holdings != null) {
                        for (Holdings holding : holdings) {
                            if (holding.getStocks().getStockId().equals(stock.getStockId())) {
                                holdingToUpdate = holding;
                                break;
                            }
                        }
                    }

                    // Checking if the user has enough holdings
                    if (holdingToUpdate == null || holdingToUpdate.getQuantity() < quantity) {
                        transaction.setStatus("Cancelled");
                        transactionRepo.save(transaction);
                    }

                    holdingToUpdate.setQuantity(holdingToUpdate.getQuantity() - (int) quantity);
                    holdingsRepo.save(holdingToUpdate);

                    portfolio.setBalance((int) (portfolio.getBalance() + quantity * price));
                    portfolio.setInvestedValue((int) (portfolio.getInvestedValue() - quantity * price));
                    portfolioRepo.save(portfolio);

                    transaction.setStatus("Completed");
                    transactionRepo.save(transaction);
                    stock.setMarketCap(stock.getMarketCap().add(new BigInteger(String.valueOf(quantity))));
                    stockRepo.save(stock);

                } else {
                    transaction.setStatus("Cancelled");
                    transactionRepo.save(transaction);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
