package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Authentication;
import model.UserInfo;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = null;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String mode = request.getParameter("mode");
        String name = request.getParameter("name");
        String firstName = request.getParameter("first-name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("first-name", firstName);
        map.put("email", email);
        map.put("password", password);
        map.put("confirm-password", confirmPassword);
        try {
            UserInfo info = null;
            if (mode.equals("login")) {
                info = Authentication.login(email, password);
            } else {
                info = Authentication.signup(name, firstName, email, password, confirmPassword);
            }
            if (info == null) {
                throw new Exception("An error has occured, please try again");
            }
            session.setAttribute("user", info);
        } catch (Exception e) {
            error = e.getMessage();
            out.println(e);
        } finally {
            if (error == null) {
                // request.getRequestDispatcher("./list-car").forward(request, response);
                response.sendRedirect("list-car");
            } else {
                request.setAttribute("error", error);
                request.setAttribute("mode", mode);
                request.setAttribute("form-map", map);
                request.getRequestDispatcher("./pages/index.jsp?page=login").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "");
        map.put("first-name", "");
        map.put("email", "");
        map.put("password", "");
        map.put("confirm-password", "");
        request.setAttribute("form-map", map);
        request.getRequestDispatcher("./pages/index.jsp?page=login").forward(request, response);
    }

}
