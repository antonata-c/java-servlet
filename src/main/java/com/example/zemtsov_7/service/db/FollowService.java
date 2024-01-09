package com.example.zemtsov_7.service.db;

import com.example.zemtsov_7.model.Follow;
import com.example.zemtsov_7.utils.DataBaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class FollowService {
    private final DataBaseService dataBaseService;

    static final String SELECT_ALL_BY_FOLLOWER = "SELECT * FROM follow " +
            "JOIN users ON follow.following_id = users.id WHERE follower_id=?";
    static final String SELECT_FOLLOW_EXISTS = "SELECT COUNT(*) AS count " +
            "FROM follow WHERE follower_id=? AND following_id=?";
    static final String INSERT = "INSERT INTO follow (follower_id, following_id) VALUES (?,?)";
    static final String UPDATE = "UPDATE follow SET follower_id=?, following_id=? WHERE id=?";
    static final String DELETE = "DELETE FROM follow WHERE follower_id=? AND following_id=?";

    public FollowService() {
        this.dataBaseService = new DataBaseService();
    }

    public List<Follow> getAllFollows(int userId) {
        List<Follow> follows = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL_BY_FOLLOWER);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Follow follow = new Follow(
                        resultSet.getInt("following_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("start_date")
                );
                follows.add(follow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return follows;
    }

    public boolean isFollowExists(int follower_id, int following_id){
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_FOLLOW_EXISTS);
            statement.setInt(1, follower_id);
            statement.setInt(2, following_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count") > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean addFollow(Follow follow) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1, follow.getFollowerId());
            statement.setInt(2, follow.getFollowingId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateFollow(Follow updatedFollow) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setInt(1, updatedFollow.getFollowerId());
            statement.setInt(2, updatedFollow.getFollowingId());
            statement.setInt(3, updatedFollow.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean deleteFollow(int followerId, int followingId) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            statement.setInt(1, followerId);
            statement.setInt(2, followingId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}


