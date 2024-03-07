package model;

import java.sql.*;
import java.util.ArrayList;

import database.SQLConnection;

public class Review {
    String id;
    String carId;
    String userId;
    String review;
    int rating;

    // CRUD Method
    public static int createReview(String carId, String userId, String review, int rating) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection
                    .prepareStatement("INSERT INTO review(car_id, user_id, review, rating) VALUES(?, ?, ?, ?)");
            statement.setString(1, carId);
            statement.setString(2, userId);
            statement.setString(3, review);
            statement.setInt(4, rating);
            result = statement.executeUpdate();
            connection.commit();
        } catch (Exception err) {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw err;
        } finally {
            statement.close();
            connection.close();
        }
        return result;
    }

    public static ArrayList<Review> readReview() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ArrayList<Review> reviews = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM review");
            set = statement.executeQuery();
            while (set.next()) {
                Review review = new Review(set.getString("id"), set.getString("car_id"), set.getString("user_id"),
                        set.getString("review"), set.getInt("rating"));
                reviews.add(review);
            }
        } catch (Exception err) {
            if (connection != null) {
                connection.close();
            }
            throw err;
        } finally {
            set.close();
            statement.close();
            connection.close();
        }
        return reviews;
    }

    public static Review readReviewById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Review review = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM review WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                review = new Review(set.getString("id"), set.getString("car_id"), set.getString("user_id"),
                        set.getString("review"), set.getInt("rating"));
            }
        } catch (Exception err) {
            if (connection != null) {
                connection.close();
            }
            throw err;
        } finally {
            set.close();
            statement.close();
            connection.close();
        }
        return review;
    }

    public static int updateReviewById(String carId, String userId, String review, int rating, String id)
            throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection
                    .prepareStatement("UPDATE review SET car_id=?, user_id=?, review=?, rating=? WHERE id=?");
            statement.setString(1, carId);
            statement.setString(2, userId);
            statement.setString(3, review);
            statement.setInt(4, rating);
            statement.setString(5, id);
            result = statement.executeUpdate();
            connection.commit();
        } catch (Exception err) {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw err;
        } finally {
            statement.close();
            connection.close();
        }
        return result;
    }

    public static int deleteReviewById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM review WHERE id=?");
            statement.setString(1, id);
            result = statement.executeUpdate();
            connection.commit();
        } catch (Exception err) {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw err;
        } finally {
            statement.close();
            connection.close();
        }
        return result;
    }

    // Constructors
    public Review() {
    }

    public Review(String id, String carId, String userId, String review, int rating) throws Exception {
        setId(id);
        setCarId(carId);
        setUserId(userId);
        setReview(review);
        setRating(rating);
    }

    // Getters and setters
    public void setId(String id) {
        this.id = id;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRating(int rating) throws Exception {
        if (rating <= 0 || rating > 5)
            throw new Exception("Invalid rating");
        this.rating = rating;
    }

    public String getId() {
        return this.id;
    }

    public String getCarId() {
        return this.carId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getReview() {
        return this.review;
    }

    public int getRating() {
        return this.rating;
    }

}
