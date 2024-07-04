package com.casestudy.datalayer;

import com.casestudy.datalayer.dto.StockDTO;
import com.casestudy.datalayer.dto.UserDTO;
import com.casestudy.datalayer.entity.Stock;
import com.casestudy.datalayer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapperUtil {
    public User mapUserDtoToUser(UserDTO userDTO){
        return new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
    }
    public UserDTO mapUserToUserDto(User user){
        return new UserDTO(user.getUsername(), user.getPassword(), user.getEmail());
    }
    public List<User> mapUserDTOListToUserList(List<UserDTO> userDTOList){
        return userDTOList.stream().map(this::mapUserDtoToUser).toList();
    }
    public List<UserDTO> mapUserListToUserDTOList(List<User> userList){
        return userList.stream().map(this::mapUserToUserDto).toList();
    }
    public Stock mapStockDtoToStock(StockDTO stockDTO){
        return new Stock(stockDTO.getStockName(), stockDTO.getSector(), stockDTO.getStockSymbol(), stockDTO.getCurrentPrice());
    }
    public StockDTO mapStockToStockDto(Stock stock){
        return new StockDTO(stock.getStockName(), stock.getSector(), stock.getStockSymbol(), stock.getCurrentPrice());
    }
    public List<Stock> mapStockDTOListToStockList(List<StockDTO> stockDTOList){
        return stockDTOList.stream().map(this::mapStockDtoToStock).toList();
    }
    public List<StockDTO> mapStockListToStockDTOList(List<Stock> stockList){
        return stockList.stream().map(this::mapStockToStockDto).toList();
    }
}
