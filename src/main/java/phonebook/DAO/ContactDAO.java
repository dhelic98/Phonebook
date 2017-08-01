/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.DAO;

import java.util.List;
import phonebook.DTO.Contact;

/**
 *
 * @author Dzenan
 */
public interface ContactDAO {

    public List<Contact> getContacts(String userName);

    public void addContact(Contact contact, String userName);

    public void updateContact(Contact contact);

    public void deleteContact(int ID);

}
