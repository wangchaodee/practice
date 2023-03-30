package com.iflytek.staff.chao.web;



import com.iflytek.staff.chao.converter.GeneratePdf;
import com.iflytek.staff.chao.model.City;
import com.iflytek.staff.chao.service.ICityService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * @author : wangchaodee
 * @Description: xxx
 */
@WebServlet(name = "PDFServlet", urlPatterns = {"/PDFServlet"})
public class PDFServlet extends HttpServlet {

    @Inject
    ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf;charset=UTF-8");

        response.addHeader("Content-Disposition", "inline; filename=" + "cities.pdf");
        ServletOutputStream out = response.getOutputStream();

        List<City> cities = cityService.getCities();

        ByteArrayOutputStream baos = GeneratePdf.getPdfFile(cities);
        baos.writeTo(out);
    }
}
