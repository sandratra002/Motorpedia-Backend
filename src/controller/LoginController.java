package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Authentication;
import model.Userinfo;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = null;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        try {
            String mode = request.getParameter("mode");
            Userinfo info = null;
            String name = request.getParameter("name");
            String firstName = request.getParameter("first-name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm-password");
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
                request.setAttribute("error", error);
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                response.sendRedirect(page);
            }
        }
    }

}
