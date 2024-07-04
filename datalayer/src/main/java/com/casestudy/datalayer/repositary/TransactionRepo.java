package com.casestudy.datalayer.repositary;


import com.casestudy.datalayer.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transactions, Long> {
}
