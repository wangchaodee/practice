package com.iflytek.staff.chao.web;

import com.iflytek.staff.chao.converter.CitysXmlConvertor;
import com.iflytek.staff.chao.model.City;
import com.iflytek.staff.chao.service.ICityService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@WebServlet(name = "GetCitiesXml", urlPatterns = {"/GetCitiesXml"})
public class GetCitiesXml extends HttpServlet {

    @Inject
    ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/xml;charset=UTF-8");

        List<City> cities = cityService.getCities();
        CitysXmlConvertor xmlConvertor = new CitysXmlConvertor();
        try(ByteArrayOutputStream bos = xmlConvertor.convertList(cities)){
            OutputStream os = response.getOutputStream();
            bos.writeTo(os);
        }
    }
}

