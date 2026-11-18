/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121poe;

import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

System.out.print("Enter first name: ");
String firstName = input.nextLine();

System.out.print("Enter last name: ");
String lastName = input.nextLine();

System.out.print("Create username: ");
String username = input.nextLine();

System.out.print("Create password: ");
String password = input.nextLine();

System.out.print("Enter cellphone number with country code: ");
String cellphoneNumber = input.nextLine();

Login login = new Login(firstName, lastName, username, password, cellphoneNumber);

String registrationStatus = login.registerUser();
System.out.println(registrationStatus);

if (!registrationStatus.equals("User successfully registered.")) {
    input.close();
    return;
}

System.out.print("Enter username to log in: ");
String enteredUsername = input.nextLine();

System.out.print("Enter password to log in: ");
String enteredPassword = input.nextLine();

boolean loginStatus = login.loginUser(enteredUsername, enteredPassword);

System.out.println(login.returnLoginStatus(loginStatus));

if (!loginStatus) {
    input.close();
    return;
}

System.out.println("Welcome to QuickChat!");
System.out.println("LOGIN SUCCESSFUL - OPENING MAIN MENU");        

        MessageManager manager = new MessageManager();
 
        
        
        boolean running = true;

        while (running) {

    System.out.println("\nQUICKCHAT MENU");
    System.out.println("1. Send Messages");
    System.out.println("2. Coming Soon");
    System.out.println("3. Quit");
    System.out.println("4. Stored Messages");

    System.out.print("Choose an option: ");

    String option = input.nextLine();

    switch (option) {

        case "1":
            // Your existing Send Messages code goes here.
            break;

        case "2":
            System.out.println("Coming Soon.");
            break;

        case "3":
            running = false;
            System.out.println("Thank you for using QuickChat.");
            break;

        case "4":
            // Part 3 stored messages functionality.
            // We will connect this to MessageManager.java.
            break;

        default:
            System.out.println("Invalid option. Please try again.");
    }
        }
    }
}