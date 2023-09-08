package com.w1zer.controllers.user.toads;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.w1zer.entity.Toad;
import com.w1zer.repository.ToadRepository;
import com.w1zer.service.ToadService;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet("/user/toads/new")
public class InsertController extends HttpServlet {
    private final ToadService toadService = new ToadService(new ToadRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/user/toads/new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");

        String name = req.getParameter("name");
        String type = req.getParameter("type");

        String weightStr = req.getParameter("weight");
        Long weight = weightStr.isEmpty() ? null : Long.valueOf(req.getParameter("weight"));

        String lengthStr = req.getParameter("length");
        BigDecimal length = lengthStr.isEmpty() ? null : new BigDecimal(req.getParameter("length"));

        String birthdayStr = req.getParameter("birthday");
        Date birthday = birthdayStr.isEmpty() ? null : Date.valueOf(req.getParameter("birthday"));

        String description = req.getParameter("description");

        Toad toad = new Toad(null, name, type, weight, length, birthday, description, idProfile);
        toadService.addToad(toad);
        resp.sendRedirect("/user/toads");
    }
}