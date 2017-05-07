package com.alexside.dto;

/**
 * Created by Alex on 07.05.2017.
 */
public class TransferDTO {
    private int senderId;
    private int recipientId;
    private double amount;
    private String description;
    public TransferDTO() {}
    public TransferDTO(int senderId, int recipientId, double amount, String description) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.description = description;
    }
    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    public int getRecipientId() { return recipientId; }
    public void setRecipientId(int recipientId) { this.recipientId = recipientId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
