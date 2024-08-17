package Tutorial;

import java.sql.*;

/**
 * Tutorial Two
 * Create a table names users in PostgreSQL database
 */
public class Two {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String dbUser = "scott";
        String dbPassword = "tiger";

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
            System.out.println("Connected to the PostgreSQL server successfully.");

            stmt = conn.createStatement();
            String dropTableSQL = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(dropTableSQL);
            System.out.println("Table 'users' dropped if it existed.");

            String createTableSQL = "CREATE TABLE users ("
                    + "id SERIAL PRIMARY KEY, "
                    + "email VARCHAR(255) UNIQUE NOT NULL, "
                    + "password VARCHAR(255) NOT NULL"
                    + ")";
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'users' created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
