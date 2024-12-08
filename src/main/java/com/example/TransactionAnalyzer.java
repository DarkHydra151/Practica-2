package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    private final List<Transaction> transactions;

    public TransactionAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double calculateTotalBalance() {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public int countTransactionsByMonth(String monthYear) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        return (int) transactions.stream()
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    return date.format(formatter).equals(monthYear);
                })
                .count();
    }

    public List<Transaction> findTopExpenses() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public Transaction findLargestExpense() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .min(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }

    public Transaction findSmallestExpense() {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .max(Comparator.comparing(Transaction::getAmount))
                .orElse(null);
    }
}
