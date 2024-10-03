package com.psugv.capstone.controller.login;

import com.psugv.capstone.login.controller.LoginController;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.service.ILoginService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginControllerTest {


    private LoginController loginController = new LoginController();

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    /**
     * This method is designed to test toMil method in the UnitsConvertor class.
     */
    public void analyzeLogin() {

        assertEquals("redirect:/index", loginController.mainPagePath());
        assertEquals("/open/login", loginController.loginPagePath());
        assertEquals("/open/index", loginController.toIndexPage());
        assertEquals("/open/error", loginController.toErrorPage());
        assertEquals("/main/chat", loginController.toChatPage(new Model() {
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
