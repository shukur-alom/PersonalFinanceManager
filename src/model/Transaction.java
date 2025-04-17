package model;

import java.time.LocalDate;

public class Transaction {
    private double amount;
    private String category;
    private String description;
    private LocalDate date;
    private boolean isIncome;

    public Transaction(double amount, String category, String description, LocalDate date, boolean isIncome) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
        this.isIncome = isIncome;
    }

    // Getters
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
    public boolean isIncome() { return isIncome; }

    @Override
    public String toString() {
        return String.format("%s | %.2f | %s | %s | %s",
                date, amount, category, description, isIncome ? "Income" : "Expense");
    }

    // For file saving
    public String toFileString() {
        return String.format("%s,%.2f,%s,%s,%b", date, amount, category, description, isIncome);
    }
}