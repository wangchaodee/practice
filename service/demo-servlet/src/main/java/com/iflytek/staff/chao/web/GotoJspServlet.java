package com.iflytek.staff.chao.web;


import com.iflytek.staff.chao.model.Country;
import com.iflytek.staff.chao.service.CountryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
@WebServlet(name = "GotoJspServlet", urlPatterns = {"/gotojsp"})
public class GotoJspServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("name", "wangchao");
        request.setAttribute("number", 30);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
