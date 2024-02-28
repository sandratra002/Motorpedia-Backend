package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Userinfo;

public class EditUserinfo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                String id = request.getParameter("id");
                request.setAttribute("userinfo", Userinfo.readUserinfoById(id));
            }
            request.getRequestDispatcher("edit-userinfo.jsp").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = "edit-userinfo";
            String name = request.getParameter("name");
            String firstName = request.getParameter("first-name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                url = url + "?mode=u";
                String id = request.getParameter("id");
                url = url + "&id=" + id;
                Userinfo.updateUserinfoById(name, firstName, email, password, id);
            } else {
                Userinfo.createUserinfo(name, firstName, email, password);
            }
            response.sendRedirect(url);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

}
