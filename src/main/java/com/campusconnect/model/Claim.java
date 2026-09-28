package com.campusconnect.model;

import java.sql.Timestamp;

public class Claim {

    private int id;
    private int itemId;
    private int claimantId;
    private String message;
    private String status;
    private Timestamp claimDate;

    public Claim() {
    }

    public Claim(int itemId, int claimantId, String message) {
        this.itemId = itemId;
        this.claimantId = claimantId;
        this.message = message;
        this.status = "PENDING";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(int claimantId) {
        this.claimantId = claimantId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Timestamp claimDate) {
        this.claimDate = claimDate;
    }
}