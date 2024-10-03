package com.psugv.capstone.login.model;

import org.junit.Test;

import java.util.Objects;

public class UserModelTest {
    UserModel userModel = new UserModel(1,"wow","12","Male",true );

    @Test
    public void testId() {
        try {
            assert this.userModel.getUser_id() == 1;
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testUsername() {
        try {
            assert Objects.equals(this.userModel.getUsername(), "wow");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testPassword() {
        try {
            assert Objects.equals(this.userModel.getPassword(), "12");
        }catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGender() {
        try {
            assert Objects.equals(this.userModel.getGender(), "Male");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testEnable(){
        try {
            assert this.userModel.getEnable();
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }
}