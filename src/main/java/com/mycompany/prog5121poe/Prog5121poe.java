/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121poe;

import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Prog5121poe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Registration and Login System =====");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your South African cellphone number: ");
        String cellPhoneNumber = scanner.nextLine();

        Login user = new Login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );

        String registrationMessage = user.registerUser();

        System.out.println("\n" + registrationMessage);

        if (registrationMessage.equals("User successfully registered.")) {

            System.out.println("\n===== Login =====");

            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            boolean loginStatus =
                    user.loginUser(enteredUsername, enteredPassword);

            System.out.println(user.returnLoginStatus(loginStatus));
        }

        scanner.close();
    }
}

