package com.casestudy.datalayer;

import com.casestudy.datalayer.dto.*;
import com.casestudy.datalayer.entity.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class MapperUtil {

    public User mapUserDtoToUser(UserDTO userDTO){
        return new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail() , userDTO.getFullname());
    }
    public UserDTO mapUserToUserDto(User user){
        return new UserDTO(user.getFullname(), user.getUsername(), user.getPassword(), user.getEmail());
    }
    public List<User> mapUserDTOListToUserList(List<UserDTO> userDTOList){
        return userDTOList.stream().map(this::mapUserDtoToUser).toList();
    }
    public List<UserDTO> mapUserListToUserDTOList(List<User> userList){
        return userList.stream().map(this::mapUserToUserDto).toList();
    }
    public Stock mapStockDtoToStock(StockDTO stockDTO){
        return new Stock(stockDTO.getStockId(),stockDTO.getStockName(), stockDTO.getSector(), stockDTO.getStockSymbol(), stockDTO.getCurrentPrice());
    }
    public StockDTO mapStockToStockDto(Stock stock){
        return new StockDTO(stock.getStockId(),stock.getStockName(), stock.getSector(), stock.getStockSymbol(), stock.getCurrentPrice());
    }
    public List<Stock> mapStockDTOListToStockList(List<StockDTO> stockDTOList){
        return stockDTOList.stream().map(this::mapStockDtoToStock).toList();
    }
    public List<StockDTO> mapStockListToStockDTOList(List<Stock> stockList){
        return stockList.stream().map(this::mapStockToStockDto).toList();
    }
    public PortfolioDTO mapPortfolioToPortfolioDTO(Portfolio portfolio){
        return new PortfolioDTO(portfolio.getInvestedValue(),portfolio.getBalance());
    }
    public Portfolio mapPortfolioDtoToPortfolio(PortfolioDTO portfolioDTO){
        return new Portfolio(portfolioDTO.getInvestedvalue(),portfolioDTO.getBalance());
    }


    public List<PortfolioDTO> mapPortfolioListToPortfolioDTOList(List<Portfolio> portfolioList){
        return portfolioList.stream().map(this::mapPortfolioToPortfolioDTO).toList();
    }
    public List<Portfolio> mapPortfolioDTOListToPortfolioList(List<PortfolioDTO> portfolioDTOList){
        return portfolioDTOList.stream().map(this::mapPortfolioDtoToPortfolio).toList();
    }
    public WatchlistDTO mapWatchlistToWatchlistDTO(Watchlist watchlist){
        return new WatchlistDTO(mapStockListToStockDTOList(watchlist.getStock().isEmpty() ? Collections.emptyList() : watchlist.getStock()));
    }
    public Watchlist mapWatchlistDtoToWatchlist(WatchlistDTO watchlistDTO){
        return new Watchlist(mapStockDTOListToStockList(watchlistDTO.getStocks()));
    }
    public List<WatchlistDTO> mapWatchlistListToWatchlistDTOList(List<Watchlist> watchlistList){
        return watchlistList.stream().map(this::mapWatchlistToWatchlistDTO).toList();
    }
    public List<Watchlist> mapWatchlistDTOListToWatchlistList(List<WatchlistDTO> watchlistDTOList){
        return watchlistDTOList.stream().map(this::mapWatchlistDtoToWatchlist).toList();
    }

    public TransactionDTO mapTransactionsToTransactionsDTO(Transactions transactions){
        return new TransactionDTO(transactions.getQuantity(),transactions.getTransactionType(), transactions.getPrice(), transactions.getStatus(),transactions.getStock());
    }

    public Transactions mapTransactionsDtoToTransactions(TransactionDTO transactionDTO){
        return new Transactions(transactionDTO.getQuantity(), transactionDTO.getTransactionType(), transactionDTO.getPrice(), transactionDTO.getStatus() , transactionDTO.getStock());
    }

    public List<Transactions> mapTransactionsDTOListToTransactionsList(List<TransactionDTO> transactionDTOList){
        return transactionDTOList.stream().map(this::mapTransactionsDtoToTransactions).toList();
    }

    public List<TransactionDTO> mapTransactionsListToTransactionsDTOList(List<Transactions> transactionsList){
        return transactionsList.stream().map(this::mapTransactionsToTransactionsDTO).toList();
    }

}
