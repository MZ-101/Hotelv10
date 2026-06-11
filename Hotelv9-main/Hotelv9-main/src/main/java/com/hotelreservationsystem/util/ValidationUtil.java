package com.hotelreservationsystem.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * ValidationUtil - Business logic utility for input validation
 * Validates all user inputs according to hotel system rules
 * Ensures data integrity and prevents invalid data from entering the system
 */
public class ValidationUtil {

    // Regular expression patterns for validation
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9_]{5,20}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(09|\\+639)\\d{9}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s'-]{2,50}$");
    private static final Pattern ROOM_NUMBER_PATTERN = Pattern.compile("^[0-9]{1,3}[A-Z]?$");
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^[0-9]{13,19}$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^[0-9]{3,4}$");
    private static final Pattern OTP_PATTERN = Pattern.compile("^[0-9]{6}$");

    /**
     * Validates username format
     * Rules: 5-20 alphanumeric characters, underscore allowed, no spaces or special chars
     * @param username Username to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return USERNAME_PATTERN.matcher(username.trim()).matches();
    }

    /**
     * Validates password strength
     * Rules: Minimum 8 characters, must contain uppercase, lowercase, and number
     * @param password Password to validate
     * @return true if password meets strength requirements, false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        // Check for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        // Check for at least one digit
        if (!password.matches(".*[0-9].*")) {
            return false;
        }
        return true;
    }

    /**
     * Validates email format
     * Standard email validation with @ and domain
     * @param email Email address to validate
     * @return true if valid email format, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Validates Philippine mobile phone number format
     * Rules: Must start with 09 or +639, total 11-12 digits
     * Format: 09XXXXXXXXX or +639XXXXXXXXX
     * @param phoneNumber Phone number to validate
     * @return true if valid Philippine format, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        String trimmed = phoneNumber.trim();
        // Remove spaces and hyphens
        trimmed = trimmed.replaceAll("[\\s-]", "");
        return PHONE_PATTERN.matcher(trimmed).matches();
    }

    /**
     * Validates a person's first name
     * Rules: Letters only, 2-50 characters, no numbers or special characters
     * @param firstName First name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            return false;
        }
        String trimmed = firstName.trim();
        // Check if starts with space
        if (firstName.startsWith(" ")) {
            return false;
        }
        // Only letters, spaces, hyphens, and apostrophes allowed
        return trimmed.matches("^[a-zA-Z\\s'-]{2,50}$") && !trimmed.matches(".*\\d.*");
    }

    /**
     * Validates a person's last name
     * Rules: Letters only, 2-50 characters, no numbers or special characters
     * @param lastName Last name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return false;
        }
        String trimmed = lastName.trim();
        // Check if starts with space
        if (lastName.startsWith(" ")) {
            return false;
        }
        // Only letters, spaces, hyphens, and apostrophes allowed
        return trimmed.matches("^[a-zA-Z\\s'-]{2,50}$") && !trimmed.matches(".*\\d.*");
    }

    /**
     * Validates a date to ensure it's not in the past
     * @param date Date to validate
     * @return true if date is today or in the future, false if in past
     */
    public static boolean isValidFutureDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        return !date.isBefore(LocalDate.now());
    }

    /**
     * Validates date of birth
     * Rules: Must be a valid date, not in future, guest must be at least 18 years old
     * @param dateOfBirth Date of birth to validate
     * @return true if valid (guest is 18+), false otherwise
     */
    public static boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        // Cannot be in the future
        if (dateOfBirth.isAfter(LocalDate.now())) {
            return false;
        }
        // Must be at least 18 years old
        return DateUtil.isAdultAge(dateOfBirth);
    }

    /**
     * Validates address field
     * Rules: Not empty, maximum 255 characters
     * @param address Address to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        return address.length() <= 255;
    }

    /**
     * Validates ID document number
     * Rules: Minimum 5 characters, not empty
     * @param idNumber ID document number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidIdNumber(String idNumber) {
        if (idNumber == null || idNumber.trim().isEmpty()) {
            return false;
        }
        return idNumber.trim().length() >= 5;
    }

    /**
     * Validates a date range for reservations
     * Rules: Check-in and check-out both valid, check-out after check-in, check-in not in past
     * @param checkInDate Check-in date
     * @param checkOutDate Check-out date
     * @return true if valid date range, false otherwise
     */
    public static boolean isValidReservationDateRange(LocalDate checkInDate, LocalDate checkOutDate) {
        return DateUtil.isValidDateRange(checkInDate, checkOutDate);
    }

    /**
     * Validates number of guests
     * Rules: Minimum 1 guest, cannot exceed room capacity (10 max)
     * @param numberOfGuests Number of guests
     * @param roomCapacity Maximum room capacity
     * @return true if valid, false otherwise
     */
    public static boolean isValidNumberOfGuests(int numberOfGuests, int roomCapacity) {
        return numberOfGuests >= 1 && numberOfGuests <= roomCapacity && roomCapacity >= 1 && roomCapacity <= 10;
    }

    /**
     * Validates price range
     * Rules: Minimum price >= 0, maximum price <= 999,999.99
     * @param minPrice Minimum price to validate
     * @param maxPrice Maximum price to validate
     * @return true if valid price range, false otherwise
     */
    public static boolean isValidPriceRange(double minPrice, double maxPrice) {
        return minPrice >= 0 && maxPrice <= 999999.99 && minPrice <= maxPrice;
    }

    /**
     * Validates credit card number using Luhn algorithm
     * Rules: 13-19 digits, must pass Luhn validation
     * @param cardNumber Credit card number (digits only)
     * @return true if valid format and passes Luhn check, false otherwise
     */
    public static boolean isValidCreditCard(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return false;
        }
        String digits = cardNumber.replaceAll("[^0-9]", "");
        // Check length (13-19 digits)
        if (digits.length() < 13 || digits.length() > 19) {
            return false;
        }
        // Luhn algorithm check
        return luhnCheck(digits);
    }

    /**
     * Validates credit card expiry date
     * Rules: Format MM/YY or MM/YYYY, cannot be expired
     * @param expiryDate Expiry date in MM/YY or MM/YYYY format
     * @return true if valid and not expired, false otherwise
     */
    public static boolean isValidCardExpiry(String expiryDate) {
        if (expiryDate == null || expiryDate.isEmpty()) {
            return false;
        }
        try {
            String[] parts = expiryDate.split("/");
            if (parts.length != 2) {
                return false;
            }
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]);

            // Validate month (1-12)
            if (month < 1 || month > 12) {
                return false;
            }

            // Handle 2-digit or 4-digit year
            if (year < 100) {
                year += 2000; // Assume 20xx for 2-digit years
            }

            // Check if not expired
            LocalDate today = LocalDate.now();
            LocalDate expiryMonthEnd = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
            return !expiryMonthEnd.isBefore(today);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates CVV (Card Verification Value)
     * Rules: 3 or 4 digits, numeric only
     * @param cvv CVV code to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidCVV(String cvv) {
        if (cvv == null || cvv.isEmpty()) {
            return false;
        }
        return CVV_PATTERN.matcher(cvv.trim()).matches();
    }

    /**
     * Validates cardholder name
     * Rules: Letters and spaces only, 3-50 characters
     * @param cardholderName Cardholder name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidCardholderName(String cardholderName) {
        if (cardholderName == null || cardholderName.isEmpty()) {
            return false;
        }
        return cardholderName.trim().matches("^[a-zA-Z\\s]{3,50}$");
    }

    /**
     * Validates OTP (One-Time Password)
     * Rules: Exactly 6 digits, numeric only
     * @param otp OTP to validate
     * @return true if valid 6-digit OTP, false otherwise
     */
    public static boolean isValidOTP(String otp) {
        if (otp == null || otp.isEmpty()) {
            return false;
        }
        return OTP_PATTERN.matcher(otp.trim()).matches();
    }

    /**
     * Validates payment amount
     * Rules: Greater than 0, not exceeding 999,999.99 PHP
     * @param amount Payment amount to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPaymentAmount(double amount) {
        return amount > 0 && amount <= 999999.99;
    }

    /**
     * Validates room number format
     * Rules: 1-3 digits, optional letter suffix (e.g., 101, 205A)
     * @param roomNumber Room number to validate
     * @return true if valid format, false otherwise
     */
    public static boolean isValidRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.isEmpty()) {
            return false;
        }
        return ROOM_NUMBER_PATTERN.matcher(roomNumber.trim()).matches();
    }

    /**
     * Validates room price
     * Rules: Must be positive, not exceeding 999,999.99 PHP
     * @param price Room price per night
     * @return true if valid, false otherwise
     */
    public static boolean isValidRoomPrice(double price) {
        return price > 0 && price <= 999999.99;
    }

    /**
     * Validates room capacity
     * Rules: Between 1-10 guests
     * @param capacity Room capacity
     * @return true if valid, false otherwise
     */
    public static boolean isValidRoomCapacity(int capacity) {
        return capacity >= 1 && capacity <= 10;
    }

    /**
     * Luhn algorithm for credit card validation
     * Validates the checksum of credit card numbers
     * @param cardNumber Card number digits only
     * @return true if passes Luhn check, false otherwise
     */
    private static boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean isSecond = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            if (isSecond) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            isSecond = !isSecond;
        }
        return sum % 10 == 0;
    }
}
