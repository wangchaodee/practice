package com.iflytek.staff.chao;


import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : wangchaodee
 * @Description: MyGenericServlet
 */
public class MyGenericServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType(Constants.MIME_TEXT);

        PrintWriter pwriter = res.getWriter();
        pwriter.print("<html>");
        pwriter.print("<body>");
        pwriter.print("<h2>Generic Servlet Example</h2>");
        pwriter.print("<p>Hello BeginnersBook Readers!</p>");
        pwriter.print("</body>");
        pwriter.print("</html>");
    }
}
