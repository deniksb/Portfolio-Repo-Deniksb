package com.servfinal.servfinal.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordHash {


    public String doHashing(String password) {
        StringBuilder sb;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));

            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(sb);
    }
}
