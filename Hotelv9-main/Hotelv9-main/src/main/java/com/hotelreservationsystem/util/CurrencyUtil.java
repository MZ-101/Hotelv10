package com.hotelreservationsystem.util;

import java.text.DecimalFormat;

/**
 * CurrencyUtil - Business logic utility for currency calculations and formatting
 * All monetary values are in Philippine Pesos (PHP)
 * Handles price calculations, tax computation, and currency formatting
 */
public class CurrencyUtil {

    // Tax rate is 12% as per hotel policy
    private static final double TAX_RATE = 0.12;
    // Decimal formatter for currency display (two decimal places with thousand separator)
    private static final DecimalFormat formatter = new DecimalFormat("#,##0.00");

    /**
     * Calculates the subtotal by multiplying room price per night by number of nights
     * Formula: subtotal = pricePerNight × nights
     * @param pricePerNight Room rate per night in PHP
     * @param nights Number of nights for the stay
     * @return Subtotal amount in PHP
     */
    public static double calculateSubtotal(double pricePerNight, long nights) {
        if (pricePerNight < 0 || nights < 0) {
            throw new IllegalArgumentException("Price and nights cannot be negative");
        }
        return pricePerNight * nights;
    }

    /**
     * Calculates the 12% tax on the subtotal
     * Formula: tax = subtotal × 0.12
     * @param subtotal The subtotal amount before tax
     * @return Tax amount (12% of subtotal) in PHP
     */
    public static double calculateTax(double subtotal) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }
        return subtotal * TAX_RATE;
    }

    /**
     * Calculates the final total after adding tax and subtracting discount
     * Formula: finalTotal = (subtotal + tax) - discount
     * @param subtotal The subtotal before tax
     * @param tax The tax amount (12% of subtotal)
     * @param discount Any discount applied to the reservation
     * @return Final total amount to be paid in PHP
     */
    public static double calculateFinalTotal(double subtotal, double tax, double discount) {
        if (subtotal < 0 || tax < 0 || discount < 0) {
            throw new IllegalArgumentException("Values cannot be negative");
        }
        double total = (subtotal + tax) - discount;
        // Ensure total is never negative
        return Math.max(0, total);
    }

    /**
     * Calculates the complete reservation cost in one call
     * Formula: finalTotal = (pricePerNight × nights × (1 + TAX_RATE)) - discount
     * @param pricePerNight Room rate per night
     * @param nights Number of nights
     * @param discount Any discount to apply
     * @return Final total amount to be paid
     */
    public static double calculateReservationTotal(double pricePerNight, long nights, double discount) {
        double subtotal = calculateSubtotal(pricePerNight, nights);
        double tax = calculateTax(subtotal);
        return calculateFinalTotal(subtotal, tax, discount);
    }

    /**
     * Formats a double amount as a PHP currency string
     * Example: 28000.0 becomes "PHP 28,000.00"
     * @param amount The amount to format
     * @return Formatted string with PHP currency symbol and thousand separators
     */
    public static String formatCurrency(double amount) {
        if (amount < 0) {
            return "PHP -" + formatter.format(Math.abs(amount));
        }
        return "PHP " + formatter.format(amount);
    }

    /**
     * Calculates change for cash payments
     * Returns the difference if customer overpays, otherwise returns 0
     * @param amountPaid The amount paid by customer in PHP
     * @param totalDue The total amount due in PHP
     * @return Change amount (positive if overpaid, 0 if exact or underpaid)
     */
    public static double calculateChange(double amountPaid, double totalDue) {
        if (amountPaid >= totalDue) {
            return amountPaid - totalDue;
        }
        return 0.0;
    }

    /**
     * Checks if a payment amount is valid for processing
     * Valid range: greater than 0 and not exceeding 999,999.99 PHP
     * @param amount The amount to validate
     * @return true if amount is valid, false otherwise
     */
    public static boolean isValidAmount(double amount) {
        return amount > 0 && amount <= 999999.99;
    }

    /**
     * Safely converts a String to Double
     * Removes commas and trims whitespace before parsing
     * Returns 0.0 if the string cannot be parsed (for safety)
     * @param value String representation of the number
     * @return Double value, or 0.0 if parsing fails
     */
    public static double convertToDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.replace(",", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * Formats a double value as a string with 2 decimal places
     * Used for database storage and display
     * @param amount The amount to format
     * @return String with format "#,##0.00"
     */
    public static String formatAmount(double amount) {
        return formatter.format(amount);
    }

    /**
     * Gets the current tax rate applied to all reservations
     * @return Tax rate as decimal (0.12 = 12%)
     */
    public static double getTaxRate() {
        return TAX_RATE;
    }

    /**
     * Calculates the balance remaining after a partial payment
     * @param totalDue Original amount due
     * @param amountPaid Amount already paid
     * @return Remaining balance to be paid
     */
    public static double calculateBalance(double totalDue, double amountPaid) {
        double balance = totalDue - amountPaid;
        return Math.max(0, balance);
    }

    /**
     * Rounds a currency amount to 2 decimal places
     * @param amount The amount to round
     * @return Rounded amount to 2 decimal places
     */
    public static double roundToTwoDecimals(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}
