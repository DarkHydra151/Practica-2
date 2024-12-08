package com.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        DataReader<Transaction> csvReader = new CSVReader();
        List<Transaction> transactions = csvReader.read(filePath);

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        TransactionReportGenerator reportGenerator = new TransactionReportGenerator();

        double totalBalance = analyzer.calculateTotalBalance();
        reportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = analyzer.countTransactionsByMonth(monthYear);
        reportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = analyzer.findTopExpenses();
        reportGenerator.printTopExpensesReport(topExpenses);

        Transaction largestExpense = analyzer.findLargestExpense();
        if (largestExpense != null) {
            System.out.println("Найбільша витрата: " + largestExpense.getDescription() + " (" + largestExpense.getAmount() + ")");
        } else {
            System.out.println("Найбільшу витрату не знайдено.");
        }

        Transaction smallestExpense = analyzer.findSmallestExpense();
        if (smallestExpense != null) {
            System.out.println("Найменша витрата: " + smallestExpense.getDescription() + " (" + smallestExpense.getAmount() + ")");
        } else {
            System.out.println("Найменшу витрату не знайдено.");
        }

        reportGenerator.printSummaryByCategory(transactions);
        reportGenerator.printSummaryByMonth(transactions);
    }
}
