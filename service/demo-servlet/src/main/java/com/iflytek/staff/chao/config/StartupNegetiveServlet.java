package com.iflytek.staff.chao.config;

import com.iflytek.staff.chao.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class StartupNegetiveServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println(System.currentTimeMillis());
        System.out.println(this.getClass().getSimpleName());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType(Constants.MIME_TEXT);
        PrintWriter out = response.getWriter();
        out.println("<p>" + "Hello Friends!" + "</p>");
    }


}
