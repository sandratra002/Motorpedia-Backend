package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Review;
import model.UserInfo;
import model.Car;

public class EditReview extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        try {
            request.setAttribute("cars", Car.readCar());
            request.setAttribute("users", UserInfo.readUserinfo());
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                String id = request.getParameter("id");
                request.setAttribute("review", Review.readReviewById(id));
                writer.println(id);
                writer.print(Review.readReviewById(id));
            }
            request.getRequestDispatcher("./pages/index.jsp?page=edit-review").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

}
