package testing;

import java.util.ArrayList;
import java.util.HashMap;

import model.Car;

public class Main {
    public static void main(String[] args) {
        try {
            HashMap<String, Object> search = new HashMap<String, Object>();
            search.put("name", "");
            search.put("min-price", "");
            search.put("max-price", 45000.0);
            search.put("year", 2024);
            search.put("seating-capacity", "");
            search.put("category", "CAT0001");
            ArrayList<Car> cars = Car.search(search);
            System.out.println(cars.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
