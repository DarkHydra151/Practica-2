//package com.example;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class FinancialAnalyzer {
//    public double calculateBalance(List<Transaction> transactions) {
//        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
//    }
//
//    public int countTransactionsByMonth(List<Transaction> transactions, String month) {
//        return (int) transactions.stream()
//                .filter(t -> t.getDate().startsWith(month)) // month у форматі "2024-01"
//                .count();
//    }
//
//    public List<Transaction> findTopExpenses(List<Transaction> transactions) {
//        return transactions.stream()
//                .filter(t -> t.getAmount() < 0)
//                .sorted(Comparator.comparingDouble(Transaction::getAmount))
//                .limit(10)
//                .collect(Collectors.toList());
//    }
//
//    public Map<String, Double> analyzeExpenseCategories(List<Transaction> transactions) {
//        return transactions.stream()
//                .filter(t -> t.getAmount() < 0)
//                .collect(Collectors.groupingBy(
//                        Transaction::getDescription,
//                        Collectors.summingDouble(Transaction::getAmount)
//                ));
//    }
//}
