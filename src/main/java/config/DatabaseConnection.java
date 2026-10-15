package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * 
 */
public class DatabaseConnection {

    private static final String URL
            = "jdbc:mysql://localhost:3306/service_ac"
            + "?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean testKoneksi() {

        try (Connection conn = getConnection()) {

            return conn.isValid(3);

        } catch (SQLException e) {

            System.err.println(
                    "Koneksi database gagal: " + e.getMessage()
            );

            return false;
        }
    }
}
