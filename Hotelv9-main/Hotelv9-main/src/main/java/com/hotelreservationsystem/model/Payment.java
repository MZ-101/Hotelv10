package com.hotelreservationsystem.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

/**
 * Payment - Model class representing a payment transaction
 * Encapsulates payment details including method, amount, status, and refunds
 * Implements OOP principles: Encapsulation, Data Hiding, Business Logic
 */
public class Payment {

    private int paymentId;
    private int reservationId;
    private double paymentAmount;
    private String paymentMethod; // Cash, Credit Card, E-Wallet
    private String paymentTypeDetails; // Card details or e-wallet identifier
    private String paymentStatus; // Pending, Completed, Failed, Refunded
    private LocalDate paymentDate;
    private LocalTime paymentTime;
    private double refundAmount;
    private LocalDate refundDate;
    private String refundReason;
    private String transactionId; // For tracking and reconciliation
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor
     */
    public Payment() {
        this.paymentStatus = "Pending";
        this.refundAmount = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Constructor with essential payment information
     */
    public Payment(int reservationId, double paymentAmount, String paymentMethod, String paymentStatus) {
        this();
        this.reservationId = reservationId;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Constructor with all fields
     */
    public Payment(int paymentId, int reservationId, double paymentAmount, String paymentMethod,
                  String paymentTypeDetails, String paymentStatus, LocalDate paymentDate,
                  LocalTime paymentTime, double refundAmount, LocalDate refundDate,
                  String refundReason, String transactionId, LocalDateTime createdAt,
                  LocalDateTime updatedAt) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paymentTypeDetails = paymentTypeDetails;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.refundAmount = refundAmount;
        this.refundDate = refundDate;
        this.refundReason = refundReason;
        this.transactionId = transactionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ============ GETTERS AND SETTERS ============

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentTypeDetails() {
        return paymentTypeDetails;
    }

    public void setPaymentTypeDetails(String paymentTypeDetails) {
        this.paymentTypeDetails = paymentTypeDetails;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
     * Checks if payment is pending
     * @return true if status is "Pending"
     */
    public boolean isPending() {
        return "Pending".equals(this.paymentStatus);
    }

    /**
     * Checks if payment is completed
     * @return true if status is "Completed"
     */
    public boolean isCompleted() {
        return "Completed".equals(this.paymentStatus);
    }

    /**
     * Checks if payment failed
     * @return true if status is "Failed"
     */
    public boolean isFailed() {
        return "Failed".equals(this.paymentStatus);
    }

    /**
     * Checks if payment was refunded
     * @return true if status is "Refunded" and refund amount > 0
     */
    public boolean isRefunded() {
        return "Refunded".equals(this.paymentStatus) && refundAmount > 0;
    }

    /**
     * Checks if payment method is cash
     * @return true if payment method is "Cash"
     */
    public boolean isCashPayment() {
        return "Cash".equals(this.paymentMethod);
    }

    /**
     * Checks if payment method is credit card
     * @return true if payment method is "Credit Card"
     */
    public boolean isCreditCardPayment() {
        return "Credit Card".equals(this.paymentMethod);
    }

    /**
     * Checks if payment method is e-wallet
     * @return true if payment method is "E-Wallet"
     */
    public boolean isEWalletPayment() {
        return "E-Wallet".equals(this.paymentMethod);
    }

    /**
     * Calculates net payment after refund
     * @return Payment amount minus refund amount
     */
    public double getNetPayment() {
        return paymentAmount - refundAmount;
    }

    /**
     * Gets payment method with details
     * Example: "Credit Card ending in 1234" or "E-Wallet: 09123456789"
     * @return Payment method with details
     */
    public String getPaymentMethodDetails() {
        if (paymentTypeDetails != null && !paymentTypeDetails.trim().isEmpty()) {
            return paymentMethod + ": " + paymentTypeDetails;
        }
        return paymentMethod;
    }

    /**
     * Gets formatted payment amount display
     * Example: "PHP 5,000.00"
     * @return Formatted payment amount
     */
    public String getFormattedAmount() {
        return "PHP " + String.format("%,.2f", paymentAmount);
    }

    /**
     * Gets formatted refund amount display
     * @return Formatted refund amount (or "No refund" if 0)
     */
    public String getFormattedRefundAmount() {
        if (refundAmount > 0) {
            return "PHP " + String.format("%,.2f", refundAmount);
        }
        return "No refund";
    }

    /**
     * Gets payment summary
     * @return Summary string with key payment information
     */
    public String getPaymentSummary() {
        return "Payment #" + paymentId +
               " | Reservation #" + reservationId +
               " | " + getFormattedAmount() +
               " | " + paymentMethod +
               " | Status: " + paymentStatus;
    }

    /**
     * Updates the updatedAt timestamp to current time
     */
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Returns string representation of Payment object
     * @return Formatted payment information
     */
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", reservationId=" + reservationId +
                ", paymentAmount=" + paymentAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }

    /**
     * Checks if two Payment objects are equal based on paymentId
     * @param obj Object to compare
     * @return true if objects represent same payment
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Payment payment = (Payment) obj;
        return paymentId == payment.paymentId;
    }

    /**
     * Returns hash code based on paymentId
     * @return Hash code of payment
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(paymentId);
    }
}
