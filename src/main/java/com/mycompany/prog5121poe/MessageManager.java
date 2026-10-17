/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121poe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Student
 */
public class MessageManager {
    
    private final ArrayList<Message> messages;
    private int totalMessagesSent;

    public MessageManager() {
        messages = new ArrayList<>();
        totalMessagesSent = 0;
    }

    public String processMessage(Message message, String choice) {

        if (choice.equals("1")) {
            messages.add(message);
            totalMessagesSent++;

            return "Message successfully sent.";
        }

        if (choice.equals("2")) {
            return "Press 0 to delete the message.";
        }

        if (choice.equals("3")) {
            messages.add(message);

            if (storeMessages()) {
                return "Message successfully stored.";
            }

            return "Message could not be stored.";
        }

        return "Invalid option.";
    }

    public boolean storeMessages() {
        try (FileWriter writer = new FileWriter("messages.json")) {

            writer.write("[\n");

            for (int i = 0; i < messages.size(); i++) {
                Message message = messages.get(i);

                writer.write("  {\n");
                writer.write("    \"messageID\": \""
                        + escapeJSON(message.getMessageID()) + "\",\n");
                writer.write("    \"messageHash\": \""
                        + escapeJSON(message.getMessageHash()) + "\",\n");
                writer.write("    \"recipient\": \""
                        + escapeJSON(message.getRecipient()) + "\"\n");
                writer.write("    \"message\": \""                
                        + escapeJSON(message.getMessage()) + "\"\n");
                writer.write("  }");

                if (i < messages.size() - 1) {
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

    private String escapeJSON(String value) {
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }
}