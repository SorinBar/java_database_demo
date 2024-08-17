import java.sql.*;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.createTable();

        userService.create(new User("email1", "password"));
        userService.create(new User("email2", "password"));
        userService.create(new User("email3", "password"));
        userService.create(new User("email4", "password"));

        System.out.println(userService.read("email3"));
        userService.delete("email3");
        System.out.println(userService.read("email3"));
    }
}

class Database {
    private static Database INSTANCE = null;
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;
    private Database() {
        url = "jdbc:postgresql://localhost:5432/postgres";
        user = "scott";
        password = "tiger";
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error connecting");
            e.printStackTrace();
        }
        System.out.println("Connected to the PostgreSQL server successfully.");
    }
    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        };
        return INSTANCE;
    }

    public void close() {
        try {
            connection.close();
            System.out.println("Connection to the PostgreSQL server closed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

class UserService {
    private Database database;

    public UserService() {
        database = Database.getInstance();
    }

    public void createTable() {
        try {
            Statement statement = database.getConnection().createStatement();
            String dropTableSQL = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(dropTableSQL);
            String createTableSQL = "CREATE TABLE users ("
                    + "id SERIAL PRIMARY KEY, "
                    + "email VARCHAR(255) UNIQUE NOT NULL, "
                    + "password VARCHAR(255) NOT NULL"
                    + ")";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating Table 'users'");
            e.printStackTrace();
        }
    }

    public User create(User user) {
        try {
            String insertUserSQL = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(insertUserSQL);
            preparedStatement.setString(1, user.email);
            preparedStatement.setString(2, user.password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating user");
            e.printStackTrace();
        }
        return read(user.email);
    }

    public User read(String email) {
        try {
            String retrieveUserSQL = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(retrieveUserSQL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String email) {
        try {
            String deleteUserSQL = "DELETE FROM users WHERE email = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(deleteUserSQL);
            preparedStatement.setString(1, email);
            int rowsAffected = preparedStatement.executeUpdate();
           return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user");
            e.printStackTrace();
        }
        return false;
    }

    public User update(String email, String newPassword) {
        try {
            String updateUserSQL = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(updateUserSQL);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User with email " + email + " updated successfully.");
                return read(email); // Return the updated user
            } else {
                System.out.println("No user found with email " + email + ".");
            }
        } catch (SQLException e) {
            System.out.println("Error updating user");
            e.printStackTrace();
        }
        return null;
    }
}