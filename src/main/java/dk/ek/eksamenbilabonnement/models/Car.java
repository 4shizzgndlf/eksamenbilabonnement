package dk.ek.eksamenbilabonnement.models;

import java.sql.Timestamp;

public class Car {

    private int id;
    private String imageUrl;
    private String vehicleNumber;
    private String vin;

    private String brand;
    private String model;
    private int equipmentLevel;

    private double steelPrice;
    private double registrationFee;
    private int co2Emissions;

    private String status;

    private double subscriptionPrice;
    private double purchasePrice;

    private Timestamp createdAt;

    // Constructor (used in repository)
    public Car(int id, String imageUrl, String vehicleNumber, String vin,
               String brand, String model, int equipmentLevel,
               double steelPrice, double registrationFee, int co2Emissions,
               String status, double subscriptionPrice, double purchasePrice) {

        this.id = id;
        this.imageUrl = imageUrl;
        this.vehicleNumber = vehicleNumber;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.equipmentLevel = equipmentLevel;
        this.steelPrice = steelPrice;
        this.registrationFee = registrationFee;
        this.co2Emissions = co2Emissions;
        this.status = status;
        this.subscriptionPrice = subscriptionPrice;
        this.purchasePrice = purchasePrice;
    }

    // Empty constructor (optional, useful for forms)
    public Car() {}

    // Getters & Setters

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVin() {
        return vin;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getEquipmentLevel() {
        return equipmentLevel;
    }

    public double getSteelPrice() {
        return steelPrice;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public int getCo2Emissions() {
        return co2Emissions;
    }

    public String getStatus() {
        return status;
    }

    public double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEquipmentLevel(int equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public void setSteelPrice(double steelPrice) {
        this.steelPrice = steelPrice;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public void setCo2Emissions(int co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubscriptionPrice(double subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}