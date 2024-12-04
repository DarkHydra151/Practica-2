package com.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionReportGenerator {

    public void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public void printSummaryByCategory(List<Transaction> transactions) {
        System.out.println("Сумарні витрати по категоріях:");
        Map<String, Double> categoryExpenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getDescription, Collectors.summingDouble(Transaction::getAmount)));

        categoryExpenses.forEach((category, sum) -> {
            int symbols = (int) Math.ceil(Math.abs(sum) / 1000);
            System.out.println(category + ": " + "*".repeat(symbols) + " (" + sum + " грн)");
        });
    }

    public void printSummaryByMonth(List<Transaction> transactions) {
        System.out.println("Сумарні витрати по місяцях:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        Map<String, Double> monthlyExpenses = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).format(formatter),
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        monthlyExpenses.forEach((month, sum) -> {
            int symbols = (int) Math.ceil(Math.abs(sum) / 1000);
            System.out.println(month + ": " + "*".repeat(symbols) + " (" + sum + " грн)");
        });
    }
}
