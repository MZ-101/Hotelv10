package com.hotelreservationsystem.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Reservation - Model class representing a hotel room reservation
 * Encapsulates all reservation details including dates, guests, pricing, and status
 * Contains business logic for reservation calculations
 * Implements OOP principles: Encapsulation, Data Hiding, Business Logic
 */
public class Reservation {

    private int reservationId;
    private int guestId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private int numberOfGuests;
    private String reservationStatus; // Confirmed, Checked-In, Checked-Out, Cancelled
    private long numberOfNights;
    private double roomRate;
    private double totalPrice;
    private double discountApplied;
    private double finalTotal;
    private LocalDate reservationDate;
    private String notes;
    private LocalDateTime cancelledDate;
    private String cancellationReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor
     */
    public Reservation() {
        this.reservationStatus = "Confirmed";
        this.discountApplied = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Constructor with essential reservation information
     */
    public Reservation(int guestId, int roomId, LocalDate checkInDate, LocalDate checkOutDate,
                      int numberOfGuests, double roomRate) {
        this();
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.roomRate = roomRate;
        this.reservationDate = LocalDate.now();
        this.numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    /**
     * Constructor with all fields
     */
    public Reservation(int reservationId, int guestId, int roomId, LocalDate checkInDate,
                      LocalDate checkOutDate, LocalTime checkInTime, LocalTime checkOutTime,
                      int numberOfGuests, String reservationStatus, long numberOfNights,
                      double roomRate, double totalPrice, double discountApplied, double finalTotal,
                      LocalDate reservationDate, String notes, LocalDateTime cancelledDate,
                      String cancellationReason, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.numberOfGuests = numberOfGuests;
        this.reservationStatus = reservationStatus;
        this.numberOfNights = numberOfNights;
        this.roomRate = roomRate;
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
        this.finalTotal = finalTotal;
        this.reservationDate = reservationDate;
        this.notes = notes;
        this.cancelledDate = cancelledDate;
        this.cancellationReason = cancellationReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ============ GETTERS AND SETTERS ============

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
        recalculateNights();
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
        recalculateNights();
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public long getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(long numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public double getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(double roomRate) {
        this.roomRate = roomRate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(double discountApplied) {
        this.discountApplied = discountApplied;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(LocalDateTime cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ============ BUSINESS LOGIC METHODS ============

    /**
     * Recalculates number of nights based on check-in and check-out dates
     * Called automatically when dates are changed
     */
    private void recalculateNights() {
        if (checkInDate != null && checkOutDate != null) {
            this.numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        }
    }

    /**
     * Checks if reservation is confirmed
     * @return true if status is "Confirmed"
     */
    public boolean isConfirmed() {
        return "Confirmed".equals(this.reservationStatus);
    }

    /**
     * Checks if guest has checked in
     * @return true if status is "Checked-In"
     */
    public boolean isCheckedIn() {
        return "Checked-In".equals(this.reservationStatus);
    }

    /**
     * Checks if guest has checked out
     * @return true if status is "Checked-Out"
     */
    public boolean isCheckedOut() {
        return "Checked-Out".equals(this.reservationStatus);
    }

    /**
     * Checks if reservation is cancelled
     * @return true if status is "Cancelled"
     */
    public boolean isCancelled() {
        return "Cancelled".equals(this.reservationStatus);
    }

    /**
     * Checks if reservation can be cancelled (only Confirmed or Checked-In status)
     * @return true if reservation can be cancelled
     */
    public boolean canBeCancelled() {
        return isConfirmed() || isCheckedIn();
    }

    /**
     * Gets reservation duration description
     * Example: "5 nights, 6 guests"
     * @return Duration description
     */
    public String getDurationDescription() {
        return numberOfNights + " night" + (numberOfNights != 1 ? "s" : "") + ", " +
               numberOfGuests + " guest" + (numberOfGuests != 1 ? "s" : "");
    }

    /**
     * Gets reservation date range description
     * Example: "2024-06-15 to 2024-06-20"
     * @return Date range description
     */
    public String getDateRangeDescription() {
        return checkInDate + " to " + checkOutDate;
    }

    /**
     * Gets formatted price display
     * Example: "PHP 28,000.00"
     * @return Formatted final total
     */
    public String getFormattedFinalTotal() {
        return "PHP " + String.format("%,.2f", finalTotal);
    }

    /**
     * Gets full reservation summary
     * @return Summary string with all key information
     */
    public String getReservationSummary() {
        return "Reservation #" + reservationId +
               " | Guest ID: " + guestId +
               " | Room ID: " + roomId +
               " | " + getDateRangeDescription() +
               " | Status: " + reservationStatus +
               " | Total: " + getFormattedFinalTotal();
    }

    /**
     * Updates the updatedAt timestamp to current time
     */
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Returns string representation of Reservation object
     * @return Formatted reservation information
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", guestId=" + guestId +
                ", roomId=" + roomId +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numberOfNights=" + numberOfNights +
                ", numberOfGuests=" + numberOfGuests +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", finalTotal=" + finalTotal +
                '}';
    }

    /**
     * Checks if two Reservation objects are equal based on reservationId
     * @param obj Object to compare
     * @return true if objects represent same reservation
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reservation reservation = (Reservation) obj;
        return reservationId == reservation.reservationId;
    }

    /**
     * Returns hash code based on reservationId
     * @return Hash code of reservation
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(reservationId);
    }
}
