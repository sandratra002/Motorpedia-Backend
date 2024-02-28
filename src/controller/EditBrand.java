package controller;

import java.io.*;
import java.util.ArrayList;
import conf.ConfigInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import model.Brand;

@MultipartConfig
public class EditBrand extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = "webapps\\Motorpedia\\conf\\countries.xml";
            ArrayList<String> countries = ConfigInfo.getInfo(path);
            request.setAttribute("countries", countries);
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                String id = request.getParameter("id");
                request.setAttribute("brand", Brand.readBrandById(id));
            }
            request.getRequestDispatcher("./pages/index.jsp?page=edit-brand").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
