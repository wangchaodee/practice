package com.iflytek.staff.chao.web;

import com.iflytek.staff.chao.converter.CitysXmlConvertor;
import com.iflytek.staff.chao.model.City;
import com.iflytek.staff.chao.service.ICityService;

import javax.inject.Inject;
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
@WebServlet(name = "GetCityXml", urlPatterns = {"/GetCityXml"})
public class GetCityXml extends HttpServlet {

    @Inject
    ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("cityId"));

        response.setContentType("application/xml;charset=UTF-8");

        City city = cityService.getCity(id);
        CitysXmlConvertor xmlConvertor = new CitysXmlConvertor();
        try(ByteArrayOutputStream bos = xmlConvertor.convertObject(city)){
            OutputStream os = response.getOutputStream();
            bos.writeTo(os);
        }
    }
}

