package com.iflytek.staff.chao.config;

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
 * @Description: Headers参数
 */
public class HeadersServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType(Constants.MIME_TEXT);
        PrintWriter pwriter = res.getWriter();
        Enumeration<String> params = req.getHeaderNames();
        while (params.hasMoreElements()) {
            String pname = params.nextElement();
            String pvalue = req.getHeader(pname);
            pwriter.printf("%s : %s  <br/>", pname, pvalue);
        }
        pwriter.close();
    }


}
