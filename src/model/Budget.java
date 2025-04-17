package model;

public class Budget {
    private String category;
    private double limit;

    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
    }

    // Getters
    public String getCategory() { return category; }
    public double getLimit() { return limit; }

    @Override
    public String toString() {
        return String.format("Category: %s | Limit: %.2f", category, limit);
    }

    // For file saving
    public String toFileString() {
        return String.format("%s,%.2f", category, limit);
    }
}