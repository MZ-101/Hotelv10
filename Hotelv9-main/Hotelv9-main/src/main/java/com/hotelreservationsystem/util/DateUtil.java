package com.hotelreservationsystem.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * DateUtil - Business logic utility for date calculations and validations
 * Handles reservation dates, checkout fees, and refund calculations
 * All methods follow the hotel's business rules and policies
 */
public class DateUtil {

    // Standard checkout time is 11:00 AM
    private static final LocalTime STANDARD_CHECKOUT = LocalTime.of(11, 0);

    /**
     * Calculates the number of nights between check-in and check-out dates
     * @param checkIn Check-in date
     * @param checkOut Check-out date
     * @return Number of nights (days between dates)
     */
    public static long calculateNights(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Check-in and check-out dates cannot be null");
        }
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    /**
     * Validates if a date range is valid for a reservation
     * Rules: Both dates must not be null, check-in cannot be in past, check-out must be after check-in
     * @param checkIn Check-in date
     * @param checkOut Check-out date
     * @return true if valid, false otherwise
     */
    public static boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            return false;
        }
        // Check-in date cannot be in the past
        if (checkIn.isBefore(LocalDate.now())) {
            return false;
        }
        // Check-out must be after check-in (at least 1 night)
        if (!checkOut.isAfter(checkIn)) {
            return false;
        }
        return true;
    }

    /**
     * Calculates refund percentage based on cancellation date
     * Refund Policy:
     * - More than 7 days before check-in: 100% refund
     * - Exactly 7 days before check-in: 90% refund
     * - Less than 7 days before check-in: 0% refund (no refund)
     * @param checkInDate The check-in date of the reservation
     * @param cancelDate The date when cancellation was made
     * @return Refund percentage as decimal (1.00 = 100%, 0.90 = 90%, 0.00 = 0%)
     */
    public static double getRefundPercentage(LocalDate checkInDate, LocalDate cancelDate) {
        if (checkInDate == null || cancelDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        // Calculate days between cancellation and check-in
        long daysBeforeCheckIn = ChronoUnit.DAYS.between(cancelDate, checkInDate);

        if (daysBeforeCheckIn > 7) {
            return 1.00; // 100% refund
        } else if (daysBeforeCheckIn == 7) {
            return 0.90; // 90% refund
        } else if (daysBeforeCheckIn > 0) {
            return 0.00; // No refund (cancelled within 7 days)
        } else {
            return 0.00; // No refund (cancelled after or on check-in date)
        }
    }

    /**
     * Calculates the refund amount based on total paid and cancellation policy
     * @param totalPaid The total amount originally paid for the reservation
     * @param checkInDate The check-in date of the reservation
     * @param cancelDate The date when cancellation was made
     * @return Refund amount (0 if no refund, or percentage of total paid)
     */
    public static double calculateRefundAmount(double totalPaid, LocalDate checkInDate, LocalDate cancelDate) {
        double percentage = getRefundPercentage(checkInDate, cancelDate);
        return totalPaid * percentage;
    }

    /**
     * Calculates late checkout fees based on actual checkout time
     * Standard checkout time is 11:00 AM
     * Fee Structure:
     * - 1-2 hours late: PHP 500
     * - 2-4 hours late: PHP 1,000
     * - Over 4 hours late: PHP 2,000 (or charge for extra night)
     * @param checkoutTime The actual checkout time
     * @return Late checkout fee (0 if on time or early)
     */
    public static double calculateCheckoutFee(LocalTime checkoutTime) {
        if (checkoutTime == null) {
            return 0;
        }

        // If checkout is at or before standard time, no fee
        if (!checkoutTime.isAfter(STANDARD_CHECKOUT)) {
            return 0;
        }

        // Calculate minutes late and convert to hours
        long minutesLate = ChronoUnit.MINUTES.between(STANDARD_CHECKOUT, checkoutTime);
        long hoursLate = minutesLate / 60;

        // Apply fee based on hours late
        if (hoursLate <= 2) {
            return 500.00; // 1-2 hours late: PHP 500
        } else if (hoursLate <= 4) {
            return 1000.00; // 2-4 hours late: PHP 1,000
        } else {
            return 2000.00; // Over 4 hours late: PHP 2,000 (or charge for extra night)
        }
    }

    /**
     * Checks if a cancellation is within the full refund window (7+ days before check-in)
     * @param checkInDate The check-in date of the reservation
     * @param cancelDate The date when cancellation was made
     * @return true if refund window is available (7+ days), false otherwise
     */
    public static boolean isWithinRefundWindow(LocalDate checkInDate, LocalDate cancelDate) {
        if (checkInDate == null || cancelDate == null) {
            return false;
        }
        long daysBeforeCheckIn = ChronoUnit.DAYS.between(cancelDate, checkInDate);
        return daysBeforeCheckIn > 7;
    }

    /**
     * Calculates the difference in days between two dates
     * @param from Starting date
     * @param to Ending date
     * @return Number of days between dates
     */
    public static long dateDifference(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        return ChronoUnit.DAYS.between(from, to);
    }

    /**
     * Checks if a given date is in the past
     * @param date Date to check
     * @return true if date is before today, false otherwise
     */
    public static boolean isDateInPast(LocalDate date) {
        if (date == null) {
            return true;
        }
        return date.isBefore(LocalDate.now());
    }

    /**
     * Checks if a guest is at least 18 years old based on date of birth
     * @param dateOfBirth Guest's date of birth
     * @return true if guest is 18 or older, false otherwise
     */
    public static boolean isAdultAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate adultDate = dateOfBirth.plusYears(18);
        return !today.isBefore(adultDate);
    }

    /**
     * Calculates age from date of birth
     * @param dateOfBirth Date of birth
     * @return Age in years
     */
    public static int calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return 0;
        }
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }

    /**
     * Gets the standard checkout time for the hotel
     * @return LocalTime representing 11:00 AM
     */
    public static LocalTime getStandardCheckoutTime() {
        return STANDARD_CHECKOUT;
    }
}
