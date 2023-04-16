import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/social_network";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            // Load the JDBC driver for MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create the users table
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) UNIQUE NOT NULL,"
                    + "password VARCHAR(255) NOT NULL,"
                    + "email VARCHAR(100) UNIQUE NOT NULL,"
                    + "first_name VARCHAR(50),"
                    + "last_name VARCHAR(50),"
                    + "profile_image VARCHAR(255),"
                    + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            // Create the posts table
            sql = "CREATE TABLE IF NOT EXISTS posts ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id INT NOT NULL,"
                    + "content TEXT NOT NULL,"
                    + "image VARCHAR(255),"
                    + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (user_id) REFERENCES users(id)"
                    + ")";
            stmt.executeUpdate(sql);

            // Create the friends table
            sql = "CREATE TABLE IF NOT EXISTS friends ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "user_id INT NOT NULL,"
                    + "friend_id INT NOT NULL,"
                    + "status ENUM('pending', 'accepted', 'rejected') NOT NULL,"
                    + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (user_id) REFERENCES users(id),"
                    + "FOREIGN KEY (friend_id) REFERENCES users(id)"
                    + ")";
            stmt.executeUpdate(sql);

            // Create the messages table
            sql = "CREATE TABLE IF NOT EXISTS messages ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "sender_id INT NOT NULL,"
                    + "recipient_id INT NOT NULL,"
                    + "content TEXT NOT NULL,"
                    + "image VARCHAR(255),"
                    + "created_at DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + "FOREIGN KEY (sender_id) REFERENCES users(id),"
                    + "FOREIGN KEY (recipient_id) REFERENCES users(id)"
                    + ")";
            stmt.executeUpdate(sql);

            // Close the database connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}