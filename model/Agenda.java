package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Agenda {

    private Connection connection = null;

    private String hostName = null;
    private String userName = null;
    private String password = null;
    private String url = null;
    private String dataBaseName = null;
    private String dataBasePrefix = null;
    private String dabaBasePort;

    public Agenda() {
        //"jdbc: mysql:/localhost:3306/meu_bd";
        hostName = "127.0.0.1";
        userName = "root";
        password = "";
        dataBaseName = "agenda";
        dataBasePrefix = "jdbc:mysql:/";
        dabaBasePort = "3306";
        url = dataBasePrefix + hostName + ":"+dabaBasePort+"/" + dataBaseName + "/";
    }

    public Connection openConnection() {
        try {
            if(connection == null) {
                connection = DriverManager.getConnection(url, userName, password);
            }
            else if (connection.isClosed()) {
                System.err.println("ERRO: Conexão nula.");
            }
        }
        catch (SQLException e) {
            System.err.println("ERRO: " + e.getCause());
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("ERRO: " + e.getCause());
            }
        }
    }
}
