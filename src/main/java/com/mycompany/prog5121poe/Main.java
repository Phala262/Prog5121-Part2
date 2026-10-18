/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121poe;

import java.util.ArrayList;
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
        System.out.println("Welcome to QuickChat.");

        MessageManager manager = new MessageManager();
 
        
        
        boolean running = true;

        while (running) {

            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            String option = input.nextLine();

            switch (option) {

                case "1":

                    System.out.print(
                            "How many messages would you like to enter? ");

                    int numberOfMessages;

                    try {
                        numberOfMessages = Integer.parseInt(
                                input.nextLine());

                        if (numberOfMessages < 0) {
                            System.out.println(
                                    "Enter a valid positive number.");
                            break;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number.");
                        break;
                    }

                    for (int i = 1; i <= numberOfMessages; i++) {

                        System.out.println("\nMessage " + i);

                        System.out.print("Enter recipient number: ");
                        String recipient = input.nextLine();

                        System.out.print("Enter message: ");
                        String text = input.nextLine();

                        Message message = new Message(
                                recipient, text, i);

                        if (!message.checkMessageID()) {
                            System.out.println("Invalid message ID.");
                            continue;
                        }

                        if (!message.checkRecipientCell()) {
                            System.out.println(
                                    "Cell phone number is incorrectly "
                                    + "formatted or does not contain "
                                    + "an international code. "
                                    + "Please correct the number "
                                    + "and try again.");
                            continue;
                        }

                        String validation = message.validateMessage();

                        if (!validation.equals("Message ready to send.")) {
                            System.out.println(validation);
                            continue;
                        }

                        System.out.println("\n" + message.printMessages());

                        System.out.println("\n1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message to send later");
                        System.out.print("Choose an option: ");

                        String choice = input.nextLine();

                        System.out.println(
                                manager.processMessage(message, choice));
                        if (choice.equals("3")){
                            boolean saved = manager.storeMessages();
                             
                            if (saved) {
                               System.out.println("Messages saved to json.");
                            } else {
                               System.out.println("Could not save messages.json.");
                            }
                        }
                    }

                    System.out.println("Total messages sent: "
                            + manager.returnTotalMessages());

                    break;

                case "2":

                    ArrayList<Message> messages = manager.getMessages();

                    if (messages.isEmpty()) {
                        System.out.println("No messages available.");
                    } else {
                        for (Message message : messages) {
                            System.out.println(
                                    message.printMessages());
                            System.out.println("--------------------");
                        }
                    }

                    break;

                case "3":

                    running = false;
                    System.out.println("Thank you for using QuickChat.");

                    break;

                default:

                    System.out.println(
                            "Invalid option. Please try again.");
            }
        }

        input.close();
    }
}