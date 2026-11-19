/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121poe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Manages QuickChat messages.
 * @author Student
 */
public class MessageManager {

    // Lists to store messages according to their status.
    private final ArrayList<Message> messages;
    private final ArrayList<Message> disregardedMessages;
    private final ArrayList<Message> storedMessages;

    // Arrays required for Part 3.
    private String[] sentMessages;
    private String[] disregardedMessageArray;
    private String[] storedMessageArray;
    private String[] messageHashes;
    private String[] messageIDs;

    private int totalMessagesSent;

    // Constructor initialises the message lists and arrays.
    public MessageManager() {

        messages = new ArrayList<>();
        disregardedMessages = new ArrayList<>();
        storedMessages = new ArrayList<>();

        sentMessages = new String[0];
        disregardedMessageArray = new String[0];
        storedMessageArray = new String[0];
        messageHashes = new String[0];
        messageIDs = new String[0];

        totalMessagesSent = 0;
        
        loadStoredMessages();
    }

    // Processes the user's selected message action.
    public String processMessage(Message message, String choice) {

        if (choice.equals("1")) {

            messages.add(message);
            totalMessagesSent++;

            updateArrays();

            return "Message successfully sent.";
        }

        if (choice.equals("2")) {

            disregardedMessages.add(message);

            updateArrays();

            return message.SentMessage("2");
        }

        if (choice.equals("3")) {

            storedMessages.add(message);

            updateArrays();

            if (storeMessages()) {
                return "Message successfully stored.";
            }

            return "Message could not be stored.";
        }

        return "Invalid option.";
    }

    // Updates the Part 3 arrays using the message lists.
    private void updateArrays() {

        ArrayList<String> sent = new ArrayList<>();
        ArrayList<String> disregarded = new ArrayList<>();
        ArrayList<String> stored = new ArrayList<>();
        ArrayList<String> hashes = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();

        for (Message message : messages) {
            sent.add(message.getMessage());
            hashes.add(message.getMessageHash());
            ids.add(message.getMessageID());
        }

        for (Message message : disregardedMessages) {
            disregarded.add(message.getMessage());
            hashes.add(message.getMessageHash());
            ids.add(message.getMessageID());
        }

        for (Message message : storedMessages) {
            stored.add(message.getMessage());
            hashes.add(message.getMessageHash());
            ids.add(message.getMessageID());
        }

        sentMessages = sent.toArray(new String[0]);
        disregardedMessageArray = disregarded.toArray(new String[0]);
        storedMessageArray = stored.toArray(new String[0]);
        messageHashes = hashes.toArray(new String[0]);
        messageIDs = ids.toArray(new String[0]);
    }

