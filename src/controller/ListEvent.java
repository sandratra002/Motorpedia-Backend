package controller;

import java.io.*;
import java.sql.Date;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Event;

public class ListEvent extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("d")) {
                String id = request.getParameter("id");
                Event.deleteEventById(id);
            }
            request.setAttribute("events", Event.readEvent());
            request.getRequestDispatcher("./pages/index.jsp?page=list-event").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = "list-event";
            String brandId = request.getParameter("brand-id");
            String name = request.getParameter("name");
            String hashtag = request.getParameter("hashtag");
            Date eventDate = Date.valueOf(request.getParameter("event-date"));
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                url = url + "?mode=u";
                String id = request.getParameter("id");
                url = url + "&id=" + id;
                Event.updateEventById(brandId, name, hashtag, eventDate, id);
            } else {
                Event.createEvent(brandId, name, hashtag, eventDate);
            }
            response.sendRedirect(url);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

}
