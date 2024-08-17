import java.sql.*;

public class Viewer {
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


            String retrieveUserSQL = "SELECT * FROM users";
            pstmt = conn.prepareStatement(retrieveUserSQL);
            rs = pstmt.executeQuery();

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
