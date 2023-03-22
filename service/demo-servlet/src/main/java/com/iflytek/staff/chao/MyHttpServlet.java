package com.iflytek.staff.chao;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyHttpServlet extends HttpServlet {

    private String mymsg;

    public void init() throws ServletException {
        mymsg = "Http Servlet Demo";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {
        // Setting up the content type of web page
        response.setContentType(Constants.MIME_TEXT);
        // Writing the message on the web page
        PrintWriter out = response.getWriter();
        out.println("<h1>" + mymsg + "</h1>");
        out.println("<p>" + "Hello Friends!" + "</p>");
    }

    public void destroy() {
        // Leaving empty. Use this if you want to perform
        //something at the end of Servlet life cycle.
    }
}
