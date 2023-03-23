package com.iflytek.staff.chao.web;


import com.iflytek.staff.chao.model.Country;
import com.iflytek.staff.chao.service.CountryService;

import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "ReadCountries", urlPatterns = {"/ReadCountries"})
public class ReadCountries extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        CountryService countryService = new CountryService();

        List<Country> countries = countryService.findCountries(currentPage,
                recordsPerPage);

        request.setAttribute("countries", countries);

        int rows = countryService.getNumberOfRows();

        int numOfPages = rows / recordsPerPage;

        if (numOfPages % recordsPerPage > 0) {
            numOfPages++;
        }

        request.setAttribute("noOfPages", numOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("listCountries.jsp");
        dispatcher.forward(request, response);
    }
}
