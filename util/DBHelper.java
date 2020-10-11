package by.itacademy.gomel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL= "jdbc:mysql://localhost:3306/cinema?useSSL=false&serverTimezone=UTC";
    static final String DB_LOGIN = "root";
    static final String DB_PASSWORD = "martafd";
    private static DBHelper instance;

    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    public static DBHelper getInstance() {

        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public Connection openConnection() throws SQLException {

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void closeConnection(Connection connection) {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PreparedStatement openStatement(Connection connection, String query) {

        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    public void closeStatement(PreparedStatement preparedStatement) {

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet openResultSet(PreparedStatement preparedStatement) {

        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void closeResultSet(ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}