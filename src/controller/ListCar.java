package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import conf.ConfigInfo;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import model.Brand;
import model.Car;
import model.Category;
import model.EngineType;
import model.TransmissionType;
import util.StringParser;

@MultipartConfig
public class ListCar extends HttpServlet {

    private static HashMap<String , Object> getHashMap() throws Exception{
        HashMap<String, Object> searchHashMap = new HashMap<String, Object>();
        searchHashMap.put("name", "");
        searchHashMap.put("year", "");
        searchHashMap.put("transmission-type", "");
        searchHashMap.put("", "");
        searchHashMap.put("engine-type", "");
        searchHashMap.put("category", "");
        searchHashMap.put("max-price", "");
        searchHashMap.put("min-price", "");
        searchHashMap.put("brand", "");
        searchHashMap.put("seating-capacity", "");
        return searchHashMap;
    }

    private static HashMap<String, Object> search(HttpServletRequest request)  throws Exception{
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
        ArrayList<Car> cars= Car.search(searchHashMap);

        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("cars", cars);
        result.put("search-map", searchHashMap);
        return result;
    }

    @SuppressWarnings("unchecked")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "./pages/index.jsp?page=list-car";
        try {
            ArrayList<Car> cars = Car.readCar();
            HashMap <String, Object> searHashMap = getHashMap();
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("d")) {
                String id = request.getParameter("id");
                Car.deleteCarById(id);
                url += "&scroll=1#main-content";
            }else if(request.getParameter("mode") != null && request.getParameter("mode").equals("s")){
                HashMap<String, Object> result = search(request);
                cars = (ArrayList<Car>)result.get("cars");
                searHashMap = (HashMap<String, Object>)result.get("search-map");
            }
            HashMap<String, HashMap<String, String>> infos = new HashMap<String, HashMap<String, String>>();
            for (Car car : cars) {
                infos.put(car.getId(), Car.getCarInfo(car.getId()));
            }
            request.setAttribute("infos", infos);
            request.setAttribute("cars", cars);
            request.setAttribute("engine-type", EngineType.readEngineType());
            request.setAttribute("transmission-type", TransmissionType.readTransmissionType());
            request.setAttribute("category", Category.readCategory());
            request.setAttribute("brand", Brand.readBrand());
            request.setAttribute("search-map", searHashMap);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "./list-car";
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        int year = Integer.parseInt(request.getParameter("year"));
        double price = Double.parseDouble(request.getParameter("price"));
        int seatingCapacity = Integer.parseInt(request.getParameter("seating-capacity"));
        String brandId = request.getParameter("brand-id");
        String transmissionTypeId = request.getParameter("transmission-type-id");
        String categoryId = request.getParameter("category-id");
        String engineTypeId = request.getParameter("engine-type-id");
        Part part = request.getPart("image");

        String extension = StringParser.getExtension(part.getSubmittedFileName());
        String imageName = name.replaceAll(" ", "-").toLowerCase() + "." + extension;

        Brand brand = Brand.readBrandById(brandId);

        HashMap<String, String> infos = ConfigInfo.getServerInfo();

        String filePath = infos.get("image-path") + "\\" + brand.getName() + "\\" + imageName;
        part.write(filePath);

        if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
            url = url + "?mode=u";
            String id = request.getParameter("id");
            url = url + "&id=" + id;
            if (request.getParameter("image-name") != null) {
                imageName = request.getParameter("image-name");
            }
            int h = Car.updateCarById(name, year, price, seatingCapacity, imageName, brandId, transmissionTypeId,
                    categoryId,
                    engineTypeId, id);
            out.println(h);
            out.println(seatingCapacity);
            out.println("Update car....");
        } else {
            Car.createCar(name, year, price, seatingCapacity, imageName, brandId, transmissionTypeId, categoryId,
                    engineTypeId);
        }
        response.sendRedirect(url);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            handleRequest(request, response);
        } catch (Exception err) {
            out.print(err);
            err.printStackTrace(response.getWriter());
        }
    }

}
