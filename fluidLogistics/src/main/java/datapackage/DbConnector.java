package datapackage;

import java.sql.*;

public class DbConnector {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private DbConnector connector = null;

    private DbConnector(String address, String user, String password) {
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql" + address, user, password);
        } catch(SQLException e) {
            System.out.println("Error. Can not establish connection to the database!");
            e.printStackTrace();
        }
    }

    public DbConnector getInstance(String address, String user, String password) {
        if (connector == null) {
            connector = new  DbConnector(address, user, password);
            return connector;
        } else {
            return this;
        }

    }


    public Statement createStatement() throws Exception {
        return connection.createStatement();
    }
}