package com.example.zemtsov_7.service;

import com.example.zemtsov_7.service.db.UserService;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {
    private final UserService userService;

    public AuthService() {
        this.userService = new UserService();
    }

    public int auth(String login, String password) {
        String hashedPassword = hashPassword(password);
        return userService.getUserIdByLoginAndPassword(login, hashedPassword);
    }

    public int register(String first_name, String last_name, String login, String password) {
        if (userService.isUserExistsByLogin(login)) {
            return -1;
        }
        String hashedPassword = hashPassword(password);
        return userService.createUser(first_name, last_name, login, hashedPassword);
    }

    public String hashPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        BigInteger hashInt = new BigInteger(1, hashBytes);
        return hashInt.toString(16);
    }
}
