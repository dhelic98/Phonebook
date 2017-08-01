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
public class Contact {

    private final int ID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;
    private String address;

    public Contact(int ID, String firstName, String lastName, String phoneNumber, String eMail, String address) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.address = address;
    }

    public Contact() {
        this.ID = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
