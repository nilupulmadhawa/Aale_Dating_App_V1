package com.example.aale.model;

public class UserFactory {
    public static User getCustomer(){
        return new Customer();
    }
    public static User getAdmin(){
        return new Admin();
    }
}
