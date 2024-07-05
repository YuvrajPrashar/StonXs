package com.casestudy.datalayer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDTO {
    @JsonProperty("totalvalue")
    private int totalvalue;
    @JsonProperty("profit")
    private int profit;
    @JsonProperty("loss")
    private int loss;
    @JsonProperty("stocks")
    private List<StockDTO> stocks;
    @JsonProperty("balance")
    private int balance;
}
