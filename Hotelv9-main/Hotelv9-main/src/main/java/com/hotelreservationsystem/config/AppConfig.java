package com.hotelreservationsystem.config;

import com.hotelreservationsystem.util.HotelException;

import java.awt.*;

/**
 * AppConfig - Application-Wide Configuration
 * 
 * Centralized configuration for the Hotel Reservation System application.
 * Stores and provides access to:
 * - Window dimensions and positions
 * - Color schemes and styling
 * - Font definitions
 * - Application-wide constants
 * - UI/UX settings
 * 
 * @author Hotel Reservation System Team
 * @version 1.0.0
 */
public class AppConfig {
    
    // ==================== Window Dimensions ====================
    
    /** Main application window width */
    public static final int WINDOW_WIDTH = 1200;
    
    /** Main application window height */
    public static final int WINDOW_HEIGHT = 700;
    
    /** Login frame width */
    public static final int LOGIN_WINDOW_WIDTH = 600;
    
    /** Login frame height */
    public static final int LOGIN_WINDOW_HEIGHT = 500;
    
    // ==================== Color Scheme ====================
    
    /** Primary dark color for headers and navigation */
    public static final Color COLOR_PRIMARY_DARK = Color.decode("#222222");
    
    /** Primary light color for backgrounds */
    public static final Color COLOR_PRIMARY_LIGHT = Color.decode("#F5F5F5");
    
    /** Secondary color for buttons and accents */
    public static final Color COLOR_SECONDARY = Color.decode("#2196F3");
    
    /** Success color for positive actions */
    public static final Color COLOR_SUCCESS = Color.decode("#4CAF50");
    
    /** Warning color for warnings */
    public static final Color COLOR_WARNING = Color.decode("#FF9800");
    
    /** Error color for errors and alerts */
    public static final Color COLOR_ERROR = Color.decode("#F44336");
    
    /** Text color for dark backgrounds */
    public static final Color COLOR_TEXT_LIGHT = Color.WHITE;
    
    /** Text color for light backgrounds */
    public static final Color COLOR_TEXT_DARK = Color.BLACK;
    
    /** Border color */
    public static final Color COLOR_BORDER = Color.decode("#C8C8C8");
    
    // ==================== Fonts ====================
    
    /** Title font (Arial Black, Bold, Size 20) */
    public static final Font FONT_TITLE = new Font("Arial Black", Font.BOLD, 20);
    
    /** Subtitle font (Arial Black, Bold, Size 14) */
    public static final Font FONT_SUBTITLE = new Font("Arial Black", Font.BOLD, 14);
    
    /** Label font (Arial, Bold, Size 11) */
    public static final Font FONT_LABEL = new Font("Arial", Font.BOLD, 11);
    
    /** Regular text font (Arial, Plain, Size 11) */
    public static final Font FONT_REGULAR = new Font("Arial", Font.PLAIN, 11);
    
    /** Button font (Arial, Bold, Size 11) */
    public static final Font FONT_BUTTON = new Font("Arial", Font.BOLD, 11);
    
    /** Small text font (Arial, Plain, Size 10) */
    public static final Font FONT_SMALL = new Font("Arial", Font.PLAIN, 10);
    
    // ==================== Component Dimensions ====================
    
    /** Standard button width */
    public static final int BUTTON_WIDTH = 100;
    
    /** Standard button height */
    public static final int BUTTON_HEIGHT = 30;
    
    /** Standard input field height */
    public static final int INPUT_FIELD_HEIGHT = 25;
    
    /** Standard label height */
    public static final int LABEL_HEIGHT = 20;
    
    /** Standard panel border width */
    public static final int PANEL_BORDER_WIDTH = 1;
    
    // ==================== Spacing & Padding ====================
    
    /** Standard padding */
    public static final int PADDING = 15;
    
    /** Small padding */
    public static final int PADDING_SMALL = 10;
    
    /** Large padding */
    public static final int PADDING_LARGE = 20;
    
    /** Gap between components */
    public static final int COMPONENT_GAP = 10;
    
    // ==================== UI Settings ====================
    
    /** Page size for table pagination */
    public static final int TABLE_PAGE_SIZE = 10;
    
