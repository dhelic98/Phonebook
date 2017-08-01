/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.BO;

import java.util.List;
import phonebook.DAO.ContactDAO;
import phonebook.DAO.ContactDAOImplementation;
import phonebook.DTO.Contact;

/**
 *
 * @author Dzenan
 */
public class ContactHandler {

    ContactDAO CDAO = new ContactDAOImplementation();

    public List<Contact> getContacts(String userName) {

        return CDAO.getContacts(userName);
    }

    public void addContact(String userName) {
        try {
            Contact contact = BOHelper.getContactInfo();
            CDAO.addContact(contact, userName);
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    public void deleteContact(int ID) {
        try {
            CDAO.deleteContact(ID);

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void editContact(Contact contact) {
        BOHelper.printEditMenu();
        int option = BOHelper.getEditMenuOption();
        BOHelper.editMenuSwitch(option, contact);
        CDAO.updateContact(contact);
    }

}
