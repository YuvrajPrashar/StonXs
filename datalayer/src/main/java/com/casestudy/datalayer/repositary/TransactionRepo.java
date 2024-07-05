package com.casestudy.datalayer.repositary;


import com.casestudy.datalayer.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepo extends JpaRepository<Transactions, UUID> {
}
