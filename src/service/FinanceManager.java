package service;

import model.Budget;
import model.Transaction;
import util.FileHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceManager {
    private List<Transaction> transactions;
    private List<Budget> budgets;

    public FinanceManager() {
        transactions = FileHandler.loadTransactions();
        budgets = FileHandler.loadBudgets();
    }

    public void addTransaction(double amount, String category, String description, boolean isIncome) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        Transaction t = new Transaction(amount, category, description, LocalDate.now(), isIncome);
        transactions.add(t);
        FileHandler.saveTransactions(transactions);
        System.out.println("Transaction added.");
    }

    public void addBudget(String category, double limit) {
        if (limit <= 0) {
            System.out.println("Budget limit must be positive.");
            return;
        }
        budgets.add(new Budget(category, limit));
        FileHandler.saveBudgets(budgets);
        System.out.println("Budget added.");
    }

    public double getBalance() {
        double income = transactions.stream()
                .filter(Transaction::isIncome)
                .mapToDouble(Transaction::getAmount)
                .sum();
        double expenses = transactions.stream()
                .filter(t -> !t.isIncome())
                .mapToDouble(Transaction::getAmount)
                .sum();
        return income - expenses;
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        transactions.forEach(System.out::println);
    }

    public void viewBudgets() {
        if (budgets.isEmpty()) {
            System.out.println("No budgets set.");
            return;
        }
        budgets.forEach(System.out::println);
    }

    public void checkBudgetStatus() {
        for (Budget b : budgets) {
            double spent = transactions.stream()
                    .filter(t -> !t.isIncome() && t.getCategory().equals(b.getCategory()))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
            System.out.printf("Category: %s | Spent: %.2f | Limit: %.2f | Remaining: %.2f%n",
                    b.getCategory(), spent, b.getLimit(), b.getLimit() - spent);
        }
    }
}