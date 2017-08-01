/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.BO;

import java.util.Scanner;
import java.util.regex.Pattern;
import phonebook.DTO.Contact;
import phonebook.DTO.User;
import phonebook.Main.MainHelper;

/**
 *
 * @author Dzenan
 */
public class BOHelper {

    static Scanner uInput = new Scanner(System.in);

    public static User getInfoFromUser() {

        String userName = getEssentialField("Enter your username:");

        String password = getEssentialField("Enter your password:");

        return new User(userName, password);
    }

    public static Contact getContactInfo() {
        String firstName = getEssentialField("Enter first name");
        String lastName = getEssentialField("Enter last name");
        String phoneNumber = validatePhoneNumber();
        String eMail = getNonEssentialField("Enter e-mail");
        String address = getNonEssentialField("Enter address of residence");

        return new Contact(0, firstName, lastName, phoneNumber, eMail, address);

    }

    private static String validatePhoneNumber() {
        String phoneNumber = "sadsadsa";
        while (Pattern.matches("\\D+", phoneNumber)) {
            phoneNumber = getEssentialField("Enter phone number. All characters must be numeric");
        }

        return phoneNumber;
    }

    private static String getEssentialField(String message) {
        System.out.println(message);
        String field = uInput.nextLine();
        while (field.length() < 3 || field.length() > 20) {
            System.out.println("Wrong input.Size must be between 2 and 20");
            field = uInput.nextLine();
        }

        return field;
    }

    private static String getNonEssentialField(String message) {
        System.out.println(message + " (OPTIONAL)");
        String field = uInput.nextLine();
        while (field.length() > 35) {
            System.out.println("Wrong input.Size must be between 2 and 20");
            field = uInput.nextLine();
        }

        return field;

    }

    public static void printEditMenu() {
        System.out.println("1.Change first name\n2.Change last name\n3.Change phone number\n4.Change e-mail\n5.Change address\n6Exit");
    }

    public static int getEditMenuOption() {
        try {
            return uInput.nextInt();
        } catch (Exception ex) {
            System.err.println("Wrong input");
            return -1;
        }
    }

    public static void editMenuSwitch(int option, Contact contact) {
        switch (option) {
            case 1:
                changeFirstName(contact);
                break;
            case 2:
                changeLastName(contact);
                break;
            case 3:
                changePhoneNumber(contact);
                break;
            case 4:
                changeEmail(contact);
                break;
            case 5:
                changeAddress(contact);
                break;
            case 6:
                MainHelper.listOfContacts();
                break;
            default:

                System.out.println("Wrong input");
                MainHelper.listOfContacts();

        }
    }

    private static void handleEditMenuSwitch(Contact contact) {

    }

    private static void changeFirstName(Contact contact) {
        try {
            String newName = getEssentialField("Enter new first name");
            String oldName = contact.getFirstName();
            contact.setFirstName(newName);
            System.out.println("New first name: " + newName + "\nOld first name: " + oldName);

        } catch (Exception ex) {

        }
    }

    private static void changeLastName(Contact contact) {
        try {
            String newName = getEssentialField("Enter new last name");
            String oldName = contact.getLastName();
            contact.setLastName(newName);
            System.out.println("New last name: " + newName + "\nOld last name: " + oldName);

        } catch (Exception ex) {

        }
    }

    private static void changePhoneNumber(Contact contact) {
        try {
            String newNumber = getEssentialField("Enter new phone number");
            String oldNumber = contact.getPhoneNumber();
            contact.setPhoneNumber(newNumber);
            System.out.println("New phone number: " + newNumber + "\nOld phone number: " + oldNumber);

        } catch (Exception ex) {

        }
    }

    private static void changeEmail(Contact contact) {
        try {
            String newEmail = getEssentialField("Enter new e-mail");
            String oldEmail = contact.geteMail();
            contact.seteMail(newEmail);
            System.out.println("New e-mail: " + newEmail + "\nOld e-mail: " + oldEmail);

        } catch (Exception ex) {

        }
    }

    private static void changeAddress(Contact contact) {
        try {
            String newAddress = getEssentialField("Enter new address");
            String oldAddress = contact.getAddress();
            contact.setAddress(newAddress);
            System.out.println("New address: " + newAddress + "\nOld address: " + oldAddress);
        } catch (Exception ex) {

        }
    }

}
