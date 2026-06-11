package com.hotelreservationsystem.util;

/**
 * Constants - System-wide constants and configuration values
 * Centralized location for all magic numbers, strings, and configuration values
 * Ensures consistency across the entire application
 */
public class Constants {

    // ============ APPLICATION INFORMATION ============
    public static final String APP_NAME = "Hotel Reservation System (HRS)";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_TITLE = "Hotel Reservation System";

    // ============ DATABASE CONFIGURATION ============
    public static final String DB_HOST = "localhost";
    public static final int DB_PORT = 3306;
    public static final String DB_NAME = "hotel_reservation_system";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = ""; // Configure as needed
    public static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    // ============ CURRENCY AND TAX ============
    public static final String CURRENCY_CODE = "PHP";
    public static final String CURRENCY_SYMBOL = "₱";
    public static final double TAX_RATE = 0.12; // 12% tax
    public static final double MAX_PRICE = 999999.99;
    public static final double MIN_PRICE = 0.0;

    // ============ CHECKOUT AND LATE FEES ============
    public static final int STANDARD_CHECKOUT_HOUR = 11;
    public static final int STANDARD_CHECKOUT_MINUTE = 0;
    public static final double LATE_CHECKOUT_FEE_1_2_HOURS = 500.0; // PHP 500
    public static final double LATE_CHECKOUT_FEE_2_4_HOURS = 1000.0; // PHP 1,000
    public static final double LATE_CHECKOUT_FEE_OVER_4_HOURS = 2000.0; // PHP 2,000

    // ============ REFUND POLICY ============
    public static final int FULL_REFUND_DAYS = 7; // 7+ days before check-in = 100% refund
    public static final int PARTIAL_REFUND_DAYS = 7; // Exactly 7 days = 90% refund
    public static final double FULL_REFUND_PERCENTAGE = 1.00; // 100%
    public static final double PARTIAL_REFUND_PERCENTAGE = 0.90; // 90%
    public static final double NO_REFUND_PERCENTAGE = 0.00; // 0%

    // ============ RESERVATION CONSTRAINTS ============
    public static final int MIN_RESERVATION_NIGHTS = 1;
    public static final int MAX_RESERVATION_NIGHTS = 365;
    public static final int MIN_GUESTS = 1;
    public static final int MAX_GUESTS = 10;
    public static final int MIN_ROOM_CAPACITY = 1;
    public static final int MAX_ROOM_CAPACITY = 10;

    // ============ ROOM TYPES ============
    public static final String ROOM_TYPE_SINGLE_STANDARD = "Single Standard";
    public static final String ROOM_TYPE_DOUBLE_STANDARD = "Double Standard";
    public static final String ROOM_TYPE_DOUBLE_DELUXE = "Double Deluxe";
    public static final String ROOM_TYPE_SUITE_DELUXE = "Suite Deluxe";
    public static final String[] VALID_ROOM_TYPES = {
            ROOM_TYPE_SINGLE_STANDARD,
            ROOM_TYPE_DOUBLE_STANDARD,
            ROOM_TYPE_DOUBLE_DELUXE,
            ROOM_TYPE_SUITE_DELUXE
    };

    // ============ ROOM STATUS ============
    public static final String ROOM_STATUS_AVAILABLE = "Available";
    public static final String ROOM_STATUS_OCCUPIED = "Occupied";
    public static final String ROOM_STATUS_MAINTENANCE = "Maintenance";
    public static final String ROOM_STATUS_CLEANING = "Cleaning";
    public static final String[] VALID_ROOM_STATUS = {
            ROOM_STATUS_AVAILABLE,
            ROOM_STATUS_OCCUPIED,
            ROOM_STATUS_MAINTENANCE,
            ROOM_STATUS_CLEANING
    };

    // ============ RESERVATION STATUS ============
    public static final String RESERVATION_STATUS_CONFIRMED = "Confirmed";
    public static final String RESERVATION_STATUS_CHECKED_IN = "Checked-In";
    public static final String RESERVATION_STATUS_CHECKED_OUT = "Checked-Out";
    public static final String RESERVATION_STATUS_CANCELLED = "Cancelled";
    public static final String[] VALID_RESERVATION_STATUS = {
            RESERVATION_STATUS_CONFIRMED,
            RESERVATION_STATUS_CHECKED_IN,
            RESERVATION_STATUS_CHECKED_OUT,
            RESERVATION_STATUS_CANCELLED
    };

    // ============ PAYMENT METHOD ============
    public static final String PAYMENT_METHOD_CASH = "Cash";
    public static final String PAYMENT_METHOD_CREDIT_CARD = "Credit Card";
    public static final String PAYMENT_METHOD_E_WALLET = "E-Wallet";
    public static final String[] VALID_PAYMENT_METHODS = {
            PAYMENT_METHOD_CASH,
            PAYMENT_METHOD_CREDIT_CARD,
            PAYMENT_METHOD_E_WALLET
    };

    // ============ PAYMENT STATUS ============
    public static final String PAYMENT_STATUS_PENDING = "Pending";
    public static final String PAYMENT_STATUS_COMPLETED = "Completed";
    public static final String PAYMENT_STATUS_FAILED = "Failed";
    public static final String PAYMENT_STATUS_REFUNDED = "Refunded";
    public static final String[] VALID_PAYMENT_STATUS = {
            PAYMENT_STATUS_PENDING,
            PAYMENT_STATUS_COMPLETED,
            PAYMENT_STATUS_FAILED,
            PAYMENT_STATUS_REFUNDED
    };

