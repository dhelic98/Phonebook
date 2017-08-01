/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import phonebook.BO.ContactHandler;
import phonebook.BO.UserHandler;
import phonebook.DTO.Contact;
import phonebook.DTO.User;

/**
 *
 * @author Dzenan
 */
public class MainHelper {

    static UserHandler uHandler = new UserHandler();
    static Scanner uInput = new Scanner(System.in);
    static User user = new User();
    static ContactHandler cHandler = new ContactHandler();
    static Contact contact = new Contact();

    static List<Contact> contacts = new ArrayList<>();

    public static void printLoginMenu() {
        System.out.println("Welcome to phonebook\n1.Sign in\n2.Sign up\n3.Exit");
    }

    public static int getUserLoginOption() {
        while (true) {
            try {
                return uInput.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input");
                printLoginMenu();
                uInput.nextLine();
            }
        }

    }

    public static void switchOption(int userLoginOption) {
        switch (userLoginOption) {
            case 1:
                login();
                break;
            case 2:
                uHandler.registerUser();
                break;
            case 3:
                System.exit(0);

            default:
                System.err.println("Wrong option");
                break;
        }
    }

    private static void login() {
        user = uHandler.login();
        if (validateUser()) {
            startApp();
        } else {
            System.out.println("User does not exist!");
        }

    }

    private static boolean validateUser() {
        return user != null;
    }

    public static void startApp() {
        while (true) {
            printPhonebookMenu();
            int option = getUserOption();
            switchMainMenu(option);
        }

    }

    private static void printPhonebookMenu() {
        System.out.println("Main menu:\n1.All contacts\n2.Add new contact\n3.Exit");
    }

    private static int getUserOption() {
        while (true) {
            try {
                return uInput.nextInt();
            } catch (Exception ex) {
                System.out.println("Wrong input!");
                printPhonebookMenu();
                uInput.nextLine();

            }
        }
    }

    private static void switchMainMenu(int mainMenuOption) {
        switch (mainMenuOption) {
            case 1:
                listOfContacts();
                break;
            case 2:
                addContact();
                break;
            case 3:
                System.exit(0);
            default:
                manageMainMenuWrongInput();

        }
    }

    private static void manageMainMenuWrongInput() {
        System.err.println("Wrong option");
        uInput.nextLine();
        startApp();
    }

    public static void listOfContacts() {
        printAllContacts(user.getUserName());
        showInfo();

    }

    private static void printAllContacts(String userName) {
        contacts = cHandler.getContacts(userName);
        System.out.println("Contacts for : " + user.getUserName());
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getName());
        }
        System.out.println("0.Exit\nChoose contact to view information about contact");

    }

    private static void showInfo() {
        try {
            int userNumber = getUserOption();
            if (userNumber == 0) {
                startApp();
            }
            contact = contacts.get(userNumber - 1);
            System.out.println("Name: " + contact.getName() + "\nPhone number: " + contact.getPhoneNumber() + "\nEmail: " + contact.geteMail() + "\nAddress: " + contact.getAddress() + "\n\nOptions:\n1.Edit contact\n2.Delete contact\n3.Back");
            int option = getUserOption();
            contactInfoSwitch(option);
        } catch (Exception ex) {
            System.out.println("Wrong input");
            uInput.nextLine();
            showInfo();
        }
    }

    private static void contactInfoSwitch(int option) {
        switch (option) {
            case 1:
                editContact();
                break;
            case 2:
                deleteContact();
                break;
            case 3:

                listOfContacts();
                break;
            default:
                manageContactInfoSwitchWrongInput();

        }
    }

    private static void manageContactInfoSwitchWrongInput() {
        System.err.println("Wrong option");
        uInput.nextLine();
        startApp();

    }

    private static void addContact() {
        cHandler.addContact(user.getUserName());
    }

    private static void editContact() {
        cHandler.editContact(contact);
    }

    private static void deleteContact() {

        cHandler.deleteContact(contact.getID());
    }

}
