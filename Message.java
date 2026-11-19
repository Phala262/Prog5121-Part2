/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121poe;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents an individual QuickChat message.
 * @author Student
 */
public final class Message {

    // Variables to store message information.
    private final String messageID;
    private final String recipient;
    private final String message;
    private final String messageHash;
    private final int messageNumber;

    // Constructor to initialise a new message.
    public Message(String recipient, String message, int messageNumber) {

        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.message = message;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
    }
    // Restores a stored message with its original ID and hash.
    public Message(String messageID, String recipient, String message,
               int messageNumber, String messageHash) {

    this.messageID = messageID;
    this.recipient = recipient;
    this.message = message;
    this.messageNumber = messageNumber;
    this.messageHash = messageHash;
}
    // Generates a random 10-digit message ID.
    private String generateMessageID() {

        long id = ThreadLocalRandom.current().nextLong(
                1000000000L, 10000000000L);

        return String.valueOf(id);
    }

    // Checks whether the message ID contains exactly 10 digits.
    public boolean checkMessageID() {

        return messageID.matches("\\d{10}");
    }

    // Validates the recipient cellphone number.
    public boolean checkRecipientCell() {

        return recipient != null
                && recipient.matches("^\\+\\d{1,3}\\d{1,10}$");
    }

    // Creates the message hash.
    // Removes punctuation before extracting the first and last words.
    public String createMessageHash() {

        if (message == null || message.trim().isEmpty()) {
            return messageID.substring(0, 2)
                    + ":" + messageNumber + ":";
        }

        // Remove punctuation and special characters.
        String cleanedMessage = message.trim()
                .replaceAll("[^a-zA-Z0-9\\s]", "");

        // Separate the message into individual words.
        String[] words = cleanedMessage.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // Get the first two digits of the message ID.
        String firstTwoID = messageID.substring(0, 2);

        // Combine the information to create the hash.
        return (firstTwoID + ":" + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    // Checks whether the message is within the 250-character limit.
    public String validateMessage() {

        if (message == null || message.trim().isEmpty()) {
            return "Message cannot be empty.";
        }

        if (message.length() > 250) {

            int excess = message.length() - 250;

            return "Message exceeds 250 characters by "
                    + excess + "; please reduce the size.";
        }

        return "Message ready to send.";
    }

    // Processes the user's selected message action.
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

    // Displays the message details.
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Returns the message ID.
    public String getMessageID() {
        return messageID;
    }

    // Returns the recipient cellphone number.
    public String getRecipient() {
        return recipient;
    }

    // Returns the message content.
    public String getMessage() {
        return message;
    }

    // Returns the message hash.
    public String getMessageHash() {
        return messageHash;
    }

    // Returns the message number.
    public int getMessageNumber() {
        return messageNumber;
    }
}