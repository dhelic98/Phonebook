/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.BO;

import java.util.List;
import phonebook.DAO.UserDAO;
import phonebook.DAO.UserDAOImplementation;
import phonebook.DTO.User;

/**
 *
 * @author Dzenan
 */
public class UserHandler {

    public static UserDAO UDAO = new UserDAOImplementation();

    public boolean registerUser() {
        try {
            User user = BOHelper.getInfoFromUser();
            List<String> userNames = UDAO.getUserNames();
            while (userNames.contains(user.getUserName())) {
                System.out.println("Username already exists, try again:");
                user = BOHelper.getInfoFromUser();

            }
            UDAO.addUser(user);
            return true;
        } catch (Exception ex) {
            
            return false;
        }
    }

    public User login() {
        //Method for getting input from user
        User inputUser = BOHelper.getInfoFromUser();

        User dbUser = UDAO.getUser(inputUser.getUserName());
        if (dbUser != null) {
            if (validate(inputUser, dbUser)) {
                return dbUser;
            }
        }

        return null;
    }

    private static boolean validate(User loginUser, User dbUser) {
        //Method for password validation
        return loginUser.getPassword().equals(dbUser.getPassword());
    }

}