    // Saves stored messages to messages.json.
    public boolean storeMessages() {

        try (FileWriter writer = new FileWriter("messages.json")) {

            writer.write("[\n");

            for (int i = 0; i < storedMessages.size(); i++) {

                Message message = storedMessages.get(i);

                writer.write("  {\n");

                writer.write("    \"messageID\": \""
                        + escapeJSON(message.getMessageID()) + "\",\n");

                writer.write("    \"messageHash\": \""
                        + escapeJSON(message.getMessageHash()) + "\",\n");

                writer.write("    \"recipient\": \""
                        + escapeJSON(message.getRecipient()) + "\",\n");

                writer.write("    \"message\": \""
                        + escapeJSON(message.getMessage()) + "\"\n");

                writer.write("  }");

                if (i < storedMessages.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]\n");

            return true;

        } catch (IOException e) {

            System.out.println("Error storing messages: "
                    + e.getMessage());

            return false;
        }
    }

    // Escapes special characters so that JSON remains valid.
    private String escapeJSON(String value) {

        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // Returns the total number of sent messages.
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Returns all successfully sent messages.
    public ArrayList<Message> getMessages() {
        return messages;
    }

    // Returns all stored messages.
    public ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    // Returns all disregarded messages.
    public ArrayList<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    // Displays all stored messages.
    public void displayStoredMessages() {

        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages available.");
            return;
        }

        for (Message message : storedMessages) {

            System.out.println("Sender: QuickChat User");
            System.out.println("Message ID: " + message.getMessageID());
            System.out.println("Message Hash: " + message.getMessageHash());
            System.out.println("Recipient: " + message.getRecipient());
            System.out.println("Message: " + message.getMessage());
            System.out.println("-------------------------");
        }
    }

    // Finds the longest sent message.
    public String findLongestMessage() {

        if (messages.isEmpty() && storedMessages.isEmpty()) {
        return "No messages available.";
    }

    Message longest = null;

    for (Message message : messages) {

        if (longest == null
                || message.getMessage().length()
                > longest.getMessage().length()) {

            longest = message;
        }
    }

    for (Message message : storedMessages) {

        if (longest == null
                || message.getMessage().length()
                > longest.getMessage().length()) {

            longest = message;
        }
    }

    return longest.getMessage();
    }

    // Searches for a message using its ID.
    public String searchMessageByID(String id) {

        for (Message message : messages) {
        if (message.getMessageID().equals(id)) {
            return message.printMessages();
        }
    }

    for (Message message : storedMessages) {
        if (message.getMessageID().equals(id)) {
            return message.printMessages();
        }
    }

    for (Message message : disregardedMessages) {
        if (message.getMessageID().equals(id)) {
            return message.printMessages();
        }
    }

    return "Message not found.";
    }

    // Searches for messages sent to a particular recipient.
    public ArrayList<Message> searchMessagesByRecipient(
            String recipient) {

        ArrayList<Message> results = new ArrayList<>();

        for (Message message : messages) {
            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        for (Message message : storedMessages) {
            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        for (Message message : disregardedMessages) {
            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        return results;
    }

    // Deletes a message using its message hash.
    public boolean deleteMessageByHash(String hash) {

        for (int i = 0; i < messages.size(); i++) {

            if (messages.get(i).getMessageHash().equals(hash)) {
                messages.remove(i);
                totalMessagesSent--;
                updateArrays();
                return true;
            }
        }

        for (int i = 0; i < storedMessages.size(); i++) {

            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                storedMessages.remove(i);
                updateArrays();
                storeMessages();
                return true;
            }
        }

        for (int i = 0; i < disregardedMessages.size(); i++) {

            if (disregardedMessages.get(i).getMessageHash().equals(hash)) {
                disregardedMessages.remove(i);
                updateArrays();
                return true;
            }
        }

        return false;
    }

    // Displays a report of all sent messages.
    public void displayMessageReport() {

        if (messages.isEmpty()
            && storedMessages.isEmpty()
            && disregardedMessages.isEmpty()) {

        System.out.println("No messages available.");
        return;
    }

    System.out.println("\nFULL MESSAGE REPORT");

    System.out.println("\nSENT MESSAGES");

    for (Message message : messages) {
        System.out.println(message.printMessages());
        System.out.println("-------------------------");
    }

    System.out.println("\nSTORED MESSAGES");

    for (Message message : storedMessages) {
        System.out.println(message.printMessages());
        System.out.println("-------------------------");
    }

    System.out.println("\nDISREGARDED MESSAGES");

    for (Message message : disregardedMessages) {
        System.out.println(message.printMessages());
        System.out.println("-------------------------");
    }

    System.out.println("Total messages sent: "
            + totalMessagesSent);
    }

    // Returns the sent messages array.
    public String[] getSentMessagesArray() {
        return sentMessages;
    }

    // Returns the message hashes array.
    public String[] getMessageHashesArray() {
        return messageHashes;
    }

    // Returns the message IDs array.
    public String[] getMessageIDsArray() {
        return messageIDs;
    }
    
    public String[] getDisregardedMessageArray() {
    return disregardedMessageArray;
    }

    public String[] getStoredMessageArray() {
    return storedMessageArray;
    }
    // Loads stored messages from messages.json when the program starts.
public void loadStoredMessages() {

    try (BufferedReader reader = new BufferedReader(
            new FileReader("messages.json"))) {

        StringBuilder json = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        String content = json.toString().trim();

        if (content.isEmpty() || content.equals("[]")) {
            updateArrays();
            return;
        }

        content = content.substring(1, content.length() - 1).trim();

        if (content.isEmpty()) {
            updateArrays();
            return;
        }

        String[] objects = content.split("\\},\\s*\\{");

        for (String object : objects) {

            String messageID = extractJSONValue(object, "messageID");
            String messageHash = extractJSONValue(object, "messageHash");
            String recipient = extractJSONValue(object, "recipient");
            String messageText = extractJSONValue(object, "message");

            Message message = new Message(messageID, recipient, messageText, 0, messageHash);

            storedMessages.add(message);
        }

        updateArrays();

    } catch (IOException e) {
        System.out.println("Could not load stored messages: "
                + e.getMessage());
    }
}

// Extracts a value from a JSON message object.
private String extractJSONValue(String json, String key) {

    String search = "\"" + key + "\": \"";
    int start = json.indexOf(search);

    if (start == -1) {
        return "";
    }

    start += search.length();

    StringBuilder value = new StringBuilder();
    boolean escaped = false;

    for (int i = start; i < json.length(); i++) {

        char current = json.charAt(i);

        if (escaped) {
            switch (current) {
                case 'n':
                    value.append('\n');
                    break;
                case 'r':
                    value.append('\r');
                    break;
                case 't':
                    value.append('\t');
                    break;
                default:
                    value.append(current);
                    break;
            }

            escaped = false;

        } else if (current == '\\') {
            escaped = true;

        } else if (current == '"') {
            return value.toString();

        } else {
            value.append(current);
        }
    }

    return "";
}
}