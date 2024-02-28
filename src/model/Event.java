package model;

import java.sql.Date;
import java.sql.*;
import java.util.Vector;

public class Event
{
    String id;
    String brandId;
    String name;
    String hashtag;
    Date eventDate;
    
    public Event()
    {
    }
    
    public Event(String id, String brandId, String name, String hashtag, Date eventDate)
    {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.hashtag = hashtag;
        this.eventDate = eventDate;
    }
    
    public String getId()
    {
        return this.id;
    }
    
    public String getBrandId()
    {
        return this.brandId;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getHashtag()
    {
        return this.hashtag;
    }
    
    public Date getEventDate()
    {
        return this.eventDate;
    }
    
    public static int createEvent(String brandId, String name, String hashtag, Date eventDate) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName("org.postgresql.Driver");
        int result = 0;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO event(brand_id, name, hashtag, event_date) VALUES(?, ?, ?, ?)");
            statement.setString(1, brandId);
            statement.setString(2, name);
            statement.setString(3, hashtag);
            statement.setDate(4, eventDate);
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
    
    public static Vector<Event> readEvent() throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Class.forName("org.postgresql.Driver");
        Vector<Event> events = new Vector<>();
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM event");
            set = statement.executeQuery();
            while(set.next())
            {
                Event event = new Event(set.getString("id"), set.getString("brand_id"), set.getString("name"), set.getString("hashtag"), set.getDate("event_date"));
                events.add(event);
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
        return events;
    }
    
    public static Event readEventById(String id) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Class.forName("org.postgresql.Driver");
        Event event = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM event WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while(set.next())
            {
                event = new Event(set.getString("id"), set.getString("brand_id"), set.getString("name"), set.getString("hashtag"), set.getDate("event_date"));
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
        return event;
    }
    
    public static int updateEventById(String brandId, String name, String hashtag, Date eventDate, String id) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName("org.postgresql.Driver");
        int result = 0;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("UPDATE event SET brand_id=?, name=?, hashtag=?, event_date=? WHERE id=?");
            statement.setString(1, brandId);
            statement.setString(2, name);
            statement.setString(3, hashtag);
            statement.setDate(4, eventDate);
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
    
    public static int deleteEventById(String id) throws Exception
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Class.forName("org.postgresql.Driver");
        int result = 0;
        try
        {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/restaurant", "postgres", "43710");
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM event WHERE id=?");
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
