package com.example.zemtsov_7.model;

import java.util.Date;

public class Follow {
    private int id;
    private int followerId;
    private int followingId;
    private Date startDate;
    private String followingFirstName;
    private String followingLastName;

    public Follow(int followingId, String followingFirstName, String followingLastName, Date startDate) {
        this.followingId = followingId;
        this.followingFirstName = followingFirstName;
        this.followingLastName = followingLastName;
        this.startDate = startDate;
    }

    public Follow(int followerId, int followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public String getFollowingFirstName() {
        return followingFirstName;
    }

    public void setFollowingFirstName(String followingFirstName) {
        this.followingFirstName = followingFirstName;
    }

    public String getFollowingLastName() {
        return followingLastName;
    }

    public void setFollowingLastName(String followingLastName) {
        this.followingLastName = followingLastName;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


}