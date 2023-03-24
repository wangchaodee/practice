package com.iflytek.staff.chao.web;

import com.iflytek.staff.chao.converter.Utils;
import com.iflytek.staff.chao.model.City;
import com.iflytek.staff.chao.service.ICityService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ManageCities", urlPatterns = {"/ManageCities"})
public class ManageCities extends HttpServlet {

    @Inject
    ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Map<String ,String>> cities = cityService.getCitiesMap();

        response.getWriter().write(JSONArray.toJSONString(cities));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        String name = request.getParameter("name");
        int population = Integer.valueOf(request.getParameter("population"));

        cityService.insertCity(name, population);

        getServletContext().log("City " + name + " inserted");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        Map<String, String> dataMap = Utils.getParameterMap(request);

        String cityName = dataMap.get("name");
        int population = Integer.valueOf(dataMap.get("population"));

        cityService.updateCity(cityName, population);

        getServletContext().log("Car " + cityName + " updated" + population);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        Map<String, String> dataMap = Utils.getParameterMap(request);

        String cityName = dataMap.get("name");

        cityService.deleteCity(cityName);

        getServletContext().log("Car:" + cityName + " deleted");
    }
}

