package lk.ijse.florist_pos.final_project.DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private final String url = "jdbc:mysql://localhost:3306/florist";
    private final String user = "charith123";
    private final String password = "ijsedb";

    private DBConnection() throws SQLException {
       connection = DriverManager.getConnection(url,user,password);
    }

    public static DBConnection getInstance() throws SQLException {
        return dbConnection == null ? dbConnection = new DBConnection () : dbConnection;

    }
    public Connection getConnection(){
        return connection;
    }


}
