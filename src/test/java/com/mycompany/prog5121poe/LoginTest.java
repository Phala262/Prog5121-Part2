/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog5121poe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
/**
 *
 * @author Student
 */
public class LoginTest {
    
 private Login createValidLogin() {
        return new Login(
                "John",
                "Smith",
                "kyl_1",
                "Ch&seC@ke991!",
                "+27839868976"
        );
    }

    @Test
    public void testValidUsername() {
        assertTrue(createValidLogin().checkUserName());
    }

    @Test
    public void testInvalidUsername() {
        Login login = new Login("John", "Smith", "kyle111",
                "Ch&seC@ke991!", "+27839868976");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testValidPassword() {
        assertTrue(createValidLogin().checkPasswordComplexity());
    }

    @Test
    public void testPasswordWithoutCapitalLetter() {
        Login login = new Login("John", "Smith", "kyl_1",
                "ch&sec@ke991!", "+27839868976");
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testValidCellphoneNumber() {
        assertTrue(createValidLogin().checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellphoneNumber() {
        Login login = new Login("John", "Smith", "kyl_1",
                "Ch&seC@ke991!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testSuccessfulRegistration() {
        assertEquals("User successfully registered.",
                createValidLogin().registerUser());
    }

    @Test
    public void testRegistrationWithInvalidUsername() {
        Login login = new Login("John", "Smith", "kyle111",
                "Ch&seC@ke991!", "+27839868976");
        assertTrue(login.registerUser().contains("Username"));
    }

    @Test
    public void testRegistrationWithInvalidPassword() {
        Login login = new Login("John", "Smith", "kyl_1",
                "password", "+27839868976");
        assertTrue(login.registerUser().contains("Password"));
    }

    @Test
    public void testRegistrationWithInvalidCellphone() {
        Login login = new Login("John", "Smith", "kyl_1",
                "Ch&seC@ke991!", "08966553");
        assertTrue(login.registerUser().contains("Cell phone"));
    }

    @Test
    public void testSuccessfulLogin() {
        Login login = createValidLogin();
        assertTrue(login.loginUser("kyl_1", "Ch&seC@ke991!"));
    }

    @Test
    public void testLoginWithIncorrectUsername() {
        Login login = createValidLogin();
        assertFalse(login.loginUser("wrong_1", "Ch&seC@ke991!"));
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        Login login = createValidLogin();
        assertFalse(login.loginUser("kyl_1", "WrongPassword1!"));
    }

    @Test
    public void testLoginStatusMessages() {
        Login login = createValidLogin();
        assertNotNull(login.returnLoginStatus(true));
        assertNotNull(login.returnLoginStatus(false));
    }
}