    /** Table row height */
    public static final int TABLE_ROW_HEIGHT = 22;
    
    /** Enable/disable animations */
    public static final boolean ENABLE_ANIMATIONS = true;
    
    /** Application timeout in seconds */
    public static final int SESSION_TIMEOUT_MINUTES = 30;
    
    // ==================== Data Validation ====================
    
    /** Minimum password length */
    public static final int MIN_PASSWORD_LENGTH = 8;
    
    /** Maximum password length */
    public static final int MAX_PASSWORD_LENGTH = 50;
    
    /** Minimum username length */
    public static final int MIN_USERNAME_LENGTH = 5;
    
    /** Maximum username length */
    public static final int MAX_USERNAME_LENGTH = 20;
    
    /** Minimum name length */
    public static final int MIN_NAME_LENGTH = 2;
    
    /** Maximum name length */
    public static final int MAX_NAME_LENGTH = 50;
    
    /** Minimum room capacity */
    public static final int MIN_ROOM_CAPACITY = 1;
    
    /** Maximum room capacity */
    public static final int MAX_ROOM_CAPACITY = 10;
    
    /** Minimum stay duration (nights) */
    public static final int MIN_STAY_NIGHTS = 1;
    
    /** Maximum stay duration (nights) */
    public static final int MAX_STAY_NIGHTS = 365;
    
    /** Minimum age for guests */
    public static final int MIN_GUEST_AGE = 18;
    
    // ==================== Business Logic ====================
    
    /** Tax rate (as decimal, e.g., 0.12 for 12%) */
    public static final double TAX_RATE = 0.12;
    
    /** Standard checkout time (24-hour format) */
    public static final String CHECKOUT_TIME = "11:00";
    
    /** Late checkout fee for 1-2 hours (PHP) */
    public static final double LATE_CHECKOUT_FEE_1_2_HOURS = 500.00;
    
    /** Late checkout fee for 2-4 hours (PHP) */
    public static final double LATE_CHECKOUT_FEE_2_4_HOURS = 1000.00;
    
    /** Late checkout fee for over 4 hours (PHP) */
    public static final double LATE_CHECKOUT_FEE_OVER_4_HOURS = 2000.00;
    
    /** Refund percentage for 7+ days before checkin */
    public static final double REFUND_PERCENTAGE_FULL = 1.00; // 100%
    
    /** Refund percentage for exactly 7 days before checkin */
    public static final double REFUND_PERCENTAGE_7_DAYS = 0.90; // 90%
    
    /** Refund percentage for less than 7 days before checkin */
    public static final double REFUND_PERCENTAGE_LESS_7_DAYS = 0.00; // 0%
    
    // ==================== Currency ====================
    
    /** Currency symbol */
    public static final String CURRENCY_SYMBOL = "PHP";
    
    /** Currency decimal places */
    public static final int CURRENCY_DECIMAL_PLACES = 2;
    
    // ==================== Initialization ====================
    
    /**
     * Initialize application configuration
     * 
     * @throws HotelException if initialization fails
     */
    public static void initialize() throws HotelException {
        // Validate database connection
        try {
            DBConfig.getConnection().close();
        } catch (Exception e) {
            throw new HotelException("Failed to initialize database connection: " + e.getMessage());
        }
    }
    
    /**
     * Get formatted window size
     * 
     * @return Dimension object with window width and height
     */
    public static Dimension getWindowSize() {
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    }
    
    /**
     * Get formatted login window size
     * 
     * @return Dimension object with login window width and height
     */
    public static Dimension getLoginWindowSize() {
        return new Dimension(LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
    }
    
    /**
     * Get currency display format
     * 
     * @param amount the amount to format
     * @return formatted currency string (e.g., "PHP 1,234.56")
     */
    public static String formatCurrency(double amount) {
        return String.format("%s %,.2f", CURRENCY_SYMBOL, amount);
    }
    
    /**
     * Private constructor to prevent instantiation
     * This is a utility class with only static members
     */
    private AppConfig() {
        throw new AssertionError("Cannot instantiate utility class AppConfig");
    }
}
