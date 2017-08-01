/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dzenan
 */
public class ConnectionObject {

    //Instance of an object
    private static ConnectionObject instance = null;

    //Credidentials for logging in to db
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final String CONN_STRING = "jdbc:mysql://localhost/phonebook?useSSL=false";

    private Connection connection = null;

    public ConnectionObject() {
    }

    public static ConnectionObject getInstance() {
        if (instance == null) {
            instance = new ConnectionObject();
        }
        return instance;
    }

    private boolean openConnection() {
        try {

            connection = DriverManager.getConnection(CONN_STRING, USER, PASSWORD);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public Connection getConnection() {
        if (connection == null) {
            if (openConnection()) {

                return connection;
            } else {
                return null;
            }
        }
        return connection;
    }

    public void close() {
        System.out.println("Konekcija zatvorena.");
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
        }
    }

}
