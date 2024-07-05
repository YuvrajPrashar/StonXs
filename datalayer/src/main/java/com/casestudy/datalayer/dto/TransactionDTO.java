package com.casestudy.datalayer.dto;

import com.casestudy.datalayer.entity.Stock;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("transactionType")
    private String transactionType;
    @JsonProperty("price")
    private float price;
}
