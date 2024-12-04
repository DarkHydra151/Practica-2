//package com.example;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TransactionCSVReader {
//    public List<Transaction> readTransactions(String filePath) {
//        List<Transaction> transactions = new ArrayList<>();
//        try {
//            URL url = new URL(filePath);
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    String[] values = line.split(",");
//                    if (values.length < 3) {
//                        System.out.println("Помилка у рядку: " + line);
//                        continue;
//                    }
//
//                    try {
//                        double amount = Double.parseDouble(values[1]);
//                        Transaction transaction = new Transaction(values[0], amount, values[2]);
//                        transactions.add(transaction);
//                    } catch (NumberFormatException e) {
//                        System.out.println("Некоректний формат суми у рядку: " + line);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return transactions;
//    }
//}
