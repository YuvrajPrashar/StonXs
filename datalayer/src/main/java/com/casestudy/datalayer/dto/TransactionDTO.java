package com.casestudy.datalayer.dto;

import com.casestudy.datalayer.entity.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("transactiontype")
    private String transactionType;
    @JsonProperty("price")
    private float price;
    @JsonProperty("status")
    private String status;
    @JsonProperty("stock")
    private Stock stock;
}
