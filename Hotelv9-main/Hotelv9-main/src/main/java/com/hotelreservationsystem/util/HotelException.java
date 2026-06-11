package com.hotelreservationsystem.util;

/**
 * HotelException - Custom exception class for Hotel Reservation System
 * Used for all business logic errors and validation failures
 * Allows for specific error handling throughout the application
 */
public class HotelException extends Exception {

    // Exception code for categorizing errors
    private String errorCode;

    /**
     * Default constructor with message only
     * @param message Error message to display to user
     */
    public HotelException(String message) {
        super(message);
        this.errorCode = "GENERAL_ERROR";
    }

    /**
     * Constructor with message and error code
     * @param message Error message to display to user
     * @param errorCode Error code for categorization
     */
    public HotelException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Constructor with message and cause (for wrapping exceptions)
     * @param message Error message to display to user
     * @param cause The underlying exception
     */
    public HotelException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "GENERAL_ERROR";
    }

    /**
     * Constructor with message, error code, and cause
     * @param message Error message to display to user
     * @param errorCode Error code for categorization
     * @param cause The underlying exception
     */
    public HotelException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code associated with this exception
     * @return Error code string
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code for this exception
     * @param errorCode Error code string
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Exception for validation errors
     */
    public static class ValidationException extends HotelException {
        public ValidationException(String message) {
            super(message, "VALIDATION_ERROR");
        }
    }

    /**
     * Exception for database errors
     */
    public static class DatabaseException extends HotelException {
        public DatabaseException(String message) {
            super(message, "DATABASE_ERROR");
        }
        public DatabaseException(String message, Throwable cause) {
            super(message, "DATABASE_ERROR", cause);
        }
    }

    /**
     * Exception for authentication errors
     */
    public static class AuthenticationException extends HotelException {
        public AuthenticationException(String message) {
            super(message, "AUTHENTICATION_ERROR");
        }
    }

    /**
     * Exception for authorization errors (access denied)
     */
    public static class AuthorizationException extends HotelException {
        public AuthorizationException(String message) {
            super(message, "AUTHORIZATION_ERROR");
        }
    }

    /**
     * Exception for reservation conflicts (double-booking, etc.)
     */
    public static class ReservationException extends HotelException {
        public ReservationException(String message) {
            super(message, "RESERVATION_ERROR");
        }
    }

    /**
     * Exception for payment processing errors
     */
    public static class PaymentException extends HotelException {
        public PaymentException(String message) {
            super(message, "PAYMENT_ERROR");
        }
    }

    /**
     * Exception for room management errors
     */
    public static class RoomException extends HotelException {
        public RoomException(String message) {
            super(message, "ROOM_ERROR");
        }
    }

    /**
     * Exception for user management errors
     */
    public static class UserException extends HotelException {
        public UserException(String message) {
            super(message, "USER_ERROR");
        }
    }

    /**
     * Exception for guest management errors
     */
    public static class GuestException extends HotelException {
        public GuestException(String message) {
            super(message, "GUEST_ERROR");
        }
    }

    /**
     * Returns a formatted error message for display
     * @return Formatted error message with code
     */
    @Override
    public String toString() {
        return "[" + errorCode + "] " + getMessage();
    }
}