    // ============ USER ACCESS LEVELS ============
    public static final String ACCESS_LEVEL_ADMIN = "Admin";
    public static final String ACCESS_LEVEL_RECEPTIONIST = "Receptionist";
    public static final String ACCESS_LEVEL_GUEST = "Guest";
    public static final String[] VALID_ACCESS_LEVELS = {
            ACCESS_LEVEL_ADMIN,
            ACCESS_LEVEL_RECEPTIONIST,
            ACCESS_LEVEL_GUEST
    };

    // ============ ID DOCUMENT TYPES ============
    public static final String ID_TYPE_PASSPORT = "Passport";
    public static final String ID_TYPE_DRIVERS_LICENSE = "Driver's License";
    public static final String ID_TYPE_NATIONAL_ID = "National ID";
    public static final String ID_TYPE_POSTAL_ID = "Postal ID";
    public static final String ID_TYPE_TIN = "TIN ID";
    public static final String[] VALID_ID_TYPES = {
            ID_TYPE_PASSPORT,
            ID_TYPE_DRIVERS_LICENSE,
            ID_TYPE_NATIONAL_ID,
            ID_TYPE_POSTAL_ID,
            ID_TYPE_TIN
    };

    // ============ CANCELLATION REASONS ============
    public static final String CANCEL_REASON_CHANGE_OF_PLANS = "Change of Plans";
    public static final String CANCEL_REASON_EMERGENCY = "Emergency";
    public static final String CANCEL_REASON_HEALTH = "Health";
    public static final String CANCEL_REASON_WORK = "Work";
    public static final String CANCEL_REASON_OTHER = "Other";
    public static final String[] VALID_CANCELLATION_REASONS = {
            CANCEL_REASON_CHANGE_OF_PLANS,
            CANCEL_REASON_EMERGENCY,
            CANCEL_REASON_HEALTH,
            CANCEL_REASON_WORK,
            CANCEL_REASON_OTHER
    };

    // ============ VALIDATION CONSTRAINTS ============
    public static final int MIN_USERNAME_LENGTH = 5;
    public static final int MAX_USERNAME_LENGTH = 20;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 50;
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MIN_ADDRESS_LENGTH = 5;
    public static final int MAX_ADDRESS_LENGTH = 255;
    public static final int MIN_ID_NUMBER_LENGTH = 5;
    public static final int MAX_ID_NUMBER_LENGTH = 100;
    public static final int CREDIT_CARD_MIN_DIGITS = 13;
    public static final int CREDIT_CARD_MAX_DIGITS = 19;
    public static final int CVV_MIN_DIGITS = 3;
    public static final int CVV_MAX_DIGITS = 4;
    public static final int OTP_LENGTH = 6;
    public static final int MIN_ROOM_AGE = 18; // Guest minimum age

    // ============ DATE FORMATS ============
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // ============ UI CONFIGURATION ============
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 700;
    public static final int WINDOW_MIN_WIDTH = 900;
    public static final int WINDOW_MIN_HEIGHT = 600;

    // ============ COLOR SCHEME ============
    public static final String COLOR_PRIMARY = "#007BFF";
    public static final String COLOR_SUCCESS = "#28A745";
    public static final String COLOR_DANGER = "#DC3545";
    public static final String COLOR_WARNING = "#FFC107";
    public static final String COLOR_INFO = "#17A2B8";
    public static final String COLOR_LIGHT = "#F8F9FA";
    public static final String COLOR_DARK = "#343A40";

    // ============ FILE PATHS ============
    public static final String LOG_DIR = "logs";
    public static final String LOG_FILE = "logs/hotel_system.log";
    public static final String RESOURCES_DIR = "resources";

    // ============ ERROR MESSAGES ============
    public static final String ERROR_DATABASE_CONNECTION = "Failed to connect to database";
    public static final String ERROR_INVALID_CREDENTIALS = "Invalid username or password";
    public static final String ERROR_INVALID_INPUT = "Invalid input provided";
    public static final String ERROR_DUPLICATE_USERNAME = "Username already exists";
    public static final String ERROR_RESERVATION_CONFLICT = "Room is not available for selected dates";
    public static final String ERROR_INVALID_PAYMENT = "Invalid payment information";
    public static final String ERROR_INSUFFICIENT_FUNDS = "Insufficient funds for payment";

    // ============ SUCCESS MESSAGES ============
    public static final String SUCCESS_LOGIN = "Login successful";
    public static final String SUCCESS_REGISTRATION = "Registration successful";
    public static final String SUCCESS_RESERVATION_CREATED = "Reservation created successfully";
    public static final String SUCCESS_PAYMENT_PROCESSED = "Payment processed successfully";
    public static final String SUCCESS_RESERVATION_CANCELLED = "Reservation cancelled successfully";
    public static final String SUCCESS_DATA_SAVED = "Data saved successfully";

    // ============ DEFAULT TEST ACCOUNTS ============
    public static final String DEFAULT_ADMIN_USERNAME = "admin";
    public static final String DEFAULT_ADMIN_PASSWORD = "Admin@123";
    public static final String DEFAULT_RECEPTIONIST_USERNAME = "receptionist";
    public static final String DEFAULT_RECEPTIONIST_PASSWORD = "Receptionist@123";
    public static final String DEFAULT_GUEST_USERNAME = "guest";
    public static final String DEFAULT_GUEST_PASSWORD = "Guest@123";

    // Private constructor to prevent instantiation
    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }
}
