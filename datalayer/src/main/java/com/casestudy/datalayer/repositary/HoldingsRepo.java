package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Holdings;
import com.casestudy.datalayer.entity.Portfolio;
import com.casestudy.datalayer.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HoldingsRepo extends JpaRepository<Holdings, UUID> {
    List<Holdings> findByPortfolio(Portfolio portfolio);

}
