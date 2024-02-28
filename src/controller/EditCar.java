package controller;

import java.io.*;
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

    }
}
