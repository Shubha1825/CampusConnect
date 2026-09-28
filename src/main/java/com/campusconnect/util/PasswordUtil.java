package com.campusconnect.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Simple password hashing helper.
 *
 * NOTE: SHA-256 without a per-user salt is NOT considered fully secure
 * for production systems (a proper app should use BCrypt/Argon2 with a
 * random salt per user). For a student/college project this is a big
 * improvement over storing plaintext passwords and is fine to submit.
 */
public class PasswordUtil {

    public static String hash(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(plainPassword.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean verify(String plainPassword, String hashedPassword) {
        String hashOfInput = hash(plainPassword);
        return hashOfInput.equals(hashedPassword);
    }
}
