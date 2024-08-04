package com.casestudy.datalayer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewStockDTO {
    @JsonProperty("stockid")
    private UUID stockId;
    @JsonProperty("stockname")
    private String stockName;
    @JsonProperty("stocksymbol")
    private String stockSymbol;
    @JsonProperty("sector")
    private String sector;
    @JsonProperty("companyvaluation")
    private BigInteger companyValuation;
    @JsonProperty("marketcap")
    private BigInteger marketCap;
}
