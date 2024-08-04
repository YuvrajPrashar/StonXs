package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Stock;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StocksRepo extends JpaRepository<Stock, UUID>   {

    Boolean existsByStockSymbol(String stockSymbol);

    Boolean existsByStockName(String stockName);

    Stock findByStockSymbol(String stockSymbol);

    Stock findByStockName(String stockName);

    List<Stock> findAllByCategory(String category);

    Page<Stock> findAll(Pageable pageable);

    Page<Stock> findAllByCategory(String category, Pageable pageable);

    @Query(value = "SELECT * FROM practice.stock s WHERE s.stock_symbol ILIKE :searchString% OR s.stock_name ILIKE :searchString% ", nativeQuery = true)
    List<Stock> findBySearch(@Param("searchString") String searchString);

    // Custom methods to filter by isDeleted
    List<Stock> findByIsDeletedFalse();

    List<Stock> findAllByCategoryAndIsDeletedFalse(String category);

    Page<Stock> findAllByCategoryAndIsDeletedFalse(String category, Pageable pageable);

    Page<Stock> findAllBySectorAndIsDeletedFalse(String sector, Pageable pageable);

    Page<Stock> findAllBySectorAndCategoryAndIsDeletedFalse(String sector, String category, Pageable pageable);

    Page<Stock> findAllByIsDeletedFalse(Pageable pageable);

    @Query(value = "SELECT * FROM practice.stock s WHERE (s.stock_symbol ILIKE :searchString% OR s.stock_name ILIKE :searchString%) AND s.is_deleted = false", nativeQuery = true)
    List<Stock> findBySearchAndNotDeleted(@Param("searchString") String searchString);

    @Query(value = "SELECT * FROM practice.stock s WHERE (s.stock_symbol ILIKE :searchString% OR s.stock_name ILIKE :searchString%) AND s.is_deleted = false", nativeQuery = true)
    Page<Stock> findBySearchAndNotDeleted(@Param("searchString") String searchString, Pageable pageable);

}
