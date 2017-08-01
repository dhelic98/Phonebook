/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.DAO;

import java.util.List;
import phonebook.DTO.User;

/**
 *
 * @author Dzenan
 */
public interface UserDAO {

    public User getUser(String userName);

    public List<String> getUserNames();

    public void addUser(User user);

    public void updateUser(User user, String oldUserName);

    public void deleteUser(String userName);

}
