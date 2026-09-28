package com.campusconnect.model;

import java.sql.Date;

public class Item {

    private int id;
    private int userId;
    private String itemName;
    private String description;
    private String category;
    private String location;
    private Date itemDate;
    private String type;
    private String status;

    public Item() {
    }

    public Item(int userId, String itemName, String description,
                String category, String location, Date itemDate,
                String type, String status) {

        this.userId = userId;
        this.itemName = itemName;
        this.description = description;
        this.category = category;
        this.location = location;
        this.itemDate = itemDate;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}