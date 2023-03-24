package com.iflytek.staff.chao.function;

import com.iflytek.staff.chao.Constants;

import javax.servlet.RequestDispatcher;
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
 * @Description: 测试登录
 */
@WebServlet(name ="LoginServlet" ,urlPatterns = "/loginPage")
public class LoginServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(Constants.MIME_TEXT);
        response.setCharacterEncoding(Constants.MIME_ENCODING);
        PrintWriter pwriter = response.getWriter();
        String name = request.getParameter("uname");
        String password = request.getParameter("upass");

        pwriter.print("Hello " + name);
        pwriter.print("Your Password is: " + password);
        HttpSession session = request.getSession();
        session.setAttribute("uname", name);
        session.setAttribute("upass", password);
        pwriter.print("<a href='welcome'>view details</a>");
        pwriter.close();
    }

    /**
     * 比较 forward 和 include 区别
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(Constants.MIME_TEXT);
        response.setCharacterEncoding(Constants.MIME_ENCODING);
        PrintWriter pwriter = response.getWriter();
        String name = request.getParameter("uname");
        String pass = request.getParameter("upass");
        if (name.equals("Chaitanya") &&
                pass.equals("beginnersbook")) {
            RequestDispatcher dis = request.getRequestDispatcher("welcome");
            dis.forward(request, response);
        } else {
            pwriter.print("User name or password is incorrect!");
            RequestDispatcher dis = request.getRequestDispatcher("form.html");
            dis.include(request, response);
        }
    }


}
