package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.SQLConnection;

public class Authentication {
    public static Userinfo login(String email, String password) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Userinfo userinfo = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                    "SELECT * FROM userinfo WHERE email = ? AND password = encode(digest(?, 'sha256'), 'hex')");
            statement.setString(1, email);
            statement.setString(2, password);
            set = statement.executeQuery();
            if (set.next()) {
                userinfo = new Userinfo(
                        set.getString("id"),
                        set.getString("name"),
                        set.getString("first_name"),
                        set.getString("email"),
                        set.getString("password"));
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

    public static Userinfo signup(String name, String firstName, String email, String password, String confirmPassword)
            throws Exception {
        if (!password.equals(confirmPassword))
            throw new Exception("Two password must be the same");
        if (!isValidEmail(email))
            throw new Exception("Invalid email");
        if ("".equals(name) || "".equals(firstName))
            throw new Exception("Invalid name or firstName");
        String id = Userinfo.createUserinfo(name, firstName, email, password);
        return Userinfo.readUserinfoById(id);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        return email.matches(regex);
    }
}
