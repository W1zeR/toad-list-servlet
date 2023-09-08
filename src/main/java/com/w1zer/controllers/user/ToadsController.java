package com.w1zer.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.w1zer.repository.ToadRepository;
import com.w1zer.service.ToadService;

import java.io.IOException;

@WebServlet("/user/toads")
public class ToadsController extends HttpServlet {
    private final ToadService toadService = new ToadService(new ToadRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");
        req.setAttribute("toadList", toadService.getToadList(idProfile));
        req.getRequestDispatcher("/WEB-INF/user/toads.jsp").forward(req, resp);
    }
}
