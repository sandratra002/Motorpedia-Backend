package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.TransmissionType;

public class ListTransmissionType extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("d")) {
                String id = request.getParameter("id");
                TransmissionType.deleteTransmissionTypeById(id);
            }
            request.setAttribute("transmission-types", TransmissionType.readTransmissionType());
            request.getRequestDispatcher("./pages/index.jsp?page=list-transmission-type").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = "list-transmission-type";
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                url = url + "?mode=u";
                String id = request.getParameter("id");
                url = url + "&id=" + id;
                TransmissionType.updateTransmissionTypeById(name, description, id);
            } else {
                TransmissionType.createTransmissionType(name, description);
            }
            response.sendRedirect(url);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

}
