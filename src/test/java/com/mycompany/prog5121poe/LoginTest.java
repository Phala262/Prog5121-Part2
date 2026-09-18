/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121poe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
/**
 *
 * @author Student
 */
public class LoginTest {
    
    // Test that the username is correctly formatted
    @Test
    public void testCheckUserNameCorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.checkUserName());
    }

    // Test that the username is incorrectly formatted
    @Test
    public void testCheckUserNameIncorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyle111",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(login.checkUserName());
    }

    // Test that the password meets the complexity requirements
    @Test
    public void testPasswordMeetsComplexityRequirements() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.checkPasswordComplexity());
    }

    // Test that the password does not meet complexity requirements
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "password",
                "+27838968976"
        );

        assertFalse(login.checkPasswordComplexity());
    }

    // Test correctly formatted cellphone number
    @Test
    public void testCellPhoneNumberCorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(login.checkCellPhoneNumber());
    }

    // Test incorrectly formatted cellphone number
    @Test
    public void testCellPhoneNumberIncorrectlyFormatted() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        assertFalse(login.checkCellPhoneNumber());
    }

    // Test successful login
    @Test
    public void testLoginSuccessful() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertTrue(
                login.loginUser(
                        "kyl_1",
                        "Ch&&sec@ke99!"
                )
        );
    }

    // Test failed login
    @Test
    public void testLoginFailed() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertFalse(
                login.loginUser(
                        "wrong",
                        "password"
                )
        );
    }

    // Test successful login message
    @Test
    public void testSuccessfulLoginMessage() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Welcome John Smith, it is great to see you again.",
                login.returnLoginStatus(true)
        );
    }

    // Test failed login message
    @Test
    public void testFailedLoginMessage() {

        Login login = new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                login.returnLoginStatus(false)
        );
    }
}


