package Tutorial;

import java.sql.*;

/**
 * Tutorial Three
 * Add a user to the user table
 */
public class Three {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String dbUser = "scott";
        String dbPassword = "tiger";

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

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

            // SQL command to insert a new user
            String insertUserSQL = "INSERT INTO users (email, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertUserSQL);
            pstmt.setString(1, "user@example.com");
            pstmt.setString(2, "password123");
            pstmt.executeUpdate();
            System.out.println("User inserted successfully.");

            // SQL command to retrieve the user by email
            String retrieveUserSQL = "SELECT * FROM users WHERE email = ?";
            pstmt = conn.prepareStatement(retrieveUserSQL);
            pstmt.setString(1, "user@example.com");
            rs = pstmt.executeQuery();

            // Process the result set
            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password"); // In practice, never print raw passwords

                System.out.println("User retrieved:");
                System.out.println("ID: " + id);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password); // Replace with secure handling
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
