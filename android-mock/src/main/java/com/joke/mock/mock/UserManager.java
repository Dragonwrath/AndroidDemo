package com.joke.mock.mock;


public class UserManager {
    public void performLogin(String username, String password){
        System.out.println("performLogin username = " + username);
        System.out.println("performLogin password = " + password);
    }

    public void singIn(String username, String password) {
        System.out.println("singIn username = " + username);
        System.out.println("singIn password = " + password);
    }

    public void validate(String username, String password, NetworkCallback networkCallback) {
        System.out.println("validate ");

    }
}
