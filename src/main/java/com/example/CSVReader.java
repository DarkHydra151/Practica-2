package com.example;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements DataReader<Transaction> {
    @Override
    public List<Transaction> read(String source) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                source.startsWith("http") ? new URL(source).openStream() : new FileInputStream(source)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        String date = parts[0].trim();
                        double amount = Double.parseDouble(parts[1].trim());
                        String description = parts[2].trim();
                        transactions.add(new Transaction(date, amount, description));
                    } catch (NumberFormatException e) {
                        System.err.println("Помилка парсингу числа: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
        return transactions;
    }
}
