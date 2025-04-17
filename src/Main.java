import service.FinanceManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FinanceManager manager = new FinanceManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Amount: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        System.out.print("Category: ");
                        String category = scanner.nextLine();
                        System.out.print("Description: ");
                        String description = scanner.nextLine();
                        System.out.print("Is income? (y/n): ");
                        boolean isIncome = scanner.nextLine().trim().equalsIgnoreCase("y");
                        manager.addTransaction(amount, category, description, isIncome);
                        break;

                    case "2":
                        System.out.print("Category: ");
                        String budgetCategory = scanner.nextLine();
                        System.out.print("Limit: ");
                        double limit = Double.parseDouble(scanner.nextLine());
                        manager.addBudget(budgetCategory, limit);
                        break;

                    case "3":
                        System.out.printf("Current balance: %.2f%n", manager.getBalance());
                        break;

                    case "4":
                        manager.viewTransactions();
                        break;

                    case "5":
                        manager.viewBudgets();
                        break;

                    case "6":
                        manager.checkBudgetStatus();
                        break;

                    case "7":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Personal Finance Manager ===");
        System.out.println("1. Add Transaction");
        System.out.println("2. Set Budget");
        System.out.println("3. View Balance");
        System.out.println("4. View Transactions");
        System.out.println("5. View Budgets");
        System.out.println("6. Check Budget Status");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }
}