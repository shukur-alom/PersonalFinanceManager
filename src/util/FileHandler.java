package util;

import model.Budget;
import model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String TRANSACTION_FILE = "transactions.txt";
    private static final String BUDGET_FILE = "budgets.txt";

    public static void saveTransactions(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE))) {
            for (Transaction t : transactions) {
                writer.write(t.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Transaction t = new Transaction(
                        Double.parseDouble(parts[1]),
                        parts[2],
                        parts[3],
                        LocalDate.parse(parts[0]),
                        Boolean.parseBoolean(parts[4])
                );
                transactions.add(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transaction file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    public static void saveBudgets(List<Budget> budgets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BUDGET_FILE))) {
            for (Budget b : budgets) {
                writer.write(b.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving budgets: " + e.getMessage());
        }
    }

    public static List<Budget> loadBudgets() {
        List<Budget> budgets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BUDGET_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Budget b = new Budget(parts[0], Double.parseDouble(parts[1]));
                budgets.add(b);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No budget file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading budgets: " + e.getMessage());
        }
        return budgets;
    }
}