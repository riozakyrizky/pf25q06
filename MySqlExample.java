import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class MySqlExample {
    public static void main(String[] args) throws ClassNotFoundException {
        String host, port, databaseName, userName, password;
        host = "mysql-tictactoe-riozaky.c.aivencloud.com";
        port = "16190";
        databaseName = "tictactoe";
        userName = "avnadmin";
        password = "AVNS_cHS_zxocCT-p3ycxwIc";
        // JDBC allows to have nullable username and password
        if (host == null || port == null || databaseName == null) {
            System.out.println("Host, port, database information is required");
            return;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (final Connection connection =
                     DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery("SELECT firstname, lastname from users")) {

            while (resultSet.next()) {
                System.out.println("Version: " + resultSet.getString("firstname"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}