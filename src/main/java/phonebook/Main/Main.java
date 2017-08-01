/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phonebook.Main;

/**
 *
 * @author Dzenan
 */
public class Main {

    public static void main(String[] args) {
        while (true) {
            MainHelper.printLoginMenu();

            //Getting input by user
            int userLoginOption = MainHelper.getUserLoginOption();

            //Calling switch on options
            MainHelper.switchOption(userLoginOption);

        }

    }

}
