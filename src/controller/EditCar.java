package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import model.Car;
import model.Brand;
import model.TransmissionType;
import model.Category;
import model.EngineType;

@MultipartConfig
public class EditCar extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("brands", Brand.readBrand());
            request.setAttribute("transmissions", TransmissionType.readTransmissionType());
            request.setAttribute("categories", Category.readCategory());
            request.setAttribute("engines", EngineType.readEngineType());
            HashMap<String, String> infos = new HashMap<String, String>();
            infos.put("brand", "");
            infos.put("transmission_type", "");
            infos.put("category", "");
            infos.put("engine_type", "");
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                String id = request.getParameter("id");
                request.setAttribute("car", Car.readCarById(id));
                infos = Car.getCarInfo(id);
            }
            request.setAttribute("info", infos);
            request.getRequestDispatcher("./pages/index.jsp?page=edit-car").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            HashMap<String, Object> searchHashMap = new HashMap<String, Object>();
            searchHashMap.put("name", request.getParameter("name"));
            searchHashMap.put("year", request.getParameter("year"));
            searchHashMap.put("transmission-type", request.getParameter("transmission-type"));
            searchHashMap.put("engine-type", request.getParameter("engine-type"));
            searchHashMap.put("category", request.getParameter("category"));
            searchHashMap.put("max-price", request.getParameter("max-price"));
            searchHashMap.put("min-price", request.getParameter("min-price"));
            searchHashMap.put("brand", request.getParameter("brand"));
            searchHashMap.put("seating-capacity", request.getParameter("seating-capacity"));
            ArrayList<Car> list = Car.search(searchHashMap);
            request.setAttribute("list", list);
            request.setAttribute("search-map", searchHashMap);
            request.getRequestDispatcher("./list-car").forward(request, response);
        } catch (Exception e) {
            out.println(e);
            e.printStackTrace(out);
        }

    }
}
