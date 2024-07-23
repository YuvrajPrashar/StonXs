package com.casestudy.datalayer.repositary;


import com.casestudy.datalayer.entity.Transactions;
import com.casestudy.datalayer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TransactionRepo extends JpaRepository<Transactions, UUID> {
    List<Transactions> findAllByUser(User user);
}
