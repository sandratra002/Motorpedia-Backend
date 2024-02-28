package model;

import java.sql.*;
import java.util.ArrayList;

import database.SQLConnection;

public class Brand {
    String id;
    String name;
    String logo;
    String originCountry;

    // CRUD Method
    public static int createBrand(String name, String logo, String originCountry) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("INSERT INTO brand(name, logo, origin_country) VALUES(?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, logo);
            statement.setString(3, originCountry);
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

    public static ArrayList<Brand> readBrand() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ArrayList<Brand> brands = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM brand");
            set = statement.executeQuery();
            while (set.next()) {
                Brand brand = new Brand(set.getString("id"), set.getString("name"), set.getString("logo"),
                        set.getString("origin_country"));
                brands.add(brand);
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
        return brands;
    }

    public static Brand readBrandById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Brand brand = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM brand WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                brand = new Brand(set.getString("id"), set.getString("name"), set.getString("logo"),
                        set.getString("origin_country"));
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
        return brand;
    }

    public static int updateBrandById(String name, String logo, String originCountry, String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("UPDATE brand SET name=?, logo=?, origin_country=? WHERE id=?");
            statement.setString(1, name);
            statement.setString(2, logo);
            statement.setString(3, originCountry);
            statement.setString(4, id);
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

    public static int deleteBrandById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement("DELETE FROM Car WHERE brand_id = ?");
            statement.setString(1, id);
            statement.executeUpdate();

            statement = connection.prepareStatement("DELETE FROM brand\r\n" + //
                    "USING brand CASCADE\r\n" + //
                    "WHERE brand.id = ?");
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

    // Constructors, getters and setters
    public Brand() {
    }

    public Brand(String id, String name, String logo, String originCountry) {
        setId(id);
        setName(name);
        setLogo(logo);
        setOriginCountry(originCountry);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogo() {
        return this.logo;
    }

    public String getOriginCountry() {
        return this.originCountry;
    }

}
