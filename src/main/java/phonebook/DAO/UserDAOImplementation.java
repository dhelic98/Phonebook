/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import phonebook.DTO.User;

/**
 *
 * @author Dzenan
 */
public class UserDAOImplementation implements UserDAO {

    @Override
    public User getUser(String userName) {

        ResultSet rs = null;
        Connection connection = ConnectionObject.getInstance().getConnection();

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT u.userName, u.password FROM phonebook.user as u WHERE userName =?")) {

            pstmt.setString(1, userName);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("userName"), rs.getString("password"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<String> getUserNames() {
        Connection connection = ConnectionObject.getInstance().getConnection();

        List<String> userNames = new ArrayList<>();

        String query = "SELECT u.userName FROM phonebook.user as u";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                userNames.add(rs.getString("userName"));
            }

        } catch (Exception e) {
            System.err.println(e);
        }

        return userNames;

    }

    @Override
    public void addUser(User user) {
        Connection connection = ConnectionObject.getInstance().getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO phonebook.user(userName, password) VALUES(?, ?);")) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());

            pstmt.executeUpdate();
            System.out.println("User added successfully!");

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateUser(User user, String oldUserName) {
        Connection connection = ConnectionObject.getInstance().getConnection();

        try (PreparedStatement pstmt = connection.prepareStatement("UPDATE phonebook.user SET userName=? , password = ? WHERE userName = ? ")) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, oldUserName);

            pstmt.executeUpdate();

            System.out.println("User info changed successfully!");

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(String userName) {
        Connection connection = ConnectionObject.getInstance().getConnection();

        String query = "DELETE * FROM phonebook.user WHERE userName = '" + userName + "';";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
