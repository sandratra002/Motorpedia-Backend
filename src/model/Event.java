package model;

import java.sql.*;
import java.util.ArrayList;

import database.SQLConnection;

public class Event {
    String id;
    String brandId;
    String name;
    String hashtag;
    Date eventDate;
    String description;
    
    public static int createEvent(String brandId, String name, String hashtag, Date eventDate, String description) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection
                    .prepareStatement("INSERT INTO event(brand_id, name, hashtag, event_date, description) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, brandId);
            statement.setString(2, name);
            statement.setString(3, hashtag);
            statement.setDate(4, eventDate);
            statement.setString(5, description);
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
    
    public static ArrayList<Event> readEvent() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ArrayList<Event> events = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM event");
            set = statement.executeQuery();
            while (set.next()) {
                Event event = new Event(set.getString("id"), set.getString("brand_id"), set.getString("name"),
                        set.getString("hashtag"), set.getDate("event_date"), set.getString("description"));
                events.add(event);
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
        return events;
    }
    
    public static Event readEventById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Event event = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM event WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                event = new Event(set.getString("id"), set.getString("brand_id"), set.getString("name"),
                        set.getString("hashtag"), set.getDate("event_date"), set.getString("description"));
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
        return event;
    }
    
    public static int updateEventById(String brandId, String name, String hashtag, Date eventDate, String description,String id)
            throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection
                    .prepareStatement("UPDATE event SET brand_id=?, name=?, hashtag=?, event_date=?, description = ? WHERE id=?");
            statement.setString(1, brandId);
            statement.setString(2, name);
            statement.setString(3, hashtag);
            statement.setDate(4, eventDate);
            statement.setString(5, description);
            statement.setString(6, id);
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
    
    public static int deleteEventById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM event WHERE id=?");
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


    //Constructors
    public Event() {
    }

    public Event(String id, String brandId, String name, String hashtag, Date eventDate, String description) {
        setId(id);
        setBrandId(brandId);
        setName(name);
        setHashtag(hashtag);
        setEventDate(eventDate);
        setDescription(description);
    }

    //Getters and setters
    public void setId(String id) {
        this.id = id;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getBrandId() {
        return this.brandId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getHashtag() {
        return this.hashtag;
    }
    
    public Date getEventDate() {
        return this.eventDate;
    }

}
