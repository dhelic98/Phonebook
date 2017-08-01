/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.DTO;

/**
 *
 * @author Dzenan
 */
public class User {

    private String userName;
    private String password;

    public User() {
        this.userName = "";
        this.password = "";
    }

    public User(String uName, String pw) {
        this.userName = uName;
        this.password = pw;

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return "User name is: " + this.userName + "\nPassword is: " + this.password;

    }

}
