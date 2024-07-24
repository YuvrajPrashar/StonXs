package com.casestudy.datalayer.dto;

import com.casestudy.datalayer.entity.Holdings;
import com.casestudy.datalayer.entity.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDTO {

    @JsonProperty("investedvalue")
    private int investedvalue;
    @JsonProperty("currentValue")
    private int currentValue;
    @JsonProperty("pnl")
    private int pnl;
    @JsonProperty("stocks")
    private List<StockDTO> stocks;
    @JsonProperty("balance")
    private int balance;

    public PortfolioDTO(int investedValue, int balance) {
        this.investedvalue = investedValue;
        this.balance = balance;
    }
}
