package com.hotelreservationsystem.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * PasswordUtil - Business logic utility for password hashing and verification
 * Implements secure password storage using PBKDF2-style hashing
 * Never stores plain text passwords in the database
 */
public class PasswordUtil {

    private static final int SALT_LENGTH = 16; // Length of salt in bytes
    private static final int HASH_ITERATIONS = 10000; // Number of iterations for hashing

    /**
     * Generates a secure hash of the password with salt
     * Uses SHA-256 with PBKDF2-style approach
     * @param password Plain text password to hash
     * @return Hashed password with salt (format: salt:hash)
     */
    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        try {
            // Generate random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_LENGTH];
            random.nextBytes(salt);

            // Hash password with salt
            byte[] hash = pbkdf2(password.toCharArray(), salt, HASH_ITERATIONS);

            // Encode salt and hash to Base64 for storage
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encodedHash = Base64.getEncoder().encodeToString(hash);

            // Return combined string
            return encodedSalt + ":" + encodedHash;
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Verifies if a plain text password matches the stored hash
     * @param password Plain text password to verify
     * @param storedHash The stored hash from database (format: salt:hash)
     * @return true if password matches, false otherwise
     */
    public static boolean verifyPassword(String password, String storedHash) {
        if (password == null || password.isEmpty() || storedHash == null || storedHash.isEmpty()) {
            return false;
        }

        try {
            // Split stored hash into salt and hash parts
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                return false;
            }

            // Decode salt and hash from Base64
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedPasswordHash = Base64.getDecoder().decode(parts[1]);

            // Hash the provided password with the stored salt
            byte[] computedHash = pbkdf2(password.toCharArray(), salt, HASH_ITERATIONS);

            // Compare hashes (constant-time comparison to prevent timing attacks)
            return constantTimeEquals(computedHash, storedPasswordHash);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * PBKDF2 (Password-Based Key Derivation Function 2) implementation
     * Derives a key from password and salt using SHA-256
     * @param password Password characters
     * @param salt Salt bytes
     * @param iterations Number of iterations
     * @return Derived key hash
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] result = md.digest(String.valueOf(password).getBytes(StandardCharsets.UTF_8));

            for (int i = 1; i < iterations; i++) {
                md.reset();
                result = md.digest(result);
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error in PBKDF2", e);
        }
    }

    /**
     * Constant-time comparison of two byte arrays
     * Prevents timing attacks by always comparing all bytes
     * @param a First byte array
     * @param b Second byte array
     * @return true if arrays are equal, false otherwise
     */
    private static boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }

        return result == 0;
    }

    /**
     * Checks if a password meets strength requirements
     * Requirements: Min 8 chars, uppercase, lowercase, digit
     * @param password Password to check
     * @return true if password is strong, false otherwise
     */
    public static boolean isStrongPassword(String password) {
        return ValidationUtil.isValidPassword(password);
    }

    /**
     * Generates a random temporary password for admin account creation
     * Format: 8 characters including uppercase, lowercase, digit, special char
     * @return Randomly generated temporary password
     */
    public static String generateTemporaryPassword() {
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "@#$%&";
        String all = uppercase + lowercase + digits + special;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one of each type
        password.append(uppercase.charAt(random.nextInt(uppercase.length())));
        password.append(lowercase.charAt(random.nextInt(lowercase.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        // Fill remaining characters
        for (int i = 4; i < 8; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }

        // Shuffle the password
        String pass = password.toString();
        char[] chars = pass.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int randomIndex = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[randomIndex];
            chars[randomIndex] = temp;
        }

        return new String(chars);
    }
}
