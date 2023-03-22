package com.iflytek.staff.chao.function;

import com.iflytek.staff.chao.Constants;

import javax.servlet.ServletException;
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
public class FormServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType(Constants.MIME_TEXT);
        PrintWriter pwriter = res.getWriter();

        String name = req.getParameter("uname");
        String age = req.getParameter("uage");
        pwriter.println("Name: " + name);
        pwriter.println("Age: " + age);
        pwriter.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType(Constants.MIME_TEXT);
        PrintWriter pwriter = res.getWriter();
        Enumeration params = req.getParameterNames();
        while (params.hasMoreElements()) {
            Object obj = params.nextElement();

            String pname = (String) obj;
            String pvalue = req.getParameter(pname);
            pwriter.println("Name: " + pname);
            pwriter.println("Value: " + pvalue);
            pwriter.print("<br/>");
        }
        pwriter.close();
    }


}
