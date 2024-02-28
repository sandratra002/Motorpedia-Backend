package model;

import java.sql.*;
import java.util.Vector;

public class Review
{
    String id;
    String carId;
    String userId;
    String review;
    int rating;
    
    public Review()
    {
    }
    
    public Review(String id, String carId, String userId, String review, int rating)
    {
        this.id = id;
        this.carId = carId;
        this.userId = userId;
        this.review = review;
        this.rating = rating;
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public String getCarId()
    {
        return this.carId;
    }
    
    public String getUserId()
    {
        return this.userId;
    }
    
    public String getReview()
    {
        return this.review;
    }
    
    public int getRating()
    {
        return this.rating;
    }
    
    public static int createReview(String carId, String userId, String review, int rating) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName("org.postgresql.Driver");
        int result = 0;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO review(car_id, user_id, review, rating) VALUES(?, ?, ?, ?)");
            statement.setString(1, carId);
            statement.setString(2, userId);
            statement.setString(3, review);
            statement.setInt(4, rating);
            result = statement.executeUpdate();
            connection.commit();
        }
        catch(Exception err)
        {
            if(connection != null)
            {
                connection.rollback();
                connection.close();
            }
            throw err;
        }
        finally
        {
            statement.close();
            connection.close();
        }
        return result;
    }
    
    public static Vector<Review> readReview() throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Class.forName("org.postgresql.Driver");
        Vector<Review> reviews = new Vector<>();
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM review");
            set = statement.executeQuery();
            while(set.next())
            {
                Review review = new Review(set.getString("id"), set.getString("car_id"), set.getString("user_id"), set.getString("review"), set.getInt("rating"));
                reviews.add(review);
            }
        }
        catch(Exception err)
        {
            if(connection != null)
            {
                connection.close();
            }
            throw err;
        }
        finally
        {
            set.close();
            statement.close();
            connection.close();
        }
        return reviews;
    }
    
    public static Review readReviewById(String id) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Class.forName("org.postgresql.Driver");
        Review review = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM review WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while(set.next())
            {
                review = new Review(set.getString("id"), set.getString("car_id"), set.getString("user_id"), set.getString("review"), set.getInt("rating"));
            }
        }
        catch(Exception err)
        {
            if(connection != null)
            {
                connection.close();
            }
            throw err;
        }
        finally
        {
            set.close();
            statement.close();
            connection.close();
        }
        return review;
    }
    
    public static int updateReviewById(String carId, String userId, String review, int rating, String id) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName("org.postgresql.Driver");
        int result = 0;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("UPDATE review SET car_id=?, user_id=?, review=?, rating=? WHERE id=?");
            statement.setString(1, carId);
            statement.setString(2, userId);
            statement.setString(3, review);
            statement.setInt(4, rating);
            statement.setString(5, id);
            result = statement.executeUpdate();
            connection.commit();
        }
        catch(Exception err)
        {
            if(connection != null)
            {
                connection.rollback();
                connection.close();
            }
            throw err;
        }
        finally
        {
            statement.close();
            connection.close();
        }
        return result;
    }
    
    public static int deleteReviewById(String id) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName("org.postgresql.Driver");
        int result = 0;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM review WHERE id=?");
            statement.setString(1, id);
            result = statement.executeUpdate();
            connection.commit();
        }
        catch(Exception err)
        {
            if(connection != null)
            {
                connection.rollback();
                connection.close();
            }
            throw err;
        }
        finally
        {
            statement.close();
            connection.close();
        }
        return result;
    }
    
}
