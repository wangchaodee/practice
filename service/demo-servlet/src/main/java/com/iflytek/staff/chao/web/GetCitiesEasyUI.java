package com.iflytek.staff.chao.web;

import com.iflytek.staff.chao.converter.Utils;
import com.iflytek.staff.chao.service.ICityService;
import org.json.simple.JSONArray;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@WebServlet(name = "GetCitiesEasyUI", urlPatterns = {"/GetCitiesEasyUI"})
public class GetCitiesEasyUI extends HttpServlet {

    @Inject
    ICityService cityService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Map<String ,String>> cities = cityService.getCitiesMap();

        response.getWriter().write(JSONArray.toJSONString(cities));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }
}

