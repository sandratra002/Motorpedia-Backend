package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import database.SQLConnection;

public class Car {
    String id;
    String name;
    int year;
    double price;
    int seatingCapacity;
    String image;
    String brandId;
    String transmissionTypeId;
    String categoryId;
    String engineTypeId;

    // Constructors
    public Car() {}

    public Car(String id, String name, int year, double price, int seatingCapacity, String image, String brandId,
            String transmissionTypeId, String categoryId, String engineTypeId) {
        setId(id);
        setName(name);
        setYear(year);
        setPrice(price);
        setSeatingCapacity(seatingCapacity);
        setImage(image);
        setBrandId(brandId);
        setTransmissionTypeId(transmissionTypeId);
        setCategoryId(categoryId);
        setEngineTypeId(engineTypeId);
    }

    // Class methods
    private static String toQuery(HashMap<String, Object> search) throws Exception {
        String query = "SELECT * FROM Car WHERE 1 = 1 ";

        query = getQuery(search, query);

        return query;
    }

    private static String getQuery(HashMap<String, Object> search, String query) {
        if (search.get("name") != null && !"".equals(search.get("name")))
            query += " AND name LIKE \'%" + ((String) search.get("name")) + "%\' ";
        if (search.get("transmission-type") != null && !"".equals(search.get("transmission-type")))
            query += " AND transmission_type_id = \'" + ((String) search.get("transmission-type")) + "\' ";
        if (search.get("engine-type") != null && !"".equals(search.get("engine-type")))
            query += " AND engine-type_type_id = \'" + ((String) search.get("engine-type")) + "\' ";
        if (search.get("category") != null && !"".equals(search.get("category")))
            query += " AND category_id = \'" + ((String) search.get("category")) + "\' ";
        if (search.get("max-price") != null && !"".equals(search.get("max-price")) && (double) search.get("max-price") > 0)
            query += " AND price <= " + ((double) search.get("max-price")) + " ";
        if (search.get("min-price") != null && !"".equals(search.get("min-price")) && (double) search.get("min-price") >= 0)
            query += " AND price >= " + ((double) search.get("min-price")) + " ";
        if (search.get("year") != null && !"".equals(search.get("year-price")) && (int) search.get("year") > 0)
            query += " AND year = " + ((int) search.get("year")) + " ";
        if (search.get("brand") != null && !"".equals(search.get("brand")))
            query += " AND brand_id = \'" + ((String) search.get("brand")) + "\' ";
        if (search.get("seating-capacity") != null && !"".equals(search.get("seating-capacity")))
            query += " AND seating_capacity = " + ((int) search.get("seating-capacity")) + " ";
        return query;
    }

    public static ArrayList<Car> search(HashMap<String, Object> search) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ArrayList<Car> cars = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            String query = toQuery(search);
            System.out.println(query);
            statement = connection.prepareStatement(query);
            set = statement.executeQuery();
            while (set.next()) {
                Car car = new Car(set.getString("id"), set.getString("name"), set.getInt("year"),
                        set.getDouble("price"), set.getInt("seating_capacity"), set.getString("image"),
                        set.getString("brand_id"), set.getString("transmission_type_id"), set.getString("category_id"),
                        set.getString("engine_type_id"));
                cars.add(car);
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
        return cars;
    }

    public static HashMap<String, String> getCarInfo(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        HashMap<String, String> categorys = new HashMap<String, String>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM v_Car_Info WHERE id = ?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                categorys.put("brand", set.getString("brand"));
                categorys.put("transmission_type", set.getString("transmission_type"));
                categorys.put("category", set.getString("category"));
                categorys.put("engine_type", set.getString("engine_type"));
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
        return categorys;
    }

    // CRUD methods
    public static int createCar(String name, int year, double price, int seatingCapacity, String image, String brandId,
            String transmissionTypeId, String categoryId, String engineTypeId) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                    "INSERT INTO car(name, year, price, seating_capacity, image, brand_id, transmission_type_id, category_id, engine_type_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setInt(2, year);
            statement.setDouble(3, price);
            statement.setInt(4, seatingCapacity);
            statement.setString(5, image);
            statement.setString(6, brandId);
            statement.setString(7, transmissionTypeId);
            statement.setString(8, categoryId);
            statement.setString(9, engineTypeId);
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

    public static ArrayList<Car> readCar() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        ArrayList<Car> cars = new ArrayList<>();
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM car");
            set = statement.executeQuery();
            while (set.next()) {
                Car car = new Car(set.getString("id"), set.getString("name"), set.getInt("year"),
                        set.getDouble("price"), set.getInt("seating_capacity"), set.getString("image"),
                        set.getString("brand_id"), set.getString("transmission_type_id"), set.getString("category_id"),
                        set.getString("engine_type_id"));
                cars.add(car);
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
        return cars;
    }

    public static Car readCarById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Car car = null;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT * FROM car WHERE id=?");
            statement.setString(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                car = new Car(set.getString("id"), set.getString("name"), set.getInt("year"), set.getDouble("price"),
                        set.getInt("seating_capacity"), set.getString("image"), set.getString("brand_id"),
                        set.getString("transmission_type_id"), set.getString("category_id"),
                        set.getString("engine_type_id"));
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
        return car;
    }

    public static int updateCarById(String name, int year, double price, int seatingCapacity, String image,
            String brandId, String transmissionTypeId, String categoryId, String engineTypeId, String id)
            throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(
                    "UPDATE car SET name=?, year=?, price=?, seating_capacity=?, image=?, brand_id=?, transmission_type_id=?, category_id=?, engine_type_id=? WHERE id=?");
            statement.setString(1, name);
            statement.setInt(2, year);
            statement.setDouble(3, price);
            statement.setInt(4, seatingCapacity);
            statement.setString(5, image);
            statement.setString(6, brandId);
            statement.setString(7, transmissionTypeId);
            statement.setString(8, categoryId);
            statement.setString(9, engineTypeId);
            statement.setString(10, id);
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

    public static int deleteCarById(String id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = 0;
        try {
            connection = SQLConnection.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM car WHERE id=?");
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

    // Getters and setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setTransmissionTypeId(String transmissionTypeId) {
        this.transmissionTypeId = transmissionTypeId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setEngineTypeId(String engineTypeId) {
        this.engineTypeId = engineTypeId;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public double getPrice() {
        return this.price;
    }

    public int getSeatingCapacity() {
        return this.seatingCapacity;
    }

    public String getImage() {
        return this.image;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public String getTransmissionTypeId() {
        return this.transmissionTypeId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getEngineTypeId() {
        return this.engineTypeId;
    }
}
