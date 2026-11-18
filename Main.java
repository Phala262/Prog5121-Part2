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
        int messageNumber = 1;
        
        
        boolean running = true;

        while (running) {

    System.out.println("\nQUICKCHAT MENU");
    System.out.println("1. Send Messages");
    System.out.println("2. Coming Soon");
    System.out.println("3. Quit");
    System.out.println("4. Stored Messages");
    System.out.println("5. Search Message by ID");
    System.out.println("6. Search Messages by Recipient");
    System.out.println("7. Delete Message by Hash");
    System.out.println("8. Display Message Report");
    System.out.println("9. Display Total Messages Sent");


    System.out.print("Choose an option: ");

    String option = input.nextLine();

    switch (option) {

        case "1":
            System.out.print("Enter recipient cellphone number: ");
    String recipient = input.nextLine();

    System.out.print("Enter your message (maximum 250 characters): ");
    String messageText = input.nextLine();

        {
            Message newMessage = new Message(
                    recipient, messageText, messageNumber);
            
            
        

    if (!newMessage.checkRecipientCell()) {
        System.out.println("Invalid recipient cellphone number.");
        break;
    }

    String validation = newMessage.validateMessage();

    if (!validation.equals("Message ready to send.")) {
        System.out.println(validation);
        break;
    }

    System.out.println("\nChoose message action:");
    System.out.println("1. Send message");
    System.out.println("2. Disregard message");
    System.out.println("3. Store message");

    System.out.print("Enter your choice: ");
    String messageChoice = input.nextLine();

    System.out.println(
            manager.processMessage(newMessage, messageChoice));

    if (messageChoice.equals("1")
            || messageChoice.equals("2")
            || messageChoice.equals("3")) {
        messageNumber++;
    }
            break;
        }
        case "2":
            System.out.println("Coming Soon.");
            break;

        case "3":
            running = false;
            System.out.println("Thank you for using QuickChat.");
            break;

        case "4":
            System.out.println("\nSTORED MESSAGES");
            manager.displayStoredMessages();
            break;
        case "5":
            System.out.print("Enter Message ID: ");
            String searchID = input.nextLine();
            System.out.println(manager.searchMessageByID(searchID));
            break;

        case "6":
            System.out.print("Enter recipient cellphone number: ");
            String searchRecipient = input.nextLine();
            System.out.println(manager.searchMessagesByRecipient(searchRecipient));
            break;

        case "7":
            System.out.print("Enter Message Hash: ");
            String deleteHash = input.nextLine();
            System.out.println(manager.deleteMessageByHash(deleteHash));
            break;
         
        case "8":
            manager.displayMessageReport();
            break;

        case "9":
            System.out.println("Total messages sent: " + manager.returnTotalMessages());
            break;

        default:
            System.out.println("Invalid option. Please try again.");
    
        }
        }
    }
}