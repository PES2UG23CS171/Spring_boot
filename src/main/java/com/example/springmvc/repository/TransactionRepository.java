package com.example.springmvc.repository;

import com.example.springmvc.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletId(String walletId);
}
