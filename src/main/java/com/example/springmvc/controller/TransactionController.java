package com.example.springmvc.controller;

import com.example.springmvc.model.Transaction;
import com.example.springmvc.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "index";
    }

    @PostMapping("/save")
    public String saveTransaction(@ModelAttribute Transaction transaction, Model model) {
        String error = transactionService.saveTransaction(transaction);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("transaction", transaction);
            model.addAttribute("transactions", transactionService.getAllTransactions());
            return "index";
        }
        return "redirect:/";
    }
}
