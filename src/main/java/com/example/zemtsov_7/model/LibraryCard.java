package com.example.zemtsov_7.model;

import java.util.Date;

public class LibraryCard {
    private int id;
    private int userId;


    private String authority;
    private Date issueDate;
    private Date expiryDate;

    public LibraryCard(int id, int userId, String authority, Date issueDate, Date expiryDate) {
        this.id = id;
        this.userId = userId;
        this.authority = authority;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public LibraryCard(int userId, String authority){
        this.userId = userId;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
