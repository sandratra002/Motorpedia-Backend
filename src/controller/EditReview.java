package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Review;
import model.Car;
import model.Userinfo;

public class EditReview extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("cars", Car.readCar());
            // request.setAttribute("options-2", Userinfo.readUserinfo());
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                String id = request.getParameter("id");
                request.setAttribute("review", Review.readReviewById(id));
            }
            request.getRequestDispatcher("./pages/index.jsp?page=edit-review").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

}
