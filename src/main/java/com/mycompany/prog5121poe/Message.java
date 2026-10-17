/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121poe;
import java.util.Random;
/**
 *
 * @author Student
 */
public final class Message {
    private final String messageID;
    private final String recipient;
    private final String message;
    private final String messageHash;
    private final int messageNumber;

    public Message(String recipient, String message, int messageNumber) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.message = message;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        long id = 1000000000L + random.nextInt(900000000);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient.matches("^\\+\\d{1,3}\\d{1,10}$");
    }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");

        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0
                ? words[words.length - 1] : "";

        String firstTwoID = messageID.substring(0, 2);

        return (firstTwoID + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    public String validateMessage() {
        if (message.length() > 250) {
            int excess = message.length() - 250;

            return "Message exceeds 250 characters by "
                    + excess + "; please reduce the size.";
        }

        return "Message ready to send.";
    }

    public String SentMessage(String choice) {
        switch (choice) {
            case "1":
                return "Message successfully sent.";

            case "2":
                return "Press 0 to delete the message.";

            case "3":
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }
}

