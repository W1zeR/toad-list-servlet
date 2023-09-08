package com.w1zer.controllers.user.toads;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.w1zer.repository.ToadRepository;
import com.w1zer.service.ToadService;

import java.io.IOException;

@WebServlet("/user/toads/delete")
public class DeleteController extends HttpServlet {
    private final ToadService toadService = new ToadService(new ToadRepository());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long idToad = Long.parseLong(req.getParameter("idToad"));
        Long idProfile = (Long) req.getSession().getAttribute("idProfile");
        if (toadService.canModify(idProfile, idToad)) {
            toadService.deleteToad(idToad);
        }
        resp.sendRedirect("/user/toads");
    }
}