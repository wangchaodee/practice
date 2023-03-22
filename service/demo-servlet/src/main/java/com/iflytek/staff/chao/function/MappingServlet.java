package com.iflytek.staff.chao.function;

import com.iflytek.staff.chao.Constants;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : wangchaodee
 * @Description: MappingServlet  注解功能
 */
@WebServlet(name ="MappingServlet" ,urlPatterns = "/testMapping")
public class MappingServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(Constants.MIME_TEXT_ENCODE);
        PrintWriter pwriter = response.getWriter();

        HttpServletMapping mapping = request.getHttpServletMapping();
        String mapName = mapping.getMappingMatch().name();
        String value = mapping.getMatchValue();
        String pattern = mapping.getPattern();
        String servletName = mapping.getServletName();

        StringBuilder builder = new StringBuilder();
        builder.append("Mapping type: ").append(mapName)
                .append("; Match value: ").append(value)
                .append("; Pattern: ").append(pattern)
                .append("; Servlet name: ").append(servletName);

//        ServletOutputStream out = response.getOutputStream();
//        out.println(builder.toString());
        pwriter.println(builder.toString());
        pwriter.close();
    }


}
