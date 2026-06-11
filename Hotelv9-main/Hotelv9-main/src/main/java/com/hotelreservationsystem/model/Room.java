package com.hotelreservationsystem.model;

import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Room - Model class representing a hotel room
 * Encapsulates room information including type, capacity, pricing, and status
 * Implements OOP principles: Encapsulation, Data Hiding, Business Logic
 */
public class Room {

    private int roomId;
    private String roomNumber;
    private String roomType; // Single Standard, Double Standard, Double Deluxe, Suite Deluxe
    private int floor;
    private int capacity; // Number of guests (1-10)
    private double pricePerNight;
    private String amenities; // JSON or comma-separated list
    private String roomImage; // Path to room image
    private String status; // Available, Occupied, Maintenance, Cleaning
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Default constructor
     */
    public Room() {
        this.status = "Available";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Constructor with essential room information
     */
    public Room(String roomNumber, String roomType, int floor, int capacity, double pricePerNight) {
        this();
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.floor = floor;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Constructor with all fields
     */
    public Room(int roomId, String roomNumber, String roomType, int floor, int capacity,
                double pricePerNight, String amenities, String roomImage, String status,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.floor = floor;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.amenities = amenities;
        this.roomImage = roomImage;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // ============ GETTERS AND SETTERS ============

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
     * Checks if room is currently available for booking
     * @return true if room status is "Available"
     */
    public boolean isAvailable() {
        return "Available".equals(this.status);
    }

    /**
     * Checks if room is occupied
     * @return true if room status is "Occupied"
     */
    public boolean isOccupied() {
        return "Occupied".equals(this.status);
    }

    /**
     * Checks if room is under maintenance
     * @return true if room status is "Maintenance"
     */
    public boolean isUnderMaintenance() {
        return "Maintenance".equals(this.status);
    }

    /**
     * Checks if room is being cleaned
     * @return true if room status is "Cleaning"
     */
    public boolean isCleaning() {
        return "Cleaning".equals(this.status);
    }

    /**
     * Checks if room can accommodate specified number of guests
     * @param numberOfGuests Number of guests
     * @return true if guests do not exceed room capacity
     */
    public boolean canAccommodate(int numberOfGuests) {
        return numberOfGuests >= 1 && numberOfGuests <= this.capacity;
    }

    /**
     * Gets room description combining type and capacity
     * Example: "Double Deluxe (Capacity: 2)"
     * @return Room description
     */
    public String getDescription() {
        return roomType + " (Capacity: " + capacity + ")";
    }

    /**
     * Gets formatted price display
     * Example: "PHP 5,000.00"
     * @return Formatted price per night
     */
    public String getFormattedPrice() {
        return "PHP " + String.format("%,.2f", pricePerNight);
    }

    /**
     * Calculates total cost for specified number of nights
     * @param nights Number of nights to stay
     * @return Total cost before tax and discount
     */
    public double calculateCost(long nights) {
        if (nights < 1) {
            return 0;
        }
        return pricePerNight * nights;
    }

    /**
     * Gets room identifier for display (Room Number + Type)
     * Example: "Room 201 - Double Deluxe"
     * @return Full room identifier
     */
    public String getIdentifier() {
        return "Room " + roomNumber + " - " + roomType;
    }

    /**
     * Updates the updatedAt timestamp to current time
     */
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Returns string representation of Room object
     * @return Formatted room information
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", floor=" + floor +
                ", capacity=" + capacity +
                ", pricePerNight=" + pricePerNight +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * Checks if two Room objects are equal based on roomId
     * @param obj Object to compare
     * @return true if objects represent same room
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        return roomId == room.roomId;
    }

    /**
     * Returns hash code based on roomId
     * @return Hash code of room
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(roomId);
    }
}
