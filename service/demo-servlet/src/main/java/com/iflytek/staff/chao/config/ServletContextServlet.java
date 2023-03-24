package com.iflytek.staff.chao.config;

import com.iflytek.staff.chao.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author : wangchaodee
 * @Description: 测试表单
 */
@WebServlet(name ="ServletContextServlet" ,urlPatterns = "/servletContext")
public class ServletContextServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType(Constants.MIME_TEXT);
        PrintWriter pwriter = res.getWriter();
        ServletContext sc = getServletContext();
        Enumeration<String> params = sc.getInitParameterNames();
        while (params.hasMoreElements()) {
            String pname = params.nextElement();
            String pvalue = sc.getInitParameter(pname);
            pwriter.printf("%s : %s  <br/>", pname, pvalue);
        }
        pwriter.close();
    }


}
