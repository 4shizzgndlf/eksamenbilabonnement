package dk.ek.eksamenbilabonnement.models;

import java.sql.Timestamp;

public class Damage {

    private int id;
    private int bookingId;
    private String description;
    private double price;
    private boolean approved;
    private Timestamp reportedAt;

    public Damage(int id, int bookingId, String description,
                  double price, boolean approved, Timestamp reportedAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.description = description;
        this.price = price;
        this.approved = approved;
        this.reportedAt = reportedAt;
    }

    // getters
    public int getId() { return id; }
    public int getBookingId() { return bookingId; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public boolean isApproved() { return approved; }
    public Timestamp getReportedAt() { return reportedAt; }
}