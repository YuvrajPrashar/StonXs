package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepo extends JpaRepository<Stock, Long> {
}
