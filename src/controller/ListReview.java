package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.Review;

public class ListReview extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("d")) {
                String id = request.getParameter("id");
                Review.deleteReviewById(id);
            }
            request.setAttribute("reviews", Review.readReview());
            request.getRequestDispatcher("./pages/index.jsp?page=list-review").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = "list-review";
            String carId = request.getParameter("car-id");
            String userId = request.getParameter("user-id");
            String review = request.getParameter("review");
            int rating = Integer.parseInt(request.getParameter("rating"));
            if (request.getParameter("mode") != null && request.getParameter("mode").equals("u")) {
                url = url + "?mode=u";
                String id = request.getParameter("id");
                url = url + "&id=" + id;
                Review.updateReviewById(carId, userId, review, rating, id);
            } else {
                Review.createReview(carId, userId, review, rating);
            }
            response.sendRedirect(url);
        } catch (Exception err) {
            err.printStackTrace(response.getWriter());
        }
    }

}
