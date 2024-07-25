package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Stock;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StocksRepo extends JpaRepository<Stock, UUID>   {
    List<Stock> findAllByCategory(String category);

    Page<Stock> findAll(Pageable pageable);

    Page<Stock> findAllByCategory(String category, Pageable pageable);

}
