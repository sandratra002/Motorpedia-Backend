package model;

import java.sql.*;
import java.util.ArrayList;

import database.SQLConnection;

public class Userinfo {
    String id;
    String name;
    String firstName;
    String email;
    String password;

    // Constructors
    public Userinfo() {
    }

    public Userinfo(String id, String name, String firstName, String email, String password) {
        setId(id);
        setName(name);
        setFirstName(firstName);
        setEmail(email);
        setPassword(password);
    }

    // CRUD Methods
    public static String createUserinfo(String name, String firstName, String email, String password) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String result = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection
                    .prepareStatement(
                            "INSERT INTO userinfo(name, first_name, email, password) VALUES(?, ?, ?, encode(digest(?, 'sha256'), 'hex')) RETURNING id");
            statement.setString(1, name);
            statement.setString(2, firstName);
            statement.setString(3, email);
            statement.setString(4, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("id");
            }
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

    public static ArrayList<Userinfo> readUserinfo() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ArrayList<Userinfo> userinfos = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM userinfo");
            set = statement.executeQuery();
            while (set.next()) {
                Userinfo userinfo = new Userinfo(set.getString("id"), set.getString("name"),
                        set.getString("first_name"), set.getString("email"), set.getString("password"));
                userinfos.add(userinfo);
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
        return userinfos;
    }

    public static Userinfo readUserinfoById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Userinfo userinfo = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM userinfo WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                userinfo = new Userinfo(set.getString("id"), set.getString("name"), set.getString("first_name"),
                        set.getString("email"), set.getString("password"));
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
        return userinfo;
    }

    public static int updateUserinfoById(String name, String firstName, String email, String password, String id)
            throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection
                    .prepareStatement("UPDATE userinfo SET name=?, first_name=?, email=?, password=? WHERE id=?");
            statement.setString(1, name);
            statement.setString(2, firstName);
            statement.setString(3, email);
            statement.setString(4, password);
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
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        return result;
    }

    public static int deleteUserinfoById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM userinfo WHERE id=?");
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
