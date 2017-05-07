package com.alexside.dto;

/**
 * Created by Alex on 07.05.2017.
 */
public class OperationDTO {
    private long id;
    private String type;
    private AccountDTO sender;
    private AccountDTO recipient;
    private long date;
    private double amount;
    private String description;
    public OperationDTO() {}
    public OperationDTO(long id, String type, AccountDTO sender, AccountDTO recipient, long date, double amount, String description) {
        this.id = id;
        this.type = type;
        this.sender = sender;
        this.recipient = recipient;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public AccountDTO getSender() { return sender; }
    public void setSender(AccountDTO sender) { this.sender = sender; }
    public AccountDTO getRecipient() { return recipient; }
    public void setRecipient(AccountDTO recipient) { this.recipient = recipient; }
    public long getDate() { return date; }
    public void setDate(long date) { this.date = date; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
