package com.casestudy.datalayer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    @JsonProperty("stockname")
  private String stockName;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("stocksymbol")
    private String stockSymbol;
    @JsonProperty("currentprice")
    private String currentPrice;
  }
