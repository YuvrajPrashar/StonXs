package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Stock;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StocksRepo extends JpaRepository<Stock, UUID> {
    List<Stock> findAllByCategory(String category);

    @Override
    <S extends Stock> Page<S> findAll(Example<S> example, Pageable pageable);
}
