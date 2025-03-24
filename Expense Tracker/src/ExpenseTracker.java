package src;
import java.sql.*;
public class ExpenseTracker {
    private DatabaseManager dbManager;

    public ExpenseTracker() {
        dbManager = new DatabaseManager();
    }

    public void addExpense(Expense expense) {
        dbManager.addExpense(expense);
    }

    public void viewExpenses() {
        ResultSet rs = dbManager.getAllExpenses();
        if (rs == null) return;

        try {
            boolean hasExpenses = false;
            System.out.println("\nID\tDate\t\tCategory\tDescription\tAmount");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                hasExpenses = true;
                System.out.printf("%d\t%s\t%s\t\t%s\t\t%.2f%n",
                    rs.getInt("id"),
                    rs.getString("date"),
                    rs.getString("category"),
                    rs.getString("description"),
                    rs.getDouble("amount"));
            }
            if (!hasExpenses) {
                System.out.println("No expenses recorded yet.");
            }
        } catch (SQLException e) {
            System.out.println("Error displaying expenses: " + e.getMessage());
        }
    }

    public double getTotalByCategory(String category) {
        return dbManager.getTotalByCategory(category);
    }
}
