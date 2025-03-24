package src;
import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USER = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "1A2b3f4l#"; // Replace with your MySQL password

    public DatabaseManager() {
        createTableIfNotExists();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS expenses (" +
                     "id INT PRIMARY KEY AUTO_INCREMENT," +
                     "date VARCHAR(10) NOT NULL," +
                     "category VARCHAR(50) NOT NULL," +
                     "description VARCHAR(100) NOT NULL," +
                     "amount DOUBLE NOT NULL)";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public void addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (date, category, description, amount) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, expense.getDate());
            pstmt.setString(2, expense.getCategory());
            pstmt.setString(3, expense.getDescription());
            pstmt.setDouble(4, expense.getAmount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding expense: " + e.getMessage());
        }
    }

    public ResultSet getAllExpenses() {
        String sql = "SELECT * FROM expenses";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error fetching expenses: " + e.getMessage());
            return null;
        }
    }

    public double getTotalByCategory(String category) {
        String sql = "SELECT SUM(amount) FROM expenses WHERE category = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getDouble(1) : 0.0;
        } catch (SQLException e) {
            System.out.println("Error calculating total: " + e.getMessage());
            return 0.0;
        }
    }
}