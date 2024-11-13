package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Apply Singleton (Three Steps)

    // 1. Create a private static attribute that is of the same type as the class (DBConnection).
    private static DBConnection dbConnection;
    private Connection connection;

    // 2. Create a default constructor and set it as private.
    private DBConnection() {
        // Private constructor prevents instantiation from outside the class
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Create a public static method that returns the single instance of the class.
    public static DBConnection getInstance() {
        return (dbConnection == null) ? dbConnection = new DBConnection() : dbConnection;
    }

    // Public method to provide access to the database connection
    public Connection getConnection() {
        return connection;
    }
}


