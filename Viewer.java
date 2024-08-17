import java.sql.*;
import java.util.ArrayList;

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

            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password")));
            }
            System.out.println("[");
            for (User user : users) {
                System.out.println(user + ",");
            }
            System.out.println("]");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
