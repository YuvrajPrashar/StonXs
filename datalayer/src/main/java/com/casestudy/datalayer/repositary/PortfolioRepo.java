package com.casestudy.datalayer.repositary;

import com.casestudy.datalayer.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {
}
