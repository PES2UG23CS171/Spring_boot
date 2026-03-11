package com.example.springmvc.service;

import com.example.springmvc.model.Transaction;
import com.example.springmvc.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public String saveTransaction(Transaction transaction) {
        if (transaction.getAmount() == null || transaction.getAmount() <= 0) {
            return "Amount must be positive!";
        }

        if ("Debit".equals(transaction.getTransactionType())) {
            double balance = getBalance(transaction.getWalletId());
            if (transaction.getAmount() > balance) {
                return "Insufficient balance! Current balance: " + balance;
            }
        }

        transactionRepository.save(transaction);
        return null;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public double getBalance(String walletId) {
        List<Transaction> transactions = transactionRepository.findByWalletId(walletId);
        double balance = 0.0;
        for (Transaction t : transactions) {
            if ("Credit".equals(t.getTransactionType())) {
                balance += t.getAmount();
            } else if ("Debit".equals(t.getTransactionType())) {
                balance -= t.getAmount();
            }
        }
        return balance;
    }
}
