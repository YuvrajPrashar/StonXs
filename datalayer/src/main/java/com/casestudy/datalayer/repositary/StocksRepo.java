package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StocksRepo extends JpaRepository<Stock, UUID> {
}
