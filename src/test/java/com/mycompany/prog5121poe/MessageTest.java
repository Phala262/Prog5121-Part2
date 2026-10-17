/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    @Test
    public void testMessageID() {
        Message message = new Message(
                "+27123456789", "Hello Mike", 1);

        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testValidRecipient() {
        Message message = new Message(
                "+277118693002", "Hello Mike", 1);

        assertTrue(message.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipient() {
        Message message = new Message(
                "12345", "Hello Mike", 1);

        assertFalse(message.checkRecipientCell());
    }

    @Test
    public void testValidMessageLength() {
        Message message = new Message(
                "+277118693002", "Hello Mike", 1);

        assertEquals(
                "Message ready to send.",
                message.validateMessage());
    }

    @Test
    public void testMessageExceeds250Characters() {
        String longMessage = "A".repeat(251);

        Message message = new Message(
                "+277118693002", longMessage, 1);

        assertEquals(
                "Message exceeds 250 characters by 1; "
                + "please reduce the size.",
                message.validateMessage());
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                "+277118693002", "Hi tonight", 1);

        assertTrue(message.getMessageHash()
                .matches("\\d{2}:1:HITONIGHT"));
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "+277118693002", "Hello Mike", 1);

        MessageManager manager = new MessageManager();

        assertEquals(
                "Message successfully sent.",
                manager.processMessage(message, "1"));

        assertEquals(1, manager.returnTotalMessages());
    }

    @Test
    public void testDiscardMessage() {
        Message message = new Message(
                "+277118693002", "Hello Mike", 1);

        MessageManager manager = new MessageManager();

        assertEquals(
                "Press 0 to delete the message.",
                manager.processMessage(message, "2"));

        assertEquals(0, manager.returnTotalMessages());
    }
}