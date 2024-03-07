package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.UserInfo;

public class ListUserinfo extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("d")) {
                String id = request.getParameter("id");
                UserInfo.deleteUserinfoById(id);
            }
            request.setAttribute("userinfos", UserInfo.readUserinfo());
            request.getRequestDispatcher("list-userinfo.jsp").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
