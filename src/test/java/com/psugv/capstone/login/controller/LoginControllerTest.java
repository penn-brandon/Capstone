package com.psugv.capstone.login.controller;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class LoginControllerTest {
    private LoginController loginController = new LoginController();

    @Test
    /**     * This method is designed to test toMil method in the UnitsConvertor class.     */
    public void analyzeLogin() {
        assertEquals("redirect:/index", loginController.mainPagePath());
        assertEquals("/login", loginController.loginPagePath());
        assertEquals("/index", loginController.toIndexPage());
        assertEquals("/error", loginController.toErrorPage());
        assertEquals("/chat", loginController.toChatPage(new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return Map.of();
            }
        }));
    }
}