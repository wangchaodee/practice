package com.iflytek.staff.chao.web;

import com.iflytek.staff.chao.model.City;
import com.iflytek.staff.chao.service.ICityService;

import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@WebServlet(name = "GetCities", urlPatterns = {"/GetCities"})
public class GetCities extends HttpServlet {

    @Inject
    ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/html;charset=UTF-8");

        List<City> cities = cityService.getCities();
        request.setAttribute("cities", cities);

        RequestDispatcher dispatcher = request.getRequestDispatcher("listCities.jsp");
        dispatcher.forward(request, response);
    }
}

