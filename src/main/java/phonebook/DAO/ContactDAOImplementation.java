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
import phonebook.DTO.Contact;

/**
 *
 * @author Dzenan
 */
public class ContactDAOImplementation implements ContactDAO {

    @Override
    public List<Contact> getContacts(String userName) {
        List<Contact> contacts = new ArrayList<>();
        Connection connection = ConnectionObject.getInstance().getConnection();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT `c`.`ID`, `c`.`firstName`, `c`.`lastName`, `c`.`phoneNumber`, `c`.`eMail`, `c`.`address` FROM `contact` as `c` WHERE `c`.`userName` = '" + userName + "';")) {

            while (rs.next()) {
                contacts.add(new Contact(rs.getInt("ID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phoneNumber"), rs.getString("eMail"), rs.getString("address")));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return contacts;
    }

    @Override
    public void addContact(Contact contact, String userName) {

        try (PreparedStatement pstmt = prepareStmt(contact)) {
            pstmt.setString(6, userName);

            pstmt.executeUpdate();

            System.out.println("Contact added successfully!");

        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    private PreparedStatement prepareStmt(Contact contact) throws SQLException {
        Connection connection = ConnectionObject.getInstance().getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO phonebook.contact (firstName, lastName, phoneNumber, eMail, address, userName) VALUES(?,?,?,?,?,?)");
        pstmt.setString(1, contact.getFirstName());
        pstmt.setString(2, contact.getLastName());
        pstmt.setString(3, contact.getPhoneNumber());
        pstmt.setString(4, contact.geteMail());
        pstmt.setString(5, contact.getAddress());

        return pstmt;
    }

    @Override
    public void updateContact(Contact contact) {
        try (PreparedStatement pstmt = prepareUpdateStmt(contact)) {

            pstmt.executeUpdate();

            System.out.println("Contact info changed successfully!");

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private PreparedStatement prepareUpdateStmt(Contact contact) throws SQLException {
        Connection connection = ConnectionObject.getInstance().getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE phonebook.contact as c SET c.firstName =?, c.lastName=?, c.phoneNumber=?, c.eMail=?, c.address=? WHERE c.ID = ?");
        pstmt.setString(1, contact.getFirstName());
        pstmt.setString(2, contact.getLastName());
        pstmt.setString(3, contact.getPhoneNumber());
        pstmt.setString(4, contact.geteMail());
        pstmt.setString(5, contact.getAddress());
        pstmt.setInt(6, contact.getID());

        return pstmt;
    }

    @Override
    public void deleteContact(int ID) {
        Connection connection = ConnectionObject.getInstance().getConnection();
        String query = "DELETE FROM `contact` WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, ID);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}
