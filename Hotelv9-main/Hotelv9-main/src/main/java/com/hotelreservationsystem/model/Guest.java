package com.hotelreservationsystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Guest - Model class representing a guest/customer
 * Extends user information with personal details required for hotel reservations
 * Implements OOP principles: Encapsulation, Data Hiding, Inheritance potential
 */
public class Guest {

    private int guestId;
    private int userId; // Foreign key to User table
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String nationality;
    private String idDocumentType; // Passport, Driver's License, National ID, etc.
    private String idDocumentNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor
     */
    public Guest() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Constructor with essential guest information
     */
    public Guest(String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Constructor with all fields
     */
    public Guest(int guestId, int userId, String firstName, String middleName, String lastName,
                 String email, String phoneNumber, String address, LocalDate dateOfBirth,
                 String nationality, String idDocumentType, String idDocumentNumber,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.guestId = guestId;
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.idDocumentType = idDocumentType;
        this.idDocumentNumber = idDocumentNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ============ GETTERS AND SETTERS ============

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public String getIdDocumentNumber() {
        return idDocumentNumber;
    }

    public void setIdDocumentNumber(String idDocumentNumber) {
        this.idDocumentNumber = idDocumentNumber;
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
     * Gets the full name of the guest
     * Format: FirstName MiddleName LastName (if middle name exists)
     * @return Full name of guest
     */
    public String getFullName() {
        if (middleName != null && !middleName.trim().isEmpty()) {
            return firstName + " " + middleName + " " + lastName;
        }
        return firstName + " " + lastName;
    }

    /**
     * Sets full name by parsing firstName and lastName
     * @param fullName Full name of guest
     */
    public void setFullName(String fullName) {
        String[] parts = fullName.trim().split(" ");
        if (parts.length >= 2) {
            this.firstName = parts[0];
            this.lastName = parts[parts.length - 1];
            if (parts.length == 3) {
                this.middleName = parts[1];
            }
        }
    }

    /**
     * Checks if guest has provided complete identification
     * @return true if ID document type and number are provided
     */
    public boolean hasValidIdentification() {
        return idDocumentType != null && !idDocumentType.trim().isEmpty()
                && idDocumentNumber != null && !idDocumentNumber.trim().isEmpty();
    }

    /**
     * Gets guest's identification details in formatted string
     * @return Formatted ID information
     */
    public String getIdentificationDetails() {
        if (hasValidIdentification()) {
            return idDocumentType + ": " + idDocumentNumber;
        }
        return "No ID on file";
    }

    /**
     * Checks if guest profile is complete
     * @return true if all required fields are filled
     */
    public boolean isProfileComplete() {
        return firstName != null && !firstName.trim().isEmpty()
                && lastName != null && !lastName.trim().isEmpty()
                && email != null && !email.trim().isEmpty()
                && phoneNumber != null && !phoneNumber.trim().isEmpty()
                && address != null && !address.trim().isEmpty()
                && hasValidIdentification();
    }

    /**
     * Gets guest's contact information
     * @return Formatted contact details
     */
    public String getContactInfo() {
        return "Email: " + email + ", Phone: " + phoneNumber;
    }

    /**
     * Updates the updatedAt timestamp to current time
     */
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Returns string representation of Guest object
     * @return Formatted guest information
     */
    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", userId=" + userId +
                ", fullName='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", identification='" + getIdentificationDetails() + '\'' +
                '}';
    }

    /**
     * Checks if two Guest objects are equal based on guestId
     * @param obj Object to compare
     * @return true if objects represent same guest
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Guest guest = (Guest) obj;
        return guestId == guest.guestId;
    }

    /**
     * Returns hash code based on guestId
     * @return Hash code of guest
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(guestId);
    }
}
