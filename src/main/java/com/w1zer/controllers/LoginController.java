package com.w1zer.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.w1zer.entity.Profile;
import com.w1zer.repository.ProfileRepository;
import com.w1zer.service.ProfileService;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final ProfileService profileService = new ProfileService(new ProfileRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Profile profile = profileService.login(login, password);
        if (profile == null) {
            req.setAttribute("error", "Incorrect login or password");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().setAttribute("idProfile", profile.getId());
        req.getSession().setAttribute("logged", true);
        resp.sendRedirect("/user/toads");
    }
}
