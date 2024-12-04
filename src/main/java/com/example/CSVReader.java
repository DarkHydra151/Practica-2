package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements DataReader<Transaction> {
    @Override
    public List<Transaction> read(String source) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(source).openStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String date = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    String description = parts[2].trim();
                    transactions.add(new Transaction(date, amount, description));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return transactions;
    }
}
