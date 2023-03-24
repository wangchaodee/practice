package com.iflytek.staff.chao.function;

import com.iflytek.staff.chao.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : wangchaodee
 * @Description: 测试Session
 */
@WebServlet(name ="SessionServlet" ,urlPatterns = "/getsession")
public class SessionServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(Constants.MIME_TEXT);
        PrintWriter pwriter = response.getWriter();
        HttpSession session = request.getSession(false);
        String myName = (String) session.getAttribute("uname");
        String myPass = (String) session.getAttribute("upass");
        pwriter.print("Name: " + myName + " Pass: " + myPass);
        pwriter.close();
    }


}
