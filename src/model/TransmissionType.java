package model;

import java.sql.*;
import java.util.ArrayList;

import database.SQLConnection;

public class TransmissionType {
    String id;
    String name;
    String description;

    // Constructors
    public TransmissionType() {
    }

    public TransmissionType(String id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    // CRUD Methods
    public static int createTransmissionType(String name, String description) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO transmission_type(name, description) VALUES(?, ?)");
            statement.setString(1, name);
            statement.setString(2, description);
            result = statement.executeUpdate();
            connection.commit();
        } catch (Exception err) {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw err;
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        return result;
    }

    public static ArrayList<TransmissionType> readTransmissionType() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ArrayList<TransmissionType> transmissionTypes = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM transmission_type");
            set = statement.executeQuery();
            while (set.next()) {
                TransmissionType transmissionType = new TransmissionType(set.getString("id"), set.getString("name"),
                        set.getString("description"));
                transmissionTypes.add(transmissionType);
            }
        } catch (Exception err) {
            if (connection != null) {
                connection.close();
            }
            throw err;
        } finally {
            if (set != null)
                set.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        return transmissionTypes;
    }

    public static TransmissionType readTransmissionTypeById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        TransmissionType transmissionType = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM transmission_type WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                transmissionType = new TransmissionType(set.getString("id"), set.getString("name"),
                        set.getString("description"));
            }
        } catch (Exception err) {
            if (connection != null) {
                connection.close();
            }
            throw err;
        } finally {
            if (set != null)
                set.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        return transmissionType;
    }

    public static int updateTransmissionTypeById(String name, String description, String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("UPDATE transmission_type SET name=?, description=? WHERE id=?");
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setString(3, id);
            result = statement.executeUpdate();
            connection.commit();
        } catch (Exception err) {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw err;
        } finally {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        return result;
    }

    public static int deleteTransmissionTypeById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement("DELETE FROM Car WHERE transmission_type_id = ?");
            statement.setString(1, id);
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM transmission_type WHERE id=?");
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
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        return result;
    }

    // Getters and setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
