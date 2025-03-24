package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        while (true) {
            System.out.println("\n=== Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total by Category");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    tracker.addExpense(new Expense(date, category, desc, amount));
                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    tracker.viewExpenses();
                    break;

                case 3:
                    System.out.print("Enter category to total: ");
                    String cat = scanner.nextLine();
                    double total = tracker.getTotalByCategory(cat);
                    System.out.printf("Total for %s: $%.2f%n", cat, total);
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}